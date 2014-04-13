package bibliothequecroustillant

class Reservation {
	String code
	Date dateReservation
	
	
	static hasMany = [livresReserves: Livre]
	static belongsTo = Livre

    static constraints = {
    }
}
