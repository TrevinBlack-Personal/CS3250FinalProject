/*===========================================================
 * Retrieves the SQL Query based on what was selected in searchInventory
 * Args:
 * 	   String searchOption = what was selected from drop down menu in searchInventory
 *     String searchTerm = What user typed to search for within database Ex: "John", "EMP001
 * Returns:
 *     String sql = SQL query relevant to what user selected for searchOption & searchTerm
 * 
 * 
 * 
 *=========================================================== */

public class retrieveSql {
    
    public static String getQuery(String searchOption, String searchTerm) {
        String sql = "";
        String tableName = "";
        


        // Large switch table in order to get the proper table name - this is an eye sore to look at and should probably be its own function
     // Map special cases only
        switch (searchOption) {
            case "maintenance":
                tableName = "maintenancelog";
                break;
            case "part":
                tableName = "spareparts";
                break;
            case "purchase":
                tableName = "purchaseorder";
                break;
            case "sales":
                tableName = "salesorder";
                break;
            case "work order":
                tableName = "workorder";
                break;
            case "invoice number":
                tableName = "invoice";
                break;
            default:
                tableName = searchOption;  
                break;
        }

        
        // Build search query
        if (searchTerm != null && !searchTerm.isEmpty()) { // If user typed something. Use what they typed & what option they selected from drop down menu
            switch (searchOption) {						   // to retrieve relevant SQL Query

            // SQL Query's and Switch case built mostly with Chat GPT. Because of the repetitive nature of this code I decided to use chat gpt to save my fingers the mileage.
                // ----------------- Users & Employees -----------------------
                case "employee":
                    sql = "SELECT e.employeeId, u.name AS employeeName, u.email, e.department, e.salary " +
                          "FROM employee e JOIN users u ON e.userId = u.id " +
                          "WHERE employeeName LIKE '%" + searchTerm + "%'";
                    break;

                case "manager":
                    sql = "SELECT m.managerId, m.employeeId, m.teamSize, m.level, u.name AS employeeName " +
                          "FROM manager m JOIN employee e ON m.employeeId = e.employeeId " +
                          "JOIN users u ON e.userId = u.id " +
                          "WHERE m.managerId LIKE '%" + searchTerm + "%'";
                    break;

                case "customer":
                    sql = "SELECT c.customerId, u.name, u.email, c.address, c.phoneNumber " +
                          "FROM customer c JOIN users u ON c.userId = u.id " +
                          "WHERE c.customerId LIKE '%" + searchTerm + "%'";
                    break;

                case "vendor":
                    sql = "SELECT v.vendorId, u.name, u.email, v.companyName, v.productCategory " +
                          "FROM vendor v JOIN users u ON v.userId = u.id " +
                          "WHERE v.vendorId LIKE '%" + searchTerm + "%'";
                    break;

                // ----------------- Core Inventory -----------------------
                case "equipment":
                    sql = "SELECT * FROM equipment WHERE name LIKE '%" + searchTerm + "%'";
                    break;

                case "spareparts":
                    sql = "SELECT * FROM spareparts WHERE name LIKE '%" + searchTerm + "%'";
                    break;

                case "fuel":
                    sql = "SELECT * FROM fuel WHERE fuelType LIKE '%" + searchTerm + "%'";
                    break;

                // ----------------- Maintenance Logs -----------------------
                case "maintenance":
                    sql = "SELECT m.recordId, e.name AS equipmentName, m.maintenanceDate, m.description, m.performedBy, m.fuelType, m.quantity, m.costPerUnit " +
                          "FROM maintenancelog m JOIN equipment e ON m.equipmentId = e.equipmentId " +
                          "WHERE m.recordId LIKE '%" + searchTerm + "%'";
                    break;

                case "inspection":
                    sql = "SELECT i.inspectionId, e.name AS equipmentName, i.inspectionDate, i.inspectorName, i.description, i.passed " +
                          "FROM inspections i JOIN equipment e ON i.equipmentId = e.equipmentId " +
                          "WHERE i.inspectionId LIKE '%" + searchTerm + "%'";
                    break;

                case "schedule":
                    sql = "SELECT s.scheduleId, e.name AS equipmentName, s.scheduleDate, s.taskType, s.assignedTo " +
                          "FROM schedule s JOIN equipment e ON s.equipmentId = e.equipmentId " +
                          "WHERE s.scheduleId LIKE '%" + searchTerm + "%'";
                    break;

                case "downtime":
                    sql = "SELECT d.logId, e.name AS equipmentName, d.startTime, d.endTime, d.reason, d.duration " +
                          "FROM downtime d JOIN equipment e ON d.equipmentId = e.equipmentId " +
                          "WHERE d.logId LIKE '%" + searchTerm + "%'";
                    break;

                // ----------------- Transaction Records -----------------------
                case "work order":
                    sql = "SELECT * FROM workorder WHERE orderId LIKE '%" + searchTerm + "%'";
                    break;

                case "sales":
                    sql = "SELECT so.orderId, so.orderDate, so.status, so.totalAmount, u.name AS customerName " +
                          "FROM salesorder so JOIN customer c ON so.customerId = c.customerId " +
                          "JOIN users u ON c.userId = u.id " +
                          "WHERE so.orderId LIKE '%" + searchTerm + "%'";
                    break;

                case "invoice number":
                    sql = "SELECT * FROM invoice WHERE orderId LIKE '%" + searchTerm + "%'";
                    break;

                case "purchase order":
                    sql = "SELECT po.orderId, po.orderDate, po.status, po.totalAmount, v.companyName AS vendorName, po.expectedDeliveryDate, po.shippingMethod " +
                          "FROM purchaseorder po JOIN vendor v ON po.vendorId = v.vendorId " +
                          "WHERE po.orderId LIKE '%" + searchTerm + "%'";
                    break;

                default:
                    sql = "SELECT * FROM " + tableName + " WHERE 1=1";
                    break;
            }

        } else { // Default case if user has not typed anything to search for. Retrieves whole table
            sql = "SELECT * FROM " + tableName;
        }

        return sql;
    }
}
