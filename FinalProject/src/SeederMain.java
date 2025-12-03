/* =========================================================
 * Seeder main files. Populates the database with the CSV files
 * 
 * Methods:
 *  	firstRun - Runs on first run. Populates the employee, user, and manager table so that you can login
 *  	tableHasData - Checks a table to see if it is already populated with data
 *  		ARGS: table name
 *  	runSeeder - seeds the database based on whether or not its a manger or user login
 *  		ARGS: isManager Boolean that tells if user is a manager
 *  
 *  	seedManagerData: Seeds entire database unrestricted
 *  	seedUserData: seeds restricted database
 *  ========================================================
 */			

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;

public class SeederMain {
	
	
	static int MANAGER_TABLES = 12;
	static int USER_TABLES = 9;
	
    // FIRST RUN — SEED ONLY LOGIN TABLES
    public static void firstRun() {

        // Create tables
        Database.initializeDatabase(false);

        // If users table already has records skip
        if (tableHasData("users")) {
            System.out.println("Login tables already initialized.");
            return;
        }

        System.out.println("Seeding base login tables...");

        // USERS
        CSVSeeder.seedTable(
            "data/user.csv",
            "INSERT INTO users(id,name,email,password) VALUES (?,?,?,?)",
            4
        );

        // EMPLOYEE
        CSVSeeder.seedTable(
            "data/employee.csv",
            "INSERT INTO employee(employeeId,userId,department,salary) VALUES (?,?,?,?)",
            4
        );

        // MANAGERS
        CSVSeeder.seedTable(
            "data/manager.csv",
            "INSERT INTO manager(managerId,employeeId,teamSize,level) VALUES (?,?,?,?)",
            4
        );

        System.out.println("Base login tables seeded.");
    }

    // CHECK IF TABLE HAS ANY ROWS
    private static boolean tableHasData(String tableName) {
        String sql = "SELECT COUNT(*) AS count FROM " + tableName;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next() && rs.getInt("count") > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false; // assume no data if error
        }
    }

    // Run seeder after login
    public static void runSeeder(boolean isManager) {

        // If equipment table already has data reset database
        if (tableHasData("equipment")) {
            System.out.println("\nDatabase already seeded. Resetting...\n");
            Database.reset(isManager);

            // reseed tables after reset
            firstRun();  
        }

        // Create tables again
        Database.initializeDatabase(isManager);

        System.out.println("Seeding database...");


        if (isManager) {
        	new Thread(() -> seedManagerData()).start();
        } else {
            new Thread(() -> seedUserData()).start();
        }

        System.out.println("Database seeding complete.");
    }



    // MANAGER SEEDING — FULL DATABASE
    private static void seedManagerData() {

        Platform.runLater(() ->
        	ProgressWindow.show("Seeding Manager database...")
        );
        
        // Equipment
        CSVSeeder.seedTable("data/equipment.csv",
                "INSERT INTO equipment(equipmentId,name,type,quantity,status) VALUES (?,?,?,?,?)", 5);

        ProgressWindow.update(1, MANAGER_TABLES);
        
        // Spare Parts
        CSVSeeder.seedTable("data/spareparts.csv",
                "INSERT INTO spareparts(partId,name,quantityInStock,cost) VALUES (?,?,?,?)", 4);
        
        ProgressWindow.update(2, MANAGER_TABLES);

        // Fuel
        CSVSeeder.seedTable("data/fuel.csv",
                "INSERT INTO fuel(fuelId,fuelType,quantity,costPerUnit) VALUES (?,?,?,?)", 4);

        // Maintenance Log
        CSVSeeder.seedTable("data/maintenancelog.csv",
                "INSERT INTO maintenancelog(recordId,equipmentId,maintenanceDate,description,performedBy,fuelType,quantity,costPerUnit) VALUES (?,?,?,?,?,?,?,?)",
                8);

        ProgressWindow.update(3, MANAGER_TABLES);

        // Inspections
        CSVSeeder.seedTable("data/inspections.csv",
                "INSERT INTO inspections(inspectionId,equipmentId,inspectionDate,inspectorName,description,passed) VALUES (?,?,?,?,?,?)",
                6);

        ProgressWindow.update(4, MANAGER_TABLES);

        // Schedule
        CSVSeeder.seedTable("data/schedule.csv",
                "INSERT INTO schedule(scheduleId,equipmentId,scheduleDate,taskType,assignedTo) VALUES (?,?,?,?,?)", 5);

        ProgressWindow.update(5, MANAGER_TABLES);

        // Downtime
        CSVSeeder.seedTable("data/downtime.csv",
                "INSERT INTO downtime(logId,equipmentId,startTime,endTime,reason,duration) VALUES (?,?,?,?,?,?)",
                6);

        ProgressWindow.update(6, MANAGER_TABLES);

        // Work Orders
        CSVSeeder.seedTable("data/workorder.csv",
                "INSERT INTO workorder(orderId,orderDate,status,totalAmount,description,assignedEmployee,dueDate) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(7, MANAGER_TABLES);

        // Sales Orders
        CSVSeeder.seedTable("data/salesorder.csv",
                "INSERT INTO salesorder(orderId,orderDate,status,totalAmount,customerId,shippingAddress,paymentMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(8, MANAGER_TABLES);

        // Invoices
        CSVSeeder.seedTable("data/invoice.csv",
                "INSERT INTO invoice(orderId,orderDate,status,totalAmount,invoiceNumber,dueDate,paid) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(9, MANAGER_TABLES);

        // Purchase Orders
        CSVSeeder.seedTable("data/purchaseorder.csv",
                "INSERT INTO purchaseorder(orderId,orderDate,status,totalAmount,vendorId,expectedDeliveryDate,shippingMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(10, MANAGER_TABLES);

        // Customer
        CSVSeeder.seedTable("data/customer.csv",
                "INSERT INTO customer(customerId,userId,address,phoneNumber) VALUES (?,?,?,?)", 4);

        ProgressWindow.update(11, MANAGER_TABLES);

        // Vendor
        CSVSeeder.seedTable("data/vendor.csv",
                "INSERT INTO vendor(vendorId,userId,companyName,productCategory) VALUES (?,?,?,?)", 4);
        
        ProgressWindow.update(12, MANAGER_TABLES);
        
        Platform.runLater(ProgressWindow::close);

    }

    
    // REGULAR USER SEEDING — LIMITED TABLES
    private static void seedUserData() {

        Platform.runLater(() ->
    	ProgressWindow.show("Seeding User database...")
    );
        // Equipment
        CSVSeeder.seedTable("data/equipment.csv",
                "INSERT INTO equipment(equipmentId,name,type,quantity,status) VALUES (?,?,?,?,?)", 5);

        ProgressWindow.update(1, USER_TABLES);

        // Spare Parts
        CSVSeeder.seedTable("data/spareparts.csv",
                "INSERT INTO spareparts(partId,name,quantityInStock,cost) VALUES (?,?,?,?)", 4);

        ProgressWindow.update(2, USER_TABLES);

        // Fuel
        CSVSeeder.seedTable("data/fuel.csv",
                "INSERT INTO fuel(fuelId,fuelType,quantity,costPerUnit) VALUES (?,?,?,?)", 4);

        ProgressWindow.update(3, USER_TABLES);

        // Work Orders
        CSVSeeder.seedTable("data/workorder.csv",
                "INSERT INTO workorder(orderId,orderDate,status,totalAmount,description,assignedEmployee,dueDate) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(4, USER_TABLES);

        // Sales Orders
        CSVSeeder.seedTable("data/salesorder.csv",
                "INSERT INTO salesorder(orderId,orderDate,status,totalAmount,customerId,shippingAddress,paymentMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(5, USER_TABLES);

        // Invoices
        CSVSeeder.seedTable("data/invoice.csv",
                "INSERT INTO invoice(orderId,orderDate,status,totalAmount,invoiceNumber,dueDate,paid) VALUES (?,?,?,?,?,?,?)", 7);

        ProgressWindow.update(6, USER_TABLES);

        // Purchase Orders
        CSVSeeder.seedTable("data/purchaseorder.csv",
                "INSERT INTO purchaseorder(orderId,orderDate,status,totalAmount,vendorId,expectedDeliveryDate,shippingMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        ProgressWindow.update(7, USER_TABLES);

        // Customer
        CSVSeeder.seedTable("data/customer.csv",
                "INSERT INTO customer(customerId,userId,address,phoneNumber) VALUES (?,?,?,?)", 4);

        ProgressWindow.update(8, USER_TABLES);

        // Vendor
        CSVSeeder.seedTable("data/vendor.csv",
                "INSERT INTO vendor(vendorId,userId,companyName,productCategory) VALUES (?,?,?,?)", 4);
        
        ProgressWindow.update(9, USER_TABLES);
        
        Platform.runLater(ProgressWindow::close);


    }
}
