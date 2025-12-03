/*===============================================
* Builds and executes an INSERT INTO statement dynamically.
* Args:
* 	tableName      The target table name
*	colNames       The list of column names
*	userColValues  The list of user input values
*
* return: 
* 	true if the insert succeeded, false otherwise
* ===============================================
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddSql {


    public static boolean executeInsert(String tableName, ArrayList<String> colNames, ArrayList<String> userColValues) {
        if (colNames.size() != userColValues.size()) {
            System.err.println("Column count does not match value count!");
            return false;
        }

        // Build the SQL dynamically
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableName).append(" (");

        for (int i = 0; i < colNames.size(); i++) {
            sql.append(colNames.get(i));
            if (i < colNames.size() - 1) sql.append(", ");
        }

        sql.append(") VALUES (");

        for (int i = 0; i < userColValues.size(); i++) {
            sql.append("?");
            if (i < userColValues.size() - 1) sql.append(", ");
        }

        sql.append(");");

        String finalSql = sql.toString();

        // Execute using PreparedStatement
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(finalSql)) {

            for (int i = 0; i < userColValues.size(); i++) {
                pstmt.setString(i + 1, userColValues.get(i));
            }

            int rows = pstmt.executeUpdate();
            
            new Thread(() -> {
                PopUp.show("Inserted " + rows + " row(s) into " + tableName);
            }).start();

            
            return true;

        } catch (SQLException e) {
            System.err.println("SQL Insert failed: " + e.getMessage());
            return false;
        }
    }
}
