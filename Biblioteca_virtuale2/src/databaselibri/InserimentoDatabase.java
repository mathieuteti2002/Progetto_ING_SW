package databaselibri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;


public class InserimentoDatabase {
	
	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Database.DB_URL);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return conn;
		
	}
	
	public void inserimento(String ISBN, String titolo, String autore, int quantitadisponibile, String genere,int annodipubblicazione, String cop){
		String sql = "INSERT INTO libri(ISBN,titolo,autore,quantitadisponibile,genere,annodipubblicazione,copertina) VALUES(?,?,?,?,?,?,?);";

		try(Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql))  {
				pstmt.setString(1, ISBN);
				pstmt.setString(2, titolo);
				pstmt.setString(3, autore);
				pstmt.setInt(4, quantitadisponibile);
				pstmt.setString(5, genere);
				pstmt.setInt(6, annodipubblicazione);
			    pstmt.setString(7, cop);				
			    pstmt.executeUpdate();
				
				System.out.println("Informazioni inserite con successo");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static void close(Statement stmt)
	{
		try {
			if(stmt !=null)
				stmt.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}


}
