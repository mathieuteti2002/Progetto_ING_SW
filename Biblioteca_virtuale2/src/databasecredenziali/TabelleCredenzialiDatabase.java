package databasecredenziali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TabelleCredenzialiDatabase {

	public static void main(String[] args) {
		try {
			Connection account = DriverManager.getConnection(Database.DB_URL);
			account.isValid(0);
			if (account != null) {
				Statement stmt = account.createStatement();
				String sql = "CREATE TABLE Account("
		                + "	email text UNIQUE,"
		                + "	utente text UNIQUE,"
		                + "	password text,"
		                + " livello integer,"
		                + " libriprenotati text, "
		                + " islogged integer, PRIMARY KEY(email,utente))";
				
				stmt.execute(sql);
				stmt.close();
				account.close();
				System.out.println("Tabella Creata");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finito");
		}

	}

}
