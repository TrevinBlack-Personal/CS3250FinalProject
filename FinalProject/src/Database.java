/* ========================================================
 * Database file that creates inventory.db & tables based on user login
 * 
 * methods:
 * 		initializeDatabase - sets up the database (creates inventory.db) and calls for table creation
 * 		createLoginTables - sets up login tables (User, Employee, Manager)
 * 		
 * ========================================================
 */

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:inventory.db";
    private static final String DB_FILE = "inventory.db";

    public static void initializeDatabase(Boolean isManager) {
        try (Connection conn = DriverManager.getConnection(URL)) {

            System.out.println("\nDatabase connected!\n");
            Statement stmt = conn.createStatement();

            //  Create log in related tables
            //  (Users, Employee, Manager)
            createLoginTables(stmt);

            //  Create Employee related tables
            createCoreInventory(stmt);
            createTransactionTables(stmt);
            createCustomerVendorTables(stmt);

            //  Create manager related tables
            if (isManager) {
                createMaintenanceTables(stmt);
                createInspectionScheduleDowntime(stmt);
            }

            System.out.println("\nDatabase initialization complete.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========== LOGIN TABLES — ALWAYS CREATED ==========
    static void createLoginTables(Statement stmt) throws Exception {

        String users = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            );
        """;

        String employee = """
            CREATE TABLE IF NOT EXISTS employee (
                employeeId TEXT PRIMARY KEY,
                userId INTEGER NOT NULL,
                department TEXT,
                salary REAL,
                FOREIGN KEY (userId) REFERENCES users(id)
            );
        """;

        String manager = """
            CREATE TABLE IF NOT EXISTS manager (
                managerId TEXT PRIMARY KEY,
                employeeId TEXT NOT NULL,
                teamSize INTEGER,
                level TEXT,
                FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
            );
        """;

        stmt.execute(users);
        stmt.execute(employee);
        stmt.execute(manager);

        System.out.println("\tLogin tables ready (users, employee, manager).\n");
    }

    // ========== CORE INVENTORY — ALWAYS CREATED ==========
    private static void createCoreInventory(Statement stmt) throws Exception {

        String equipment = """
            CREATE TABLE IF NOT EXISTS equipment (
                equipmentId INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                type TEXT NOT NULL,
                quantity INTEGER,
                status TEXT NOT NULL
            );
        """;

        String spareParts = """
            CREATE TABLE IF NOT EXISTS spareparts (
                partId INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                quantityInStock INTEGER,
                cost INTEGER
            );
        """;

        String fuel = """
            CREATE TABLE IF NOT EXISTS fuel (
                fuelId INTEGER PRIMARY KEY AUTOINCREMENT,
                fuelType TEXT NOT NULL,
                quantity INTEGER,
                costPerUnit INTEGER
            );
        """;

        stmt.execute(equipment);
        stmt.execute(spareParts);
        stmt.execute(fuel);

        System.out.println("\tCore inventory tables ready.\n");
    }

    // ========== MANAGER-ONLY TABLES ==========
    private static void createMaintenanceTables(Statement stmt) throws Exception {

        String maintenanceLog = """
            CREATE TABLE IF NOT EXISTS maintenancelog (
                recordId INTEGER PRIMARY KEY AUTOINCREMENT,
                equipmentId INTEGER NOT NULL,
                maintenanceDate TEXT,
                description TEXT,
                performedBy TEXT,
                fuelType TEXT NOT NULL,
                quantity INTEGER,
                costPerUnit INTEGER,
                FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)
            );
        """;

        stmt.execute(maintenanceLog);
        System.out.println("\tMaintenance log table ready.\n");
    }

    private static void createInspectionScheduleDowntime(Statement stmt) throws Exception {

        String inspections = """
            CREATE TABLE IF NOT EXISTS inspections (
                inspectionId INTEGER PRIMARY KEY AUTOINCREMENT,
                equipmentId INTEGER NOT NULL,
                inspectionDate TEXT,
                inspectorName TEXT,
                description TEXT,
                passed INTEGER,
                FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)
            );
        """;

        String schedule = """
            CREATE TABLE IF NOT EXISTS schedule (
                scheduleId INTEGER PRIMARY KEY AUTOINCREMENT,
                equipmentId INTEGER NOT NULL,
                scheduleDate TEXT,
                taskType TEXT,
                assignedTo TEXT,
                FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)
            );
        """;

        String downtime = """
            CREATE TABLE IF NOT EXISTS downtime (
                logId INTEGER PRIMARY KEY AUTOINCREMENT,
                equipmentId INTEGER NOT NULL,
                startTime TEXT,
                endTime TEXT,
                reason TEXT,
                duration TEXT,
                FOREIGN KEY (equipmentId) REFERENCES equipment(equipmentId)
            );
        """;

        stmt.execute(inspections);
        stmt.execute(schedule);
        stmt.execute(downtime);

        System.out.println("\tManager-only tables ready.\n");
    }

    // ========== CUSTOMER & VENDOR (ALWAYS CREATED) ==========
    private static void createCustomerVendorTables(Statement stmt) throws Exception {

        String customer = """
            CREATE TABLE IF NOT EXISTS customer (
                customerId TEXT PRIMARY KEY,
                userId INTEGER NOT NULL,
                address TEXT,
                phoneNumber TEXT,
                FOREIGN KEY (userId) REFERENCES users(id)
            );
        """;

        String vendor = """
            CREATE TABLE IF NOT EXISTS vendor (
                vendorId TEXT PRIMARY KEY,
                userId INTEGER NOT NULL,
                companyName TEXT,
                productCategory TEXT,
                FOREIGN KEY (userId) REFERENCES users(id)
            );
        """;

        stmt.execute(customer);
        stmt.execute(vendor);

        System.out.println("\tCustomer & vendor tables ready.\n");
    }

    // ========== TRANSACTION RECORDS — ALWAYS CREATED ==========
    private static void createTransactionTables(Statement stmt) throws Exception {

        String workOrder = """
            CREATE TABLE IF NOT EXISTS workorder (
                orderId INTEGER PRIMARY KEY AUTOINCREMENT,
                orderDate TEXT NOT NULL,
                status TEXT NOT NULL,
                totalAmount REAL,
                description TEXT,
                assignedEmployee TEXT,
                dueDate TEXT
            );
        """;

        String salesOrder = """
            CREATE TABLE IF NOT EXISTS salesorder (
                orderId INTEGER PRIMARY KEY AUTOINCREMENT,
                orderDate TEXT NOT NULL,
                status TEXT NOT NULL,
                totalAmount REAL,
                customerId INTEGER NOT NULL,
                shippingAddress TEXT,
                paymentMethod TEXT,
                FOREIGN KEY (customerId) REFERENCES customer(customerId)
            );
        """;

        String invoice = """
            CREATE TABLE IF NOT EXISTS invoice (
                orderId INTEGER PRIMARY KEY AUTOINCREMENT,
                orderDate TEXT NOT NULL,
                status TEXT NOT NULL,
                totalAmount REAL,
                invoiceNumber TEXT,
                dueDate TEXT,
                paid INTEGER
            );
        """;

        String purchaseOrder = """
            CREATE TABLE IF NOT EXISTS purchaseorder (
                orderId INTEGER PRIMARY KEY AUTOINCREMENT,
                orderDate TEXT NOT NULL,
                status TEXT NOT NULL,
                totalAmount REAL,
                vendorId INTEGER NOT NULL,
                expectedDeliveryDate TEXT,
                shippingMethod TEXT,
                FOREIGN KEY (vendorId) REFERENCES vendor(vendorId)
            );
        """;

        stmt.execute(workOrder);
        stmt.execute(salesOrder);
        stmt.execute(invoice);
        stmt.execute(purchaseOrder);

        System.out.println("\tTransaction tables ready.\n");
    }

    // ========== RESET DATABASE ==========
    public static void reset(Boolean isManager) {
        try {
            File dbFile = new File(DB_FILE);
            if (dbFile.exists()) {
                if (dbFile.delete()) {
                    System.out.println("Database file deleted.");
                }
            }

            initializeDatabase(isManager);
            System.out.println("Database reset and initialized.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========== GET CONNECTION ==========
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
