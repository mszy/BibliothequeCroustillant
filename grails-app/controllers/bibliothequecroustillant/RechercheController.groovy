package bibliothequecroustillant

import java.text.SimpleDateFormat;

import org.apache.catalina.core.ApplicationContext;

class RechercheController {
	
	def formatDate( date ) {
		new SimpleDateFormat( "EEEE dd MMMM yyyy - HH:mm" ).format( date )
	}

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
		def titreExiste		= params.titreRecherche != null && !params.titreRecherche.isEmpty()
		def auteurExiste	= params.auteurRecherche != null && !params.auteurRecherche.isEmpty()
		def typeDocExiste	= params.typeDocumentRecherche != null
		def qteDispoExiste	= params.qteDispoRecherche != null && !params.qteDispoRecherche.isEmpty()
		
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
		
		if( livres.isEmpty() ) {
			flash.message = message(code: 'search.noresult.message', default: "There are no results for your search")
		}
		
		render view: "recherche", model: [	titreRecherche: params.titreRecherche,
											auteurRecherche: params.auteurRecherche,
											typeDocumentRecherche: params.typeDocumentRecherche,
											qteDispoRecherche: params.qteDispoRecherche,
											livresInstances: paginateList(livres, max, offset),
											livresInstanceTotal: livres.size()]
	}
	
	def ajouterAuPanier(Long id) {
		if( session.getAttribute("panier") == null ) {
			session.setAttribute("panier", [:])
		}
		
		def livreDansPanier = session.getAttribute("panier").find { it.key == id } != null
		def valeur
		if( !livreDansPanier ) {
			valeur = 1
		} else {
			valeur = session.getAttribute("panier").get( id )
			valeur++
			session.getAttribute("panier").get( id )
		}
		
		session.getAttribute("panier").put( (id), valeur )
		
		redirect action: "recherche", params: params
	}
	
	def retirerDuPanier( Long id ) {
		def valeur = session.getAttribute( "panier" ).get( id )
		if( valeur == 1 ) {
			session.getAttribute( "panier" ).remove( id )
		} else {
			session.getAttribute( "panier" ).put( (id), valeur - 1 )
		}
		
		if( session.getAttribute( "panier" ).size() == 0 ) {
			session.removeAttribute( "panier" )
		}
		
		redirect( uri: request.getHeader('referer') )
	}
	
	def controlerPanier() {
		
		def livresAReserver = session.getAttribute("panier")
		def reservationComplete = true
		def livresIndisponibles = []
		
		if( livresAReserver == null ) {
			redirect( action: "index" )
			return
		}
		
		session.getAttribute("panier").each {
			def livreAAjouter = Livre.get( it.key )
			if( it.value > livreAAjouter.qteDispo ) {
				reservationComplete = false
				livresIndisponibles << it.key
			}
		}
		
		if ( session.getAttribute("panier").keySet() == livresIndisponibles.toSet() ) {
			flash.message = message(code: 'cart.allgoodunavailable.message', default: "None of the goods are available.")
			session.removeAttribute("panier")
			redirect action: "index"
			return
		} else if ( !reservationComplete ) {
			render view: "correction", model: [ livresIndisponibles: livresIndisponibles ]
			return
		}
		
		def reservation = validerReservation( livresAReserver )
		
		def dateText = formatDate( reservation.dateReservation + 1 )
		
		render view: "validation", model: [ livres: livresAReserver,
											reservationCode: reservation.code,
											date: dateText ]
	}
	
	def corrigerPanier() {
		def livresAReserver = session.getAttribute("panier").clone()
		if( livresAReserver == null ) {
			redirect( action: "index" )
			return
		}
		
		def livresIndisponibles = Eval.me( params.livresIndisponibles )
		livresAReserver.each {
			if ( livresIndisponibles.contains( it.key as Integer ) ) {
				it.remove()
			}
		}
		
		def reservation = validerReservation( livresAReserver )
		
		def dateText = formatDate( reservation.dateReservation + 1 )
		
		render view: "validation", model: [ livres: livresAReserver,
											reservationCode: reservation.code,
											date: dateText ]
	}
	
	def validerReservation( livresAReserver ) {
		def reservation = new Reservation()
		reservation.code = Reservation.list().size() + 1
		reservation.dateReservation = new Date()
		
		livresAReserver.each {
			def livre = Livre.get( it.key )
			reservation.addToLivresReserves( livre );
			livre.qteDispo -= it.value
			livre.save()
		}
		
		reservation.save()

		session.removeAttribute("panier")
		
		reservation
	}
	
	def annulerReservation() {
		session.removeAttribute("panier")
		
		redirect action: "index"
	}
}
