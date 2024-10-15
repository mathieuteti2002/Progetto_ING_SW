package databasecredenziali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UpdateIsLogged{

	 
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String utente() {
    	String sql = "SELECT utente,islogged FROM Account WHERE islogged=1";
    	String utente="";
     try (Connection conn = this.connect();) {
    	 ResultSet rs = conn.createStatement().executeQuery(sql);
     	
    	 while(rs.next())
    	 {
    		 utente=rs.getString("utente");
    		 System.out.println(utente);
    	 }
			
         // update 
			
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
     return utente;
    }
    
    public void update(String utente, int islogged) {
        String sql = "UPDATE Account SET islogged = ? "
                	+ "WHERE utente = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	
			pstmt.setInt(1, islogged);
			pstmt.setString(2, utente);
			
            // update 
			pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
}