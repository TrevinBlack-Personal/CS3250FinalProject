/* =======================================
 * Method for seeding the database 
 * =======================================
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeederMain {

    // Method to check if a table already has rows
    private static boolean tableHasData(String tableName) {
        String sql = "SELECT COUNT(*) AS count FROM " + tableName;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.getInt("count") > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // error. assume no data
        }
    }

    public static void runSeeder() {
        Database.initializeDatabase(); 

        // Only seed if equipment table is empty
        if (tableHasData("equipment")) {
            System.out.println("Database already seeded. Reseting.");
            
            Database.reset();
        }

        System.out.println("Seeding database...");

        // Equipment
        CSVSeeder.seedTable(
            "data/equipment.csv",
            "INSERT INTO equipment(equipmentId,name,type,quantity,status) VALUES (?,?,?,?,?)",
            5
        );

        // Spare Parts
        CSVSeeder.seedTable(
            "data/spareparts.csv",
            "INSERT INTO spareparts(partId,name,quantityInStock,cost) VALUES (?,?,?,?)",
            4
        );

        // Fuel
        CSVSeeder.seedTable(
            "data/fuel.csv",
            "INSERT INTO fuel(fuelId,fuelType,quantity,costPerUnit) VALUES (?,?,?,?)",
            4
        );

        // Maintenance Log
        CSVSeeder.seedTable(
            "data/maintenancelog.csv",
            "INSERT INTO maintenancelog(recordId,equipmentId,maintenanceDate,description,performedBy,fuelType,quantity,costPerUnit) VALUES (?,?,?,?,?,?,?,?)",
            8
        );

        // Inspections
        CSVSeeder.seedTable(
            "data/inspections.csv",
            "INSERT INTO inspections(inspectionId,equipmentId,inspectionDate,inspectorName,description,passed) VALUES (?,?,?,?,?,?)",
            6
        );

        // Schedule
        CSVSeeder.seedTable(
            "data/schedule.csv",
            "INSERT INTO schedule(scheduleId,equipmentId,scheduleDate,taskType,assignedTo) VALUES (?,?,?,?,?)",
            5
        );

        // Downtime
        CSVSeeder.seedTable(
            "data/downtime.csv",
            "INSERT INTO downtime(logId,equipmentId,startTime,endTime,reason,duration) VALUES (?,?,?,?,?,?)",
            6
        );

        // Work Orders
        CSVSeeder.seedTable(
            "data/workorder.csv",
            "INSERT INTO workorder(orderId,orderDate,status,totalAmount,description,assignedEmployee,dueDate) VALUES (?,?,?,?,?,?,?)",
            7
        );

        // Sales Orders
        CSVSeeder.seedTable(
            "data/salesorder.csv",
            "INSERT INTO salesorder(orderId,orderDate,status,totalAmount,customerId,shippingAddress,paymentMethod) VALUES (?,?,?,?,?,?,?)",
            7
        );

        // Invoices
        CSVSeeder.seedTable(
            "data/invoice.csv",
            "INSERT INTO invoice(orderId,orderDate,status,totalAmount,invoiceNumber,dueDate,paid) VALUES (?,?,?,?,?,?,?)",
            7
        );

        // Purchase Orders
        CSVSeeder.seedTable(
            "data/purchaseorder.csv",
            "INSERT INTO purchaseorder(orderId,orderDate,status,totalAmount,vendorId,expectedDeliveryDate,shippingMethod) VALUES (?,?,?,?,?,?,?)",
            7
        );

        // Users
        CSVSeeder.seedTable(
            "data/user.csv",
            "INSERT INTO users(id,name,email,password) VALUES (?,?,?,?)",
            4
        );

        // Employees
        CSVSeeder.seedTable(
            "data/employee.csv",
            "INSERT INTO employee(employeeId,userId,department,salary) VALUES (?,?,?,?)",
            4
        );

        // Managers
        CSVSeeder.seedTable(
            "data/manager.csv",
            "INSERT INTO manager(managerId,employeeId,teamSize,level) VALUES (?,?,?,?)",
            4
        );

        // Customers
        CSVSeeder.seedTable(
            "data/customer.csv",
            "INSERT INTO customer(customerId,userId,address,phoneNumber) VALUES (?,?,?,?)",
            4
        );

        // Vendors
        CSVSeeder.seedTable(
            "data/vendor.csv",
            "INSERT INTO vendor(vendorId,userId,companyName,productCategory) VALUES (?,?,?,?)",
            4
        );

        System.out.println("Database seeding complete.");
    }
}
