package databaselibri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDatabase{

 
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void update(String ISBN, String titolo, String autore, int quantitadisponibile, String genere,int annodipubblicazione, String copertina) {
        String sql = "UPDATE libri SET titolo = ?, autore=?, quantitadisponibile=?, genere=?, annodipubblicazione=?, copertina=?"
        		+ " WHERE ISBN=?";


        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	
			pstmt.setString(1, titolo);
			pstmt.setString(2, autore);
			pstmt.setInt(3, quantitadisponibile);
			pstmt.setString(4, genere);
			pstmt.setInt(5, annodipubblicazione);
			pstmt.setString(6, copertina);
			pstmt.setString(7, ISBN);

            // update 
			pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateQta(String ISBN,int quantitadisponibile) {
        String sql = "UPDATE libri SET quantitadisponibile=? WHERE ISBN=?";


        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, quantitadisponibile);
			pstmt.setString(2, ISBN);
            // update 
			pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}