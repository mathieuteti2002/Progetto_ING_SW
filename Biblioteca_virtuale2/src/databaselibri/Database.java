package databaselibri;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
		public static String DB_REL_FILE = ".\\db\\informazioni.db3";
		public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
		
		public static void main(String[] args) throws IOException, SQLException {
			Connection conn = DriverManager.getConnection(DB_URL);
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Il nome del driver � " + meta.getDriverName());
			System.out.println("Il Database � stato creato");
			// controllo che il file esista a questo punto
			System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
		}

}
