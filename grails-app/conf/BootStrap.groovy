import bibliothequecroustillant.Auteur
import bibliothequecroustillant.Livre
import bibliothequecroustillant.TypeDocument


class BootStrap {

    def init = { servletContext ->
		def csv = new File("books.csv")
		csv.splitEachLine(';') { row ->
			if( row[4] != null && !row[1].isEmpty() ) {
				def rawAuteur = row[4].tokenize(", ");
				def prenomAuteur = rawAuteur[1]
				def nomAuteur = rawAuteur[0]
				def auteur = Auteur.findByNom(nomAuteur) ?: new Auteur( nom: nomAuteur, prenom: prenomAuteur )
				auteur.save()
				
				def typeDocument = TypeDocument.findByIntitule(row[1]) ?: new TypeDocument( intitule: row[1] )
				typeDocument.save()
				
			    def livre = new Livre(
					typeDocument: typeDocument,
				    titre: row[3],
				    qteDispo: 10,
				    qteTotale: 10
				)
				
				if ( auteur != null) {
					livre.addToAuteurs(auteur)
				}
			}
		}
    }
    def destroy = {
    }
}
