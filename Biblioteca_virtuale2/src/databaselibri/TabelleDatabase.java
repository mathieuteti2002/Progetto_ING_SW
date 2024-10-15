package databaselibri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TabelleDatabase {

	public static void main(String args[]) throws IOException {

		try {
			Connection Account = DriverManager.getConnection(Database.DB_URL);
			Account.isValid(0);
			if (Account != null) {
				Statement stmt = Account.createStatement();
				String sql = "CREATE TABLE libri("
		                + "	ISBN text PRIMARY KEY UNIQUE,"
		                + "	titolo text,"
		                + "	autore text,"
		                + "	quantitadisponibile integer,"
		                + " genere text,"
		                + "	annodipubblicazione text"
						+ " copertina text);";
				
				stmt.execute(sql);
				stmt.close();
				Account.close();
				System.out.println("Tabella Creata");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finito");
		}
	}
	
}



