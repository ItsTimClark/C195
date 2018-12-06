// Import Packages
package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DB;
import util.Log;

public class UserDB {
    private static User currentUser;
    // Getter
    public static User getCurrentUser() {
        return currentUser;
    }
    
    // Attempt Login via DB
    public static Boolean login(String username, String password) {
        try {
            Statement statement = DB.getConnection().createStatement();
            String query = "SELECT * FROM user WHERE userName='" + username + "' AND password='" + password + "'";
            ResultSet results = statement.executeQuery(query);
            if(results.next()) {
                currentUser = new User();
                currentUser.setUsername(results.getString("userName"));
                statement.close();
                Log.log(username, true);
                return true;
            } else {
                Log.log(username, false);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }
}
