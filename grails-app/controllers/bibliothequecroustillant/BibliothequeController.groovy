package bibliothequecroustillant

import org.springframework.dao.DataIntegrityViolationException

import bibliothequecroustillant.Bibliotheque;
import bibliothequecroustillant.Livre;

class BibliothequeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bibliothequeInstanceList: Bibliotheque.list(params), bibliothequeInstanceTotal: Bibliotheque.count()]
    }

    def create() {
        [bibliothequeInstance: new Bibliotheque(params)]
    }
	
	def paginateList(listToPaginate, Integer max, Integer offset) {
		listToPaginate.subList(offset, Math.min(offset+max, listToPaginate.size()))
	 }
	
	def rechercheTitreContient(String chaine) {
		def max = params.max = params.int('max') ?: 5 
		def offset = params.offset = params.int('offset') ?: 0
		def livres = Livre.findAllByTitreLike ( "%${chaine}%" )
		
		if (offset < 0 || offset > livres.size()) {
			flash.message = message(code: 'bad.offset.message', default: "You must specify a valid offset")
			
			max = 5
			offset = 0
		}
		else if (!chaine) {
			flash.message = message(code: 'bad.searchparam.message', default: "You must specify a valid search parameter")
			
			max = 5
			offset = 0
			chaine = ""
		}

		render view: "recherche", model: [	chaineRecherche: chaine, 
											livresInstances: paginateList(livres, max, offset),
											livresInstanceTotal: livres.size()]
	}
	def rechercheTypeDeDocument(String chaine) {
		def max = params.max = params.int('max') ?: 5
		def offset = params.offset = params.int('offset') ?: 0
		
		def livres = Livre.findAllByTypeDocument ( TypeDocument.findByIntitule(chaine) )
		
		if (offset < 0 || offset > livres.size()) {
			flash.message = message(code: 'bad.offset.message', default: "You must specify a valid offset")
			
			max = 5
			offset = 0
		}
		else if (!chaine) {
			flash.message = message(code: 'bad.searchparam.message', default: "You must specify a valid search parameter")
			
			max = 5
			offset = 0
			chaine = ""
		}
		
		render view: "recherche", model: [	chaineRecherche: chaine,
											livresInstances: paginateList(livres, max, offset),
											livresInstanceTotal: livres.size()]
	}
	def rechercheAuteur(String chaine) {
		def max = params.max = params.int('max') ?: 5
		def offset = params.offset = params.int('offset') ?: 0
		
		def critereLivre = Livre.createCriteria()
		def livres = critereLivre {
			auteurs {
				like("nom", "%${chaine}%")
			}
		}
		
				
		if (offset < 0 || offset > livres.size()) {
			flash.message = message(code: 'bad.offset.message', default: "You must specify a valid offset")
			
			max = 5
			offset = 0
		}
		else if (!chaine) {
			flash.message = message(code: 'bad.searchparam.message', default: "You must specify a valid search parameter")
			
			max = 5
			offset = 0
			chaine = ""
		}
		
		render view: "recherche", model: [	chaineRecherche: chaine,
											livresInstances: paginateList(livres, max, offset),
											livresInstanceTotal: livres.size()]
	}
    def save() {
        def bibliothequeInstance = new Bibliotheque(params)
        if (!bibliothequeInstance.save(flush: true)) {
            render(view: "create", model: [bibliothequeInstance: bibliothequeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), bibliothequeInstance.id])
        redirect(action: "show", id: bibliothequeInstance.id)
    }

    def show(Long id) {
        def bibliothequeInstance = Bibliotheque.get(id)
        if (!bibliothequeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "list")
            return
        }

        [bibliothequeInstance: bibliothequeInstance]
    }

    def edit(Long id) {
        def bibliothequeInstance = Bibliotheque.get(id)
        if (!bibliothequeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "list")
            return
        }

        [bibliothequeInstance: bibliothequeInstance]
    }

    def update(Long id, Long version) {
        def bibliothequeInstance = Bibliotheque.get(id)
        if (!bibliothequeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bibliothequeInstance.version > version) {
                bibliothequeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bibliotheque.label', default: 'Bibliotheque')] as Object[],
                          "Another user has updated this Bibliotheque while you were editing")
                render(view: "edit", model: [bibliothequeInstance: bibliothequeInstance])
                return
            }
        }

        bibliothequeInstance.properties = params

        if (!bibliothequeInstance.save(flush: true)) {
            render(view: "edit", model: [bibliothequeInstance: bibliothequeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), bibliothequeInstance.id])
        redirect(action: "show", id: bibliothequeInstance.id)
    }

    def delete(Long id) {
        def bibliothequeInstance = Bibliotheque.get(id)
        if (!bibliothequeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "list")
            return
        }

        try {
            bibliothequeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bibliotheque.label', default: 'Bibliotheque'), id])
            redirect(action: "show", id: id)
        }
    }
}
