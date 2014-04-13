package bibliothequecroustillant

class Livre {
	String titre
	Integer nombreExemplairesTotal
	Integer nombreExemplairesDispo
	
	static hasMany = [auteurs: Auteur,
					  reservations: Reservation]
	static belongsTo = Auteur
	
	TypeDocument typeDocument

    static constraints = {
		nombreExemplairesDispo min: 0, default: 0
		nombreExemplairesTotal min: 0, default: 0
		typeDocument nullable: true
    }
	
	String toString() {
		"${titre} de ${auteur}"
	}
}
