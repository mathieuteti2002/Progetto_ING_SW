package databaselibri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CancellaDatabase {

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


    public void delete(String ISBN) {
        String sql = "DELETE FROM libri WHERE ISBN = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1,ISBN);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

