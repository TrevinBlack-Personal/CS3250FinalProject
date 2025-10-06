import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVSeeder {

    // Seed any table with CSV (columns must match order of INSERT)
    public static void seedTable(String csvFile, String insertSQL, int columnCount) {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue; // skip comments/empty lines
                if (skipHeader) { skipHeader = false; continue; }

                String[] parts = line.split(",");

                for (int i = 0; i < columnCount; i++) {
                    pstmt.setString(i + 1, parts[i].trim());
                }

                pstmt.executeUpdate();
            }

            System.out.println("Table seeded from " + csvFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
