import bibliothequecroutillant.Bibliotheque;
import bibliothequecroutillant.Livre

class BootStrap {

    def init = { servletContext ->
		new Livre(titre: "Livre 1", auteur: "Truc machin", type: "Roman").save();
		new Livre(titre: "Livre 2", auteur: "Truc machin1", type: "Nouvelle").save();
		new Livre(titre: "Livre 3", auteur: "Truc machin2", type: "Article").save();
		
    }
    def destroy = {
    }
}
