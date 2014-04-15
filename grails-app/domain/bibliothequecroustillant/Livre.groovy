package bibliothequecroustillant

class Livre {
	String titre
	Integer qteTotale
	Integer qteDispo
	
	static hasMany = [auteurs: Auteur,
					  reservations: Reservation]
	static belongsTo = Auteur
	
	TypeDocument typeDocument

    static constraints = {
		qteDispo min: 0
		qteTotale min: 0
    }
	
	String toString() {
		"${titre}"
	}
}
