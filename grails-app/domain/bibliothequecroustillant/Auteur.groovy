package bibliothequecroustillant

class Auteur {
	
	String nom, prenom
	
	static hasMany = [publications: Livre]

    static constraints = {
		prenom nullable: true
    }
	
	String toString() {
		nom + " " + prenom
	}
}
