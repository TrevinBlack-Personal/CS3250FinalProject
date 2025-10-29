/* =======================================
 * Class that dynamically creates a delete SQl statement
 * 
 * Args:
 *  	currentTableName  Name of table selected from drop down menu
 *  	pkValue 		  Primary Key Value		  
 *  	pkColumn 		  Primary Key Column
 * =======================================
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSql {
	
	public static void executeDelete(String currentTableName, String pkValue, String pkColumn) {
		
        switch (currentTableName) {
        case "maintenance":
            currentTableName = "maintenancelog";
            break;
        case "part":
            currentTableName = "spareparts";
            break;
        case "purchase":
            currentTableName = "purchaseorder";
            break;
        case "sales":
            currentTableName = "salesorder";
            break;
        case "work order":
            currentTableName = "workorder";
            break;
        case "invoice number":
            currentTableName = "invoice";
            break;

    }
        
	    String sql = "DELETE FROM " + currentTableName + " WHERE " + pkColumn + " = ?";

	    System.out.println("Deleted 1 row(s) from " + currentTableName);
	    
	    try (Connection conn = Database.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, pkValue);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Delete failed: " + e.getMessage());
	    }
	}
}
