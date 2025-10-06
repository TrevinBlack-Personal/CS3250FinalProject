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
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setCenter(tableView);
    };
    
    String sql = "";
    

    // Executes SQL statement and displays in the table view
    public void displayResults(String searchOption, Object... params) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        
        // Only SQL statement available 
        if ("employee name" == searchOption) {
            sql = "SELECT e.employeeId, u.name AS employeeName, u.email, e.department, e.salary " +
                  "FROM employee e JOIN users u ON e.userId = u.id " +
                  "WHERE u.name LIKE ?";
        }

            
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Create table columns dynamically - generated with chat gpt
            // Prompt: Can you help me dynamically generate columns and rows for the TableView so that it fits the values of whatever is returned from the SQL statement
            for (int i = 1; i <= columnCount; i++) {
                final int colIndex = i - 1;
                TableColumn<ObservableList<String>, String> col =
                        new TableColumn<>(meta.getColumnName(i));
                col.setCellValueFactory(cellData -> {
                    String value = cellData.getValue().get(colIndex);
                    return new javafx.beans.property.SimpleStringProperty(value);
                });
                tableView.getColumns().add(col);
            }

            // Add rows
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
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
