class UrlMappings {

	static mappings = {
		"/recherche/titreContient/$chaine?"(controller: "bibliotheque", action: "rechercheTitreContient")
		"/recherche/titreContient/$chaine/$offset-$max"(controller: "bibliotheque", action: "rechercheTitreContient")
		
		"/recherche/typeDeDocument/$chaine?"(controller: "bibliotheque", action: "rechercheTypeDeDocument")
		"/recherche/typeDeDocument/$chaine/$offset-$max"(controller: "bibliotheque", action: "rechercheTypeDeDocument")
		
		"/recherche/auteur/$chaine?"(controller: "bibliotheque", action: "rechercheAuteur")
		"/recherche/auteur/$chaine/$offset-$max"(controller: "bibliotheque", action: "rechercheAuteur")
		
		"/recherche/nbExemplaires/$chaine?"(controller: "bibliotheque", action: "rechercheNbExemplairesDispo")
		"/recherche/nbExemplaires/$chaine/$offset-$max"(controller: "bibliotheque", action: "rechercheNbExemplairesDispo")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
