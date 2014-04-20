package bibliothequecroustillant


import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TypeDocument)
class TypeDocumentTests {

    void testInitialisation() {
		def typeDocument = null
		
		typeDocument = new TypeDocument( intitule: "This is a document type" )
		
		assert typeDocument
    }
	
	void testToString() {
		def typeDocument = new TypeDocument( intitule: "This is a document type" )
		
		def string = typeDocument.toString()
		
		assert string == "This is a document type"
	}
}
