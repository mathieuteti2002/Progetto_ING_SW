package databasecredenziali;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class RegistrazioneDatabase {

		private Connection connect() {
			Connection Account = null;
			try {
				Account = DriverManager.getConnection(Database.DB_URL);
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
			
			return Account;
			
		}
		
		public void insCredenziali(String email, String utente, String password, int livello, String libriprenotati,int islogged){
			String sql = "INSERT INTO Account(email,utente,password,livello,libriprenotati,islogged) VALUES(?,?,?,?,?,?)";
			
			try(Connection Account = this.connect(); PreparedStatement pstmt = Account.prepareStatement(sql))  {
					pstmt.setString(1, email);
					pstmt.setString(2, utente);
					pstmt.setString(3, password);
					pstmt.setInt(4, livello);
					pstmt.setString(5, libriprenotati);
					pstmt.setInt(6, islogged);
					
					pstmt.executeUpdate();
					
					System.out.println("Credenziali inserite con successo");
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


