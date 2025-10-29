
/*===============================================
* Builds and executes an UPDATE statement dynamically.
* Args:
* 	tableName      The target table name
*	colNames       The list of column names
*	userColValues  The list of user input values
*
* return: 
* 	true if the change succeeded, false otherwise
* ===============================================
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeSQL {


    public static boolean executeUpdate(String tableName, ArrayList<String> colNames, ArrayList<String> userColValues) {
        if (colNames.size() != userColValues.size()) {
            System.err.println("Column count does not match value count!");
            return false;
        }

        // Build the SQL dynamically
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tableName);
        sql.append(" SET ");
        
        for (int i = 0; i < colNames.size(); i++) {
            sql.append(colNames.get(i));
            sql.append(" = ");
            sql.append("?");
            if (i < colNames.size() - 1) sql.append(", ");
        }

        sql.append(" WHERE " + colNames.get(0) + " = " + "'" + userColValues.get(0) + "';");

        String finalSql = sql.toString();

        // Execute using PreparedStatement
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(finalSql)) {

            for (int i = 0; i < userColValues.size(); i++) {
                pstmt.setString(i + 1, userColValues.get(i));
            }

            int rows = pstmt.executeUpdate();
            System.out.println("Updated " + rows + " row(s) in " + tableName);
            return true;

        } catch (SQLException e) {
        	
        	System.out.println(finalSql);
            System.err.println("SQL update failed: " + e.getMessage());
            return false;
        }
    }
}
