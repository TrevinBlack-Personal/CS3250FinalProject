import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NavigationBar extends HBox {

    public NavigationBar(String username) {
        // Welcome label
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-padding:20;");
        HBox.setMargin(welcomeLabel, new Insets(0, 50, 0, 0));

        // Radio buttons
        RadioButton searchBtn = new RadioButton("Search");
        RadioButton addBtn = new RadioButton("Add");
        RadioButton deleteBtn = new RadioButton("Delete");

        // Assign userData for logic
        searchBtn.setUserData("Search");
        addBtn.setUserData("Add");
        deleteBtn.setUserData("Delete");

        // Toggle group
        ToggleGroup crudButtons = new ToggleGroup();
        searchBtn.setToggleGroup(crudButtons);
        addBtn.setToggleGroup(crudButtons);
        deleteBtn.setToggleGroup(crudButtons);

        // Styles - styling generated using Chat GPT
        String buttonStyle = """
            -fx-text-fill: white;
            -fx-background-radius: 5;
            -fx-padding: 8 15 8 15;
            -fx-border-color: transparent;
            -fx-cursor: hand;
        """;
        searchBtn.setStyle("-fx-background-color: #3498db;" + buttonStyle);
        addBtn.setStyle("-fx-background-color: #27ae60;" + buttonStyle);
        deleteBtn.setStyle("-fx-background-color: #e74c3c;" + buttonStyle);

        // Margins
        HBox.setMargin(searchBtn, new Insets(0, 10, 0, 0));
        HBox.setMargin(addBtn, new Insets(0, 10, 0, 0));

        // Add components
        getChildren().addAll(welcomeLabel, searchBtn, addBtn, deleteBtn);

        // Scene switch listener
        crudButtons.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                String menuType = newToggle.getUserData().toString(); // Grabs "Search" , "Add", || "Delete" from user data for indexing what new page to open
                switchScene(menuType, username);
            }
        });
    }

    // Switch scenes based on button pressed - Search, Add, Delete
    private void switchScene(String menuType, String username) {
        // Get the current stage from any node in NavigationBar
        Stage stage = (Stage) getScene().getWindow();

        // Replace the scene
        Scene scene = new Scene(new MainMenuPane(username, menuType), 500, 500);
        stage.setScene(scene);
        stage.setTitle(menuType);
    }

}
