class UrlMappings {

	static mappings = {
		"/recherche/titreContient/$chaine?"(controller: "bibliotheque", action: "rechercheTitreContient")
		"/recherche/typeDeDocument/$chaine?"(controller: "bibliotheque", action: "rechercheTypeDeDocument")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
