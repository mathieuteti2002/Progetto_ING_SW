package databasecredenziali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateLibriPrenotati{

	 
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

    public void update(String libriprenotati,int islogged) {
        String sql = "UPDATE Account SET libriprenotati=? WHERE islogged=1";
        String app = "";

        try (Connection conn = this.connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
           ResultSet rs = conn.createStatement().executeQuery("SELECT libriprenotati FROM Account");
  		   while(rs.next())
  		   {
  			   app=libriprenotati+rs.getString("libriprenotati");
  		   }
  		   
			pstmt.setString(1, app);
			//pstmt.setInt(2, islogged);
			
            // update 
			pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
  


}