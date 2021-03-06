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
		
		"/recherche/recherche"(controller: "recherche", action: "recherche")
		"/recherche/recherche/$offset-$max"(controller: "recherche", action: "recherche")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"recherche", action:"index")
		"500"(view:'/error')
	}
}
