package bibliothequecroustillant


import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

    void testInitialisation() {
		def auteur = null
		
		auteur = new Auteur( nom: "Smith", prenom: "John" )
		
		assert auteur
    }
	
	void testToString() {
		def auteur = new Auteur( nom: "Smith", prenom: "John" )
		
		def string = auteur.toString()
		
		assert string == "Smith John"
	}
}
