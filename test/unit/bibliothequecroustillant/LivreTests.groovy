package bibliothequecroustillant


import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

    void testInitialisation() {
		def livre = null
		
		livre = new Livre( titre: "A Great Title", qteDispo: 10, qteTotale: 10 )
		
		assert livre
    }
	
	void testToString() {
		def livre = new Livre( titre: "A Great Title", qteDispo: 10, qteTotale: 10 )
		
		def string = livre.toString()
		
		assert string == "A Great Title"
	}
}
