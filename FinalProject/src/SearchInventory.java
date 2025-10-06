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

        // Search label
        Label searchLabel = new Label("Search By:");
        searchOptions = new ComboBox<>();
        // Need to re add all search values available but havent written SQL statements
        searchOptions.getItems().addAll("employee name"); 
        searchOptions.setValue("employee name");

        searchField = new TextField();
        searchField.setPromptText("Type John"); // John for database searching

        Button searchButton = new Button("Search");
        Button clearButton = new Button("Clear");

        HBox buttonBox = new HBox(10, searchButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add all items 
        searchPane.add(searchLabel, 0, 0);
        searchPane.add(searchOptions, 0, 1);
        searchPane.add(searchField, 0, 2);
        searchPane.add(buttonBox, 0, 3);

        setCenter(searchPane);

        // ------------------ Button Actions ------------------
        searchButton.setOnAction(e -> {
            String term = searchField.getText();
            String option = searchOptions.getValue();
            if (onSearch != null) {
                onSearch.accept(term, option); // Trigger the callback
            }
        });

        clearButton.setOnAction(e -> searchField.clear());
    }

    // Setter for the callback
    public void setOnSearch(BiConsumer<String, String> callback) {
        this.onSearch = callback;
    }
}
