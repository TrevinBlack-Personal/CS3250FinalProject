import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.function.BiConsumer; 

public class SearchInventory extends BorderPane {

    private ComboBox<String> searchOptions;
    private TextField searchField;

    // Callback to send search term and option back to MainMenuPane
    private BiConsumer<String, String> onSearch;
    

    public SearchInventory() {
        setPadding(new Insets(20));

        GridPane searchPane = new GridPane();
        searchPane.setVgap(15);
        searchPane.setHgap(10);
        searchPane.setAlignment(Pos.TOP_CENTER);

        // Search label & combo box. Has an option so you can retrieve a record from every table
        Label searchLabel = new Label("Search By:");
        searchOptions = new ComboBox<>();
        
        searchOptions.getItems().addAll(
        	    "customer id",
        	    "downtime log id",
        	    "employee id",
        	    "employee name",
        	    "equipment name",
        	    "fuel type",
        	    "inspection id",
        	    "invoice order id",
        	    "maintenance record id",
        	    "manager id",
        	    "part name",
        	    "purchase order id",
        	    "sales order id",
        	    "schedule id",
        	    "vendor id",
        	    "work order id"
        	);
 
        searchOptions.setValue(null);

        // Search box: Holds the value of what the user may want to search for
        searchField = new TextField();
        searchField.setPromptText("Type Here"); 

        // Buttons
        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear");

        // Button box for horizontal alignment
        HBox buttonBox = new HBox(10, searchButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add all items 
        searchPane.add(searchLabel, 0, 0);
        searchPane.add(searchOptions, 0, 1);
        searchPane.add(searchField, 0, 2);
        searchPane.add(buttonBox, 0, 3);

        setCenter(searchPane);

        // ------------------ Button Actions ------------------
        // Call back function to pass the values back to MainMenuPane to the results pane can properly update
        searchButton.setOnAction(e -> {
            String term = searchField.getText();
            String option = searchOptions.getValue();
            if (onSearch != null) {
                onSearch.accept(term, option); // Trigger the callback
            }
        });

        clearButton.setOnAction(e -> searchField.clear());
    }

    // Setter for the callback - on search
    public void setOnSearch(BiConsumer<String, String> callback) {
        this.onSearch = callback;
    }
    

}
