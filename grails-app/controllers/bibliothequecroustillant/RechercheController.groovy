package bibliothequecroustillant

class RechercheController {

    def index() {
		render( view: "create" )
	}
	
	def paginateList(listToPaginate, Integer max, Integer offset) {
		listToPaginate.subList(offset, Math.min(offset+max, listToPaginate.size()))
	}
	
	def rechercheTitreContient(String chaine, listeRecherche) {
		listeRecherche.findAll { it.titre.find( chaine ) }
	}
	
	def rechercheTypeDeDocument(String chaine, listeRecherche) {
		listeRecherche.findAll { it.typeDocument == TypeDocument.findByIntitule(chaine) }
	}
	
	def rechercheAuteur(String chaine, listeRecherche) {
		def critereAuteur = Auteur.createCriteria()
		def auteurs = critereAuteur {
			or {
				like("nom", "%${chaine}%")
				like("prenom", "%${chaine}%")
			}
		}
		
		def livres = []
		
		auteurs.each { auteur ->
			livres += listeRecherche.findAll {
				it.auteurs.contains( auteur )
			}
		}

		livres
	}
	
	def rechercheQteDispo(String chaine, listeRecherche) {
		def nbSaisi = Integer.valueOf(chaine)
		def queryLivre = Livre.where { qteDispo >= nbSaisi }
		
		queryLivre.findAll()
	}
	
	def recherche() {
		def titreExiste		= !params.titreRecherche?.isEmpty()
		def auteurExiste	= !params.auteurRecherche?.isEmpty()
		def typeDocExiste	= params.typeDocumentRecherche != null
		def qteDispoExiste	= !params.qteDispoRecherche?.isEmpty()
		
		def max = params.max = params.int('max') ?: 5
		def offset = params.offset = params.int('offset') ?: 0
		
		def livres = Livre.list()
		
		if ( titreExiste ) {
			livres = rechercheTitreContient( params.titreRecherche, livres )
		}
		if ( auteurExiste ) {
			livres = rechercheAuteur( params.auteurRecherche, livres )
		}
		if ( typeDocExiste ) {
			livres = rechercheTypeDeDocument( params.typeDocumentRecherche, livres )
		}
		if ( qteDispoExiste ) {
			livres = rechercheQteDispo( params.qteDispoRecherche, livres )
		}
		
		render view: "recherche", model: [	titreRecherche: params.titreRecherche,
											auteurRecherche: params.auteurRecherche,
											typeDocumentRecherche: params.typeDocumentRecherche,
											qteDispoRecherche: params.qteDispoRecherche,
											livresInstances: paginateList(livres, max, offset),
											livresInstanceTotal: livres.size()]
	}
}
