package bibliothequecroutillant

class Bibliotheque {
    
	static constraints = {
    }
	
	def rechercheTitreContient(List <Livre> livres, titre) {
		livres.find {
			it.titre == titre
		}
	}
}
