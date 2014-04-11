class UrlMappings {

	static mappings = {
		
		"/recherche/titreContient/$chaine?"(controller: "bibliotheque", action: "rechercheTitreContient", view: "/recherche.gsp")
		"/recherche/typeDeDocument/$chaine?"(controller: "bibliotheque", action: "rechercheTypeDeDocument", view: "/recherche.gsp")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
