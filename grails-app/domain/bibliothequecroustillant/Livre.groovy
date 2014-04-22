package bibliothequecroustillant

import java.sql.Timestamp;

class Livre {
	String titre
	Integer qteTotale
	Integer qteDispo

	Timestamp version

	static hasMany = [auteurs: Auteur,
		reservations: Reservation]
	static belongsTo = Auteur

	TypeDocument typeDocument

	static constraints = {
		qteDispo min: 0
		qteTotale min: 0
	}

	static mapping = {
		auteurs lazy: false
		typeDocument lazy: false
	}

	String toString() {
		"${titre}"
	}
}
