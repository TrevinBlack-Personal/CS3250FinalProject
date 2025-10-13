import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultsPane extends BorderPane {

    private TableView<ObservableList<String>> tableView;

    @SuppressWarnings("deprecation")
	public ResultsPane() {
    	// Create table for data viewing
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setCenter(tableView);
    };
    
    String sql = "";
    

    public void displayResults(String searchOption, String searchTerm, Object... params) {
        clear();

        // Retrieve SQL query based on searchOption & searchTerm
        // Args:
        	//String searchOption = what was selected from drop down menu in searchInventory
        	//String searchTerm = What user typed to search for within database Ex: "John", "EMP001
        String sql = retrieveSql.getQuery(searchOption, searchTerm);

     /* Not my proudest work.. most of this code was generated with Chat GPT.
      * I was able to get to a point where I could connect to the DB & get data into the cells
      * However, formatting was a nightmare. First I could only show one record, then the first column pushed everything 
      * else off screen. So I had enough and used chat gpt to format the cells & properly update table based on what was retrieved from the SQL statement dynamically
        */
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Create columns dynamically
            for (int i = 1; i <= columnCount; i++) {
                final int colIndex = i - 1;
                TableColumn<ObservableList<String>, String> col =
                    new TableColumn<>(meta.getColumnLabel(i));

                col.setCellValueFactory(cellData -> {
                    String value = cellData.getValue().get(colIndex);
                    return new javafx.beans.property.SimpleStringProperty(value);
                });

                tableView.getColumns().add(col);
            }

            // Evenly size columns
            double totalWidth = tableView.getWidth() > 0 ? tableView.getWidth() : 600;
            double colWidth = totalWidth / columnCount;
            for (TableColumn<ObservableList<String>, ?> col : tableView.getColumns()) {
                col.setPrefWidth(colWidth);
            }

            // Fill rows
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i) == null ? "" : rs.getString(i));
                }
                data.add(row);
            }

            tableView.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Clears table view
    public void clear() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }
}
