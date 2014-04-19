package bibliothequecroustillant

import org.apache.catalina.core.ApplicationContext;

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
	
	def ajouterAuPanier( Long id ) {
		if( session.getAttribute( "panier" ) == null ) {
			session.setAttribute("panier", [:])
		}
		
		def livreDansPanier = session.getAttribute("panier").find { it.key == id }
		def valeur
		if( livreDansPanier == null ) {
			valeur = 1
		} else {
			valeur = session.getAttribute("panier").get( id )
			valeur++
			session.getAttribute("panier").get( id )
		}
		
		session.getAttribute("panier").put( (id), valeur )
		
		def livre = Livre.get( id )
		livre.qteDispo--
		livre.save()
		
		redirect action: "recherche", params: params
	}
}
