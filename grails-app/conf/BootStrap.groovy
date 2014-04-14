import bibliothequecroustillant.Auteur;
import bibliothequecroustillant.Livre

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
			}	
		    def livre = new Livre(
			    typeDocument: row[1],
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
