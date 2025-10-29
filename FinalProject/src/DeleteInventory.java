/*=======================================
 *  Class for interaction in deleting rows from the database
 *  
 *  Constructor: 
 *  	DeleteInventory() 	sets up the objects on this pane
 *  
 *  Methods:
 *  	setOnTableSelected() 	Callback function for when table is selected from drop down menu
 *  	setOnDeletePressed() 	Callback function for when deleting a row 
 * =======================================
 */

import java.util.function.Consumer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DeleteInventory extends GridPane {
	
    private Consumer<String> onTableSelected; 	// Callback variable for table selection
    private Runnable onDeletePressed; 			// Callback variable for when deleting a row

	public DeleteInventory() {
		
		// Set gap between rows 15 pixels
		setVgap(15);
		
		// Label for dropdown menu
		Label searchViaLabel = new Label("Delete Record");
		searchViaLabel.setStyle(
		    "-fx-font-size: 18px;" +
		    "-fx-font-weight: bold;" +
		    "-fx-text-fill: #ffffff;"		);

        // Dropdown menu
        ComboBox<String> tableSelector = new ComboBox<>();
        tableSelector.getItems().addAll(
                "customer", "downtime", "employee", "equipment",
                "fuel", "inspections", "invoice number", "maintenance",
                "manager", "part", "purchase",
                "sales", "schedule", "users", "vendor", "work order"
            );
        
        
        // Search button
        Button deleteButton = new Button("Delete");
        
        // Create new HBox so buttons can be next to eachother
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        
        // Generated with Chat GPT
        // Prompt: can you generate the javafx css for two buttons. button names are deleteButton & clearButton
        deleteButton.setStyle(
        	    "-fx-background-color: #e74c3c;" +   // blue background
        	    "-fx-text-fill: white;" +           // white text
        	    "-fx-font-size: 10px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-background-radius: 8;" +       // rounded corners
        	    "-fx-padding: 8 20 8 20;"
        	    + "-fx-cursor: hand;"           
        	);
        
        // set margin to allow for space between buttons
        HBox.setMargin(deleteButton, new Insets(0,10 ,0,0));
        layout.getChildren().add(deleteButton);
        
        // Add all the elements
        add(searchViaLabel, 0, 0);
        add(tableSelector, 0, 1);
        add(layout, 0,3);
        
        // Updates table view when user selects a table via callback
        tableSelector.setOnAction(e -> {
            String tableName = tableSelector.getValue();
            if (tableName != null) {
                if (onTableSelected != null) {
                    onTableSelected.accept(tableName);
                }
            }
        });
        
        // delete button event listener 
        deleteButton.setOnAction(e ->{
            if (onDeletePressed != null) {
                onDeletePressed.run();
            }
        });
	}
	// on table selection issue callback - updates tableview
	public void setOnTableSelected(Consumer<String> callback) {
	    this.onTableSelected = callback;
	}
	
	// on delete press issue callback - updates tableview for deleting a row
    public void setOnDeletePressed(Runnable callback) {
        this.onDeletePressed = callback;
    }
}




