package bibliothequecroustillant


import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {

    void testInitialisation() {
		def reservation = null
		
		reservation = new Reservation( code: "##1234ABCD", dateReservation: new Date() )
		
		assert reservation
	}
}
