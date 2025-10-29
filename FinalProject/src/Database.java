/* =======================================
* A class for setting up the database. Creates all tables relevant to the database.
*
* Constructor: 
* 	InitializeDatabase() 	runs all create table SQL statements
*  
* Methods:
* 	Reset() 	Deletes all data and re initializes database based on default CSV file values
* =======================================
*/
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Database {
    private static final String URL = "jdbc:sqlite:inventory.db";
	private static final String DB_FILE = "inventory.db";

    // Create DB & table if not exist
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Database connected!");
                Statement stmt = conn.createStatement();

                // ----------------- Core Inventory -----------------------
                // Equipment
                String equipTable = "CREATE TABLE IF NOT EXISTS equipment (" +
                             "equipmentId INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "name TEXT NOT NULL," +
                             "type TEXT NOT NULL," +
                             "quantity INTEGER," +
                             "status TEXT NOT NULL" +
                             ");";

                stmt.execute(equipTable);

                System.out.println("Equipment table ready.");
                
                // Spare parts
                String SparePartsTable = "CREATE TABLE IF NOT EXISTS spareparts (" +
                        "partId INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "quantityInStock INTEGER," +
                        "cost INTEGER" +
                        ");";
                
           stmt.execute(SparePartsTable);
           System.out.println("Spare Parts table ready.");
           
           // Fuel
           String fuelTable = "CREATE TABLE IF NOT EXISTS fuel (" +
                   "fuelId INTEGER PRIMARY KEY AUTOINCREMENT," +
                   "fuelType TEXT NOT NULL," +
                   "quantity INTEGER," +
                   "costPerUnit INTEGER" +
                   ");";
           
           stmt.execute(fuelTable);
           System.out.println("Fuel table ready.");
           
        // ----------------- Maintenance Logs -----------------------
           // Maintenance Log
           String MaintenanceLogTable = "CREATE TABLE IF NOT EXISTS maintenancelog (" +
        	        "recordId INTEGER PRIMARY KEY AUTOINCREMENT," +
        	        "equipmentId INTEGER NOT NULL," +              
        	        "maintenanceDate TEXT," +                      
        	        "description TEXT," +
        	        "performedBy TEXT," +
        	        "fuelType TEXT NOT NULL," +
        	        "quantity INTEGER," +
        	        "costPerUnit INTEGER," +
        	        "FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)" +
        	        ");";

           
           stmt.execute(MaintenanceLogTable);
           System.out.println("Maintenance Log Table is ready.");
           
           // Inspections
           String InspectionsTable = "CREATE TABLE IF NOT EXISTS inspections (" +
        	        "inspectionId INTEGER PRIMARY KEY AUTOINCREMENT," +
        	        "equipmentId INTEGER NOT NULL," +
        	        "inspectionDate TEXT," +          
        	        "inspectorName TEXT," +
        	        "description TEXT," +
        	        "passed INTEGER," +               // 0 = fail, 1 = pass
        	        "FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)" +
        	        ");";

           stmt.execute(InspectionsTable);
           System.out.println("Inspections Table is ready.");
           
           // Schedule 
           String ScheduleTable = "CREATE TABLE IF NOT EXISTS schedule (" +
       	        "scheduleId INTEGER PRIMARY KEY AUTOINCREMENT," +
       	        "equipmentId INTEGER NOT NULL," +
       	        "scheduleDate TEXT," +          
       	        "taskType TEXT," +
       	        "assignedTo TEXT," +
       	        "FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)" +
       	        ");";

          stmt.execute(ScheduleTable);
          System.out.println("Schedule Table is ready.");

          // Down time
          String DowntimeTable = "CREATE TABLE IF NOT EXISTS downtime (" +
         	        "logId INTEGER PRIMARY KEY AUTOINCREMENT," +
         	        "equipmentId INTEGER NOT NULL," +
         	        "startTime TEXT," +          
         	        "endTime TEXT," +
         	        "reason TEXT," +
         	        "duration TEXT," +
         	        "FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)" +
         	        ");";
          
          stmt.execute(DowntimeTable);
          System.out.println("Downtime Table is ready.");
          
       // ----------------- Transaction Records -----------------------
          	// Work Order
          String WorkOrderTable = "CREATE TABLE IF NOT EXISTS workorder (" +
       	        "orderId INTEGER PRIMARY KEY AUTOINCREMENT," +
       	        "orderDate TEXT NOT NULL," +
       	        "status TEXT NOT NULL," +          
       	        "totalAmount REAL," +
       	        "description TEXT," +
       	        "assignedEmployee TEXT," +
       	        "dueDate TEXT" +
       	        ");";
        
        stmt.execute(WorkOrderTable);
        System.out.println("Work Order Table is ready.");
        
        	// Sales Order
        String SalesOrderTable = "CREATE TABLE IF NOT EXISTS salesorder (" +
                "orderId INTEGER PRIMARY KEY AUTOINCREMENT," +   
                "orderDate TEXT NOT NULL," +                     
                "status TEXT NOT NULL," +
                "totalAmount REAL," +                            
                "customerId INTEGER NOT NULL," +                
                "shippingAddress TEXT," +
                "paymentMethod TEXT," +
                "FOREIGN KEY (customerId) REFERENCES customer(customerId)" +
                ");";
        
        stmt.execute(SalesOrderTable);
        System.out.println("Sales Order Table is ready");
        
        	// Invoice 
        String InvoiceTable = "CREATE TABLE IF NOT EXISTS invoice (" +
                "orderId INTEGER PRIMARY KEY AUTOINCREMENT," +  
                "orderDate TEXT NOT NULL," +                     
                "status TEXT NOT NULL," +
                "totalAmount REAL," +                            
                "invoiceNumber TEXT," +
                "dueDate TEXT," +                                
                "paid INTEGER" +                                 
                ");";
        
        stmt.execute(InvoiceTable);
        System.out.println("Invoice Table is ready");
        
        	// Purchase Order
        String PurchaseOrderTable = "CREATE TABLE IF NOT EXISTS purchaseorder (" +
                "orderId INTEGER PRIMARY KEY AUTOINCREMENT," +   
                "orderDate TEXT NOT NULL," +                     
                "status TEXT NOT NULL," +
                "totalAmount REAL," +                            
                "vendorId INTEGER NOT NULL," +                   
                "expectedDeliveryDate TEXT," +                   
                "shippingMethod TEXT," +
                "FOREIGN KEY (vendorId) REFERENCES vendor(vendorId)" +
                ");";

        stmt.execute(PurchaseOrderTable);
        System.out.println("Purchase Order Table is ready");
        
        // ----------------- Users & Roles -----------------------
        	// User
        String UserTable = "CREATE TABLE IF NOT EXISTS users (" +
        	    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
        	    "name TEXT NOT NULL," +
        	    "email TEXT NOT NULL UNIQUE," +
        	    "password TEXT NOT NULL" +
        	    ");";
        	stmt.execute(UserTable);
        	System.out.println("Users Table is ready");
        
        	// Employee
        String EmployeeTable = "CREATE TABLE IF NOT EXISTS employee (" +
                "employeeId TEXT PRIMARY KEY," +                    
                "userId INTEGER NOT NULL," +                        
                "department TEXT," +
                "salary REAL," +
                "FOREIGN KEY (userId) REFERENCES user(id)" +
                ");";

        stmt.execute(EmployeeTable);
        System.out.println("Employee table is ready");
        
        	// Manager
        String ManagerTable = "CREATE TABLE IF NOT EXISTS manager (" +
                "managerId TEXT PRIMARY KEY," +                     
                "employeeId TEXT NOT NULL," +                       
                "teamSize INTEGER," +
                "level TEXT," +
                "FOREIGN KEY (employeeId) REFERENCES employee(employeeId)" +
                ");";

        stmt.execute(ManagerTable);
        System.out.println("Manager Table is ready");
        
        	// Customer
        String CustomerTable = "CREATE TABLE IF NOT EXISTS customer (" +
                "customerId TEXT PRIMARY KEY," +
                "userId INTEGER NOT NULL," +
                "address TEXT," +
                "phoneNumber TEXT," +
                "FOREIGN KEY (userId) REFERENCES user(id)" +
                ");";

        stmt.execute(CustomerTable);
        System.out.println("Customer Table is ready");
        
        	// Vendor
        String VendorTable = "CREATE TABLE IF NOT EXISTS vendor (" +
                "vendorId TEXT PRIMARY KEY," +
                "userId INTEGER NOT NULL," +
                "companyName TEXT," +
                "productCategory TEXT," +
                "FOREIGN KEY (userId) REFERENCES user(id)" +
                ");";

        stmt.execute(VendorTable);
        System.out.println("Vendor table is ready");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reset the database 
    public static void reset() {
        try {
            File dbFile = new File(DB_FILE);
            if (dbFile.exists()) {
                if (dbFile.delete()) {
                    System.out.println("Database file deleted successfully.");
                } else {
                    System.out.println("Failed to delete database file.");
                    return;
                }
            } else {
                System.out.println("No database file found. Nothing to delete.");
            }

            // Re-create database & tables
            initializeDatabase();

            System.out.println("Database has been reset and seeded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get connection for other queries
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
