package bibliothequecroustillant

import java.sql.Timestamp

class Auteur {
	
	String nom, prenom
	
	Timestamp version
	
	static hasMany = [publications: Livre]

    static constraints = {
		prenom nullable: true
    }
	
	String toString() {
		nom + " " + prenom
	}
}
