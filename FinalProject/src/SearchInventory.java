import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SearchInventory extends GridPane {
	
	public SearchInventory() {
		
		// Set gap between rows 15 pixels
		setVgap(15);
		
		// Label for dropdown menu
		Label searchViaLabel = new Label("Search Via");
		searchViaLabel.setStyle(
		    "-fx-font-size: 18px;" +
		    "-fx-font-weight: bold;" +
		    "-fx-text-fill: #ffffff;"		);

        // Dropdown menu
        ComboBox<String> searchOptions = new ComboBox<>();
        searchOptions.getItems().addAll(
            "employee name",
            "company name",
            "customer name",
            "employee email",
            "equipment id",
            "equipment name",
            "equipment type",
            "fuel",
            "inspection date",
            "inspectionid",
            "invoice number",
            "maintenance date",
            "order date",
            "order id",
            "part id",
            "part name",
            "record id",
            "sales order",
            "schedule id",
            "vendor id",
            "work order description",
            "workorder date"
        );
        
        // Search field text field
        TextField textField = new TextField();
        textField.setStyle("-fx-margin:20;");
        
        
        // Search and clear button
        Button searchButton = new Button("Search");
        Button clearButton = new Button ("Clear");
        
        // Create new HBox so buttons can be next to eachother
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        
        // Generated with Chat GPT
        // Prompt: can you generate the javafx css for two buttons. button names are searchButton & clearButton
        searchButton.setStyle(
        	    "-fx-background-color: #3498db;" +   // blue background
        	    "-fx-text-fill: white;" +           // white text
        	    "-fx-font-size: 10px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-background-radius: 8;" +       // rounded corners
        	    "-fx-padding: 8 20 8 20;"           // top right bottom left padding
        	);

        	clearButton.setStyle(
        	    "-fx-background-color: #e74c3c;" +  // red background
        	    "-fx-text-fill: white;" +
        	    "-fx-font-size: 10px;" +
        	    "-fx-font-weight: bold;" +
        	    "-fx-background-radius: 8;" +
        	    "-fx-padding: 8 20 8 20;"
        	);
        
        // set margin to allow for space between buttons
        HBox.setMargin(searchButton, new Insets(0,10 ,0,0));
        layout.getChildren().addAll(searchButton, clearButton);
        
        // Add all the elements
        add(searchViaLabel, 0, 0);
        add(searchOptions, 0, 1);
        add(textField, 0, 2);
        add(layout, 0,3);
	}
}
