// Import Packages
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Set DB Connection Information to UCertify MySQL Database
public class DB {
    private static final String DBNAME = "U04aHC";
    private static final String URL = "jdbc:mysql://52.206.157.109/" + DBNAME;
    private static final String USER = "U04aHC";
    private static final String PASS = "53688186382";
    private static final String DRIVER = "com.mysql.jdbc.Driver"; 
    private static Connection conn;
    
    // Connect to DB if located and auth is successful
    public static void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to MySQL Database");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()); 
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    
    // Return DB Connection
    public static Connection getConnection() {
        return conn;
    }
    
    // Close DB Connection
    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected From MySQL Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
