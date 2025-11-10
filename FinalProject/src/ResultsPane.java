/* =======================================
 * Method for substantiating data in a viewable way via TableView
 * 
 * Methods:
 *  	displayResults()		Displays data via a TableView
 *  	deleteSelectedRow()		Deletes a row from the table & database
 * Args:
 * 		searchOption - Table selected by user
 * 		searchTerm - What user typed / wants to search by
 * =======================================
 */

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
    private String currentTableName;   

    public ResultsPane() {
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setCenter(tableView);
    }

    public void displayResults(String searchOption, String searchTerm, Object... params) {
        clear();

        // Save for later use in deleteSelectedRow()
        this.currentTableName = searchOption;

        // Get query for this table
        String sql = retrieveSql.getQuery(searchOption, searchTerm);

        System.out.println(sql);
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Create columns dynamically - Generated with chat GPT
            // Prompt: How can I update columns dynamically in a TableView and evenly space the columns
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
            System.out.println("Results found: " + data.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clears table view
    public void clear() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }

    // Delete row based on selection
    public void deleteSelectedRow() {
        ObservableList<String> selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No row selected to delete");
            return;
        }

        if (currentTableName == null) {
            System.out.println("No table currently loaded");
            return;
        }

        String pkValue = selected.get(0); // Primary Key value
        String pkColumn = tableView.getColumns().get(0).getText(); // Primary key column


        DeleteSql.executeDelete(currentTableName, pkValue, pkColumn);
        tableView.getItems().remove(selected); // remove from UI
    }
}
