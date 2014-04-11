package bibliothequecroutillant

class Livre {
	String titre, auteur, type

    static constraints = {
		titre blank: false
		auteur blank: false
		type blank: false
    }
	
	String toString() {
		"${titre} de ${auteur}"
	}
}
