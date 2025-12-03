/* =======================================
 * Insert data into DB using CSV files
 * 
 * Args:
 *     csvFile		Comma separated file of data
 *     insertSQL 	Insert statement for data
 *     columnCount 	Number of columns in table
 * =======================================
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVSeeder {

    // Seeding table with CSV
    public static void seedTable(String csvFile, String insertSQL, int columnCount) {
    	// Connect to DB
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL); // Insert into table
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) { // Read CSV File

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty())
                	continue; 												 // skip comments/empty lines
                if (skipHeader) { 
                	skipHeader = false; continue;							// skip header AKA column names
                }			 

                String[] parts = line.split(",");							 // split lines on comma

                for (int i = 0; i < columnCount; i++) {
                    pstmt.setString(i + 1, parts[i].trim());
                }

                pstmt.executeUpdate();
            }

            System.out.println("\tTable seeded from " + csvFile);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
