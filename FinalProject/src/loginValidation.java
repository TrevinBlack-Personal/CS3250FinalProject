/* =======================================
 *  Simple login validation. Checks to see if a user email & password are correct
 *  
 *  Methods:
 *  	validateLogin 	connects to db and checks for matching username and password pair
 *  
 *  Args:
 *  	String Username 	Email of the user logging in
 *  	String Password 	Password of the user logging in
 * =======================================
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginValidation {

    public static String validateLogin(String Username, String Password) {
        String sql = "SELECT u.name FROM users u WHERE u.email = ? AND u.password = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Connect to database

            pstmt.setString(1, Username); // Set Username variable 
            pstmt.setString(2, Password); // Set Password variable

            ResultSet rs = pstmt.executeQuery(); // Results of query

            if (rs.next()) { // Move to next column
                return rs.getString("name"); // get the user's name
            } else {
                return null; // no match found
            }

        } catch (Exception e) {
            System.out.println("An error has occurred connecting to the database. loginValidation");
            e.printStackTrace(); 
            return null;
        }
    }
}
