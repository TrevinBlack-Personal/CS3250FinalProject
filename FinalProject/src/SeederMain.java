import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeederMain {

    /* =====================================================
     * FIRST RUN — SEED ONLY LOGIN TABLES
     * ===================================================== */
    public static void firstRun() {

        // Create tables (if not exist)
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

    /* =====================================================
     * CHECK IF TABLE HAS ANY ROWS
     * ===================================================== */
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

    /* =====================================================
     * RUN SEEDER AFTER LOGIN
     * ===================================================== */
    public static void runSeeder(boolean isManager) {

        // If equipment table already has data → reset DB and reseed
        if (tableHasData("equipment")) {
            System.out.println("Database already seeded. Resetting...");
            Database.reset(isManager);

            // reseed tables after reset
            firstRun();  
        }

        // Create tables again
        Database.initializeDatabase(isManager);

        System.out.println("Seeding database...");

        if (isManager) {
            seedManagerData();
        } else {
            seedUserData();
        }

        System.out.println("Database seeding complete.");
    }


    /* =====================================================
     * MANAGER SEEDING — FULL DATABASE
     * ===================================================== */
    private static void seedManagerData() {

        // Equipment
        CSVSeeder.seedTable("data/equipment.csv",
                "INSERT INTO equipment(equipmentId,name,type,quantity,status) VALUES (?,?,?,?,?)", 5);

        // Spare Parts
        CSVSeeder.seedTable("data/spareparts.csv",
                "INSERT INTO spareparts(partId,name,quantityInStock,cost) VALUES (?,?,?,?)", 4);

        // Fuel
        CSVSeeder.seedTable("data/fuel.csv",
                "INSERT INTO fuel(fuelId,fuelType,quantity,costPerUnit) VALUES (?,?,?,?)", 4);

        // Maintenance Log
        CSVSeeder.seedTable("data/maintenancelog.csv",
                "INSERT INTO maintenancelog(recordId,equipmentId,maintenanceDate,description,performedBy,fuelType,quantity,costPerUnit) VALUES (?,?,?,?,?,?,?,?)",
                8);

        // Inspections
        CSVSeeder.seedTable("data/inspections.csv",
                "INSERT INTO inspections(inspectionId,equipmentId,inspectionDate,inspectorName,description,passed) VALUES (?,?,?,?,?,?)",
                6);

        // Schedule
        CSVSeeder.seedTable("data/schedule.csv",
                "INSERT INTO schedule(scheduleId,equipmentId,scheduleDate,taskType,assignedTo) VALUES (?,?,?,?,?)", 5);

        // Downtime
        CSVSeeder.seedTable("data/downtime.csv",
                "INSERT INTO downtime(logId,equipmentId,startTime,endTime,reason,duration) VALUES (?,?,?,?,?,?)",
                6);

        // Work Orders
        CSVSeeder.seedTable("data/workorder.csv",
                "INSERT INTO workorder(orderId,orderDate,status,totalAmount,description,assignedEmployee,dueDate) VALUES (?,?,?,?,?,?,?)",
                7);

        // Sales Orders
        CSVSeeder.seedTable("data/salesorder.csv",
                "INSERT INTO salesorder(orderId,orderDate,status,totalAmount,customerId,shippingAddress,paymentMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        // Invoices
        CSVSeeder.seedTable("data/invoice.csv",
                "INSERT INTO invoice(orderId,orderDate,status,totalAmount,invoiceNumber,dueDate,paid) VALUES (?,?,?,?,?,?,?)",
                7);

        // Purchase Orders
        CSVSeeder.seedTable("data/purchaseorder.csv",
                "INSERT INTO purchaseorder(orderId,orderDate,status,totalAmount,vendorId,expectedDeliveryDate,shippingMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        // Customer
        CSVSeeder.seedTable("data/customer.csv",
                "INSERT INTO customer(customerId,userId,address,phoneNumber) VALUES (?,?,?,?)", 4);

        // Vendor
        CSVSeeder.seedTable("data/vendor.csv",
                "INSERT INTO vendor(vendorId,userId,companyName,productCategory) VALUES (?,?,?,?)", 4);
    }

    /* =====================================================
     * REGULAR USER SEEDING — LIMITED TABLES
     * ===================================================== */
    private static void seedUserData() {

        // Equipment
        CSVSeeder.seedTable("data/equipment.csv",
                "INSERT INTO equipment(equipmentId,name,type,quantity,status) VALUES (?,?,?,?,?)", 5);

        // Spare Parts
        CSVSeeder.seedTable("data/spareparts.csv",
                "INSERT INTO spareparts(partId,name,quantityInStock,cost) VALUES (?,?,?,?)", 4);

        // Fuel
        CSVSeeder.seedTable("data/fuel.csv",
                "INSERT INTO fuel(fuelId,fuelType,quantity,costPerUnit) VALUES (?,?,?,?)", 4);

        // Work Orders
        CSVSeeder.seedTable("data/workorder.csv",
                "INSERT INTO workorder(orderId,orderDate,status,totalAmount,description,assignedEmployee,dueDate) VALUES (?,?,?,?,?,?,?)",
                7);

        // Sales Orders
        CSVSeeder.seedTable("data/salesorder.csv",
                "INSERT INTO salesorder(orderId,orderDate,status,totalAmount,customerId,shippingAddress,paymentMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        // Invoices
        CSVSeeder.seedTable("data/invoice.csv",
                "INSERT INTO invoice(orderId,orderDate,status,totalAmount,invoiceNumber,dueDate,paid) VALUES (?,?,?,?,?,?,?)", 7);

        // Purchase Orders
        CSVSeeder.seedTable("data/purchaseorder.csv",
                "INSERT INTO purchaseorder(orderId,orderDate,status,totalAmount,vendorId,expectedDeliveryDate,shippingMethod) VALUES (?,?,?,?,?,?,?)",
                7);

        // Customer
        CSVSeeder.seedTable("data/customer.csv",
                "INSERT INTO customer(customerId,userId,address,phoneNumber) VALUES (?,?,?,?)", 4);

        // Vendor
        CSVSeeder.seedTable("data/vendor.csv",
                "INSERT INTO vendor(vendorId,userId,companyName,productCategory) VALUES (?,?,?,?)", 4);
    }
}
