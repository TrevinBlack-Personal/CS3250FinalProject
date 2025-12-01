/* =======================================
 * Substantiates the pane for searching the database
 * 
 * Methods:
 *      setOnSearch - Call back function for when user initiates a search
 * =======================================
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.function.BiConsumer;

public class SearchInventory extends BorderPane {

    private TextField searchField;
    private ComboBox<String> tableSelector;

    // Use BiConsumer so we can pass both table name and search term
    private BiConsumer<String, String> onSearch;

    public SearchInventory(Boolean isManager) {
        setPadding(new Insets(20));

        GridPane searchPane = new GridPane();
        searchPane.setVgap(15);
        searchPane.setHgap(10);
        searchPane.setAlignment(Pos.TOP_CENTER);

        // Search label & combo box
        Label searchLabel = new Label("Search By:");
        searchLabel.setStyle("-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #ffffff;");

        tableSelector = new ComboBox<>();
        loadRoleTables(isManager);
        tableSelector.setValue(null);

        // Search text field
        searchField = new TextField();
        searchField.setPromptText("Type Here");

        // Buttons
        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear");

        searchButton.setStyle(
            "-fx-background-color: #27ae60;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 5;" +
            "-fx-cursor: hand;"
        );

        clearButton.setStyle(
            "-fx-background-color: #e74c3c;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 5;" +
            "-fx-cursor: hand;"
        );

        HBox buttonBox = new HBox(10, searchButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        searchPane.add(searchLabel, 0, 0);
        searchPane.add(tableSelector, 0, 1);
        searchPane.add(searchField, 0, 2);
        searchPane.add(buttonBox, 0, 3);

        setCenter(searchPane);

        // ------------------ Button Actions ------------------
        searchButton.setOnAction(e -> {
            String tableName = tableSelector.getValue();
            String searchTerm = searchField.getText();

            if (tableName != null && onSearch != null) {
                switch (tableName) {
                    case "maintenance" -> tableName = "maintenancelog";
                    case "part" -> tableName = "spareparts";
                    case "purchase" -> tableName = "purchaseorder";
                    case "sales" -> tableName = "salesorder";
                    case "work order" -> tableName = "workorder";
                    case "invoice number" -> tableName = "invoice";
                }

                
                onSearch.accept(tableName, searchTerm);
            }
        });

        clearButton.setOnAction(e -> searchField.clear());
    }

    // Setter for callback
    public void setOnSearch(BiConsumer<String, String> callback) {
        this.onSearch = callback;
    }
    
    private void loadRoleTables(Boolean isManager) {

        if (isManager) {
            tableSelector.getItems().addAll(
                    "customer", "downtime", "employee", "equipment",
                    "fuel", "inspections", "invoice number", "maintenance",
                    "manager", "part", "purchase", "sales",
                    "schedule", "users", "vendor", "work order"
                );
            
            return;
        }
        
        tableSelector.getItems().addAll(
                "customer", "equipment",
                "fuel", "invoice number",
                "part", "purchase", "sales", 
                 "vendor", "work order"
            );
        
        return;
        
    }

}
