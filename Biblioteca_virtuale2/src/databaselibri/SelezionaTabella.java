package databaselibri;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class SelezionaTabella {
	
	   private Connection connect1() {
	        // SQLite connection string
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(Database.DB_URL);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	   
	   private Connection connect2() {
	        // SQLite connection string
	        Connection conn = null;
	        try {
	        	String DB_REL_FILE = ".\\\\db\\\\CredenzialiAccount.db3";
	    		String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	            conn = DriverManager.getConnection(DB_URL);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	   
	   

	 
	   public int getRowCount() throws SQLException {
	        Connection conn = this.connect1();
	        int rowCount = 0;

	        try (ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM libri")) {
	            rowCount = rs.getInt(1);
	        }

	        return rowCount;
	    }
	   
	   public Object[][] fillTable() throws SQLException{
		   Connection conn = this.connect1();
		   Object[][] backobj = new Object[100][7];
		   int numRighe= 0;
		   ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM libri");
		   while(rs.next())
		   {
			   backobj[numRighe][0]=rs.getString("ISBN");
			   backobj[numRighe][1]=rs.getString("titolo");
			   backobj[numRighe][2]=rs.getString("autore");
			   backobj[numRighe][3]=rs.getString("quantitadisponibile");
			   backobj[numRighe][4]=rs.getString("genere");
			   backobj[numRighe][5]=rs.getString("annodipubblicazione");
			   backobj[numRighe][6]=rs.getString("copertina");

			   numRighe++;
		   }
		  
		   return backobj;
	   }
	   
	   public Object[][] fillTablePrenotati() throws SQLException{
		   Connection conn1 = this.connect1(); //libri
		   Connection conn2 = this.connect2(); //credenziali
		   Object[][] backobj = new Object[100][5];
		   int numRighe= 0;
		   String pren="";

		   ResultSet rsCred= conn2.createStatement().executeQuery("SELECT libriprenotati,islogged FROM Account WHERE islogged=1");
		   ResultSet rsInfo= conn1.createStatement().executeQuery("SELECT ISBN,titolo,autore,genere,annodipubblicazione FROM libri");
		  	   
		   
		   while(rsCred.next())
		   {
			   pren=rsCred.getString("libriprenotati");
		   }

		   String[] str = pren.split(",");
		   

		   while(rsInfo.next())
		   {
			   for(int i=0;i<str.length;i++) 
			   {
				   if(rsInfo.getString("ISBN").equals(str[i]))
				   {
					   
					   backobj[numRighe][0]=rsInfo.getString("ISBN");
					   backobj[numRighe][1]=rsInfo.getString("titolo");
					   backobj[numRighe][2]=rsInfo.getString("autore");
					   backobj[numRighe][3]=rsInfo.getString("genere");
					   backobj[numRighe][4]=rsInfo.getString("annodipubblicazione");
					   numRighe++;
				   }
			   }	   
		   }

		   return backobj;
	   }
	   
	   
}
