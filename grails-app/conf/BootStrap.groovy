import bibliothequecroustillant.Auteur
import bibliothequecroustillant.Livre
import bibliothequecroustillant.TypeDocument

class BootStrap {

    def init = { servletContext ->
		def csv = new File("books.csv")
		csv.splitEachLine(';') { row ->
			def auteur
			if( !row[4] ) {
				auteur = null
			} else {
				def rawAuteur = row[4].tokenize(", ");
				def prenomAuteur = rawAuteur[1]
				def nomAuteur = rawAuteur[0]
				auteur = Auteur.findByNom(nomAuteur) ?: new Auteur( nom: nomAuteur, prenom: prenomAuteur )
				auteur.save()
			}
			def typeDocument = TypeDocument.findByIntitule(row[1]) ?: new TypeDocument( intitule: row[1] )
			typeDocument.save()
		    def livre = new Livre(
			    typeDocument: typeDocument,
			    titre: row[3],
			    nombreExemplairesDispo: 10,
			    nombreExemplairesTotal: 10
			)
			if ( auteur ) {
				livre.addToAuteurs(auteur)
			}
			livre.save()
		}
    }
    def destroy = {
    }
}
