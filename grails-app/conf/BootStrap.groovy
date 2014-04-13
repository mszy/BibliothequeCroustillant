import bibliothequecroustillant.Livre

class BootStrap {

    def init = { servletContext ->
		def csv = new File("books.csv")
		def livre
		csv.splitEachLine(';') { row ->
			println row
		   new Livre(
			   typeDocument: row[1],
			   titre: row[3]
		   ).save()
		}
    }
    def destroy = {
    }
}
