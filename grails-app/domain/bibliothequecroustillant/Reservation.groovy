package bibliothequecroustillant

import java.sql.Timestamp;

class Reservation {
	String code
	Date dateReservation
	
	Timestamp version
	
	static hasMany = [livresReserves: Livre]
	static belongsTo = Livre
}
