/*=========================================================
 * Class for the changing items in the database
 * Constructor:
 * 		ChangeInventory() - Generates GridPane objects
 * Methods:
 *      loadTableColumns - Dynamically loads text fields based on selected table
 *      setOnTableSelected - Callback function that updates the table in ResultsPane
 * =========================================================
 * */


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChangeInventory extends GridPane {

    private GridPane fieldContainer;
    private ComboBox<String> tableSelector;
    
    private Consumer<String> onTableSelected;					 // Callback function param

    private ArrayList<String> colNames = new ArrayList<>();      // Column names
    private ArrayList<String> userColValues = new ArrayList<>(); // User input values
    private Map<String, TextField> textFields = new HashMap<>(); // Map: columnName → TextField

    private String currentTableName = null;

    public ChangeInventory(boolean isManager) {
        setVgap(15);
        setPadding(new Insets(20));

        Label title = new Label("Change Record");
        title.setStyle("""
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-text-fill: #ffffff;
        """);

        // Table Selection drop down menu
        tableSelector = new ComboBox<>();
        loadRoleTables(isManager);
        tableSelector.setValue(null);

        // Buttons
        Button changeButton = new Button("Change");
        Button clearButton = new Button("Clear");

        // Button styles. CSS Generated with CHAT GPT
        changeButton.setStyle(
        	    "-fx-background-color: #702963;" +   
        	    "-fx-text-fill: white;" +           
        	    "-fx-font-size: 10px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-background-radius: 8;" +
        	    "-fx-padding: 5;"
        	    + "-fx-cursor: hand;" );

        clearButton.setStyle(        	    
        		"-fx-background-color: #e74c3c;" +   
        	    "-fx-text-fill: white;" +           
        	    "-fx-font-size: 10px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-background-radius: 8;" +       
        	    "-fx-padding: 5;"
        	    + "-fx-cursor: hand;" );

        // HBOX for Alignment
        HBox buttons = new HBox(10, changeButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        fieldContainer = new GridPane();
        fieldContainer.setVgap(8);
        fieldContainer.setHgap(10);
        fieldContainer.setPadding(new Insets(10, 0, 0, 0));

        // When user selects a table
        tableSelector.setOnAction(e -> {

            String tableName = tableSelector.getValue();
            
            // Switch for formatting unsavory table names. Should be its own function
            if (tableName != null) {
            	
                switch (tableName) {
                case "maintenance" -> tableName = "maintenancelog";
                case "part" -> tableName = "spareparts";
                case "purchase" -> tableName = "purchaseorder";
                case "sales" -> tableName = "salesorder";
                case "work order" -> tableName = "workorder";
                case "invoice number" -> tableName = "invoice";
            }
                
                currentTableName = tableName;
                loadTableColumns(tableName);			// Load text fields of columns in table selected

                if (onTableSelected != null) {
                    onTableSelected.accept(tableName); // Update callback param
                }
            }
        });

        // Add button listener
        changeButton.setOnAction(e -> {
            if (currentTableName == null || textFields.isEmpty()) {
                System.err.println("No table selected or no fields loaded.");
                return;
            }

            userColValues.clear();
            
            // For each column field get the user entered data from text field
            for (String colName : colNames) {
                TextField tf = textFields.get(colName);
                if (tf != null) {
                    userColValues.add(tf.getText().trim());		// Append user entered values to ArrayList
                }
            }

            ChangeSQL.executeUpdate(currentTableName, colNames, userColValues);
            loadTableColumns(currentTableName);
            onTableSelected.accept(currentTableName);

        });

        // Clear button resets all text fields
        clearButton.setOnAction(e -> {
            textFields.values().forEach(tf -> tf.clear());
        });

        // Add all items
        add(title, 0, 0);
        add(tableSelector, 0, 1);
        add(fieldContainer, 0, 2);
        add(buttons, 0, 3);
    }

    // Dynamically load text fields for SQL table columns
    private void loadTableColumns(String tableName) {
        fieldContainer.getChildren().clear();
        textFields.clear();
        colNames.clear();

        // Switch for formatting unsavory table names. Should be its own function
        switch (tableName) {
            case "maintenance" -> tableName = "maintenancelog";
            case "part" -> tableName = "spareparts";
            case "purchase" -> tableName = "purchaseorder";
            case "sales" -> tableName = "salesorder";
            case "work order" -> tableName = "workorder";
            case "invoice number" -> tableName = "invoice";
        }

        // Connect to DB
        try (Connection conn = Database.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(null, null, tableName, null); // Get columns of selected table

            int row = 0;
            
            // Create a text field for each column name in selected table
            while (rs.next()) {
                String colName = rs.getString("COLUMN_NAME");
                colNames.add(colName);

                TextField tf = new TextField();
                tf.setPromptText("Enter " + colName);
                
                fieldContainer.add(tf, 1, row);
                textFields.put(colName, tf);

                row++;
            }

        } catch (SQLException ex) {
            Label error = new Label("Error loading columns: " + ex.getMessage());
            error.setStyle("-fx-text-fill: red;");
            fieldContainer.add(error, 0, 0);
        }
    }

    // Callback function for when a table is selected from drop down menu
    public void setOnTableSelected(Consumer<String> callback) {
        this.onTableSelected = callback;
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
