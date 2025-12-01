/* =======================================
 * Class for navigation through panes
 * 
 * Constructor:
 * 		NavigationBar 	Sets up objects within pane
 * Args:
 * 		username 	Username of person logged in
 * 
 * Methods:
 * 		SwitchScene() 	Switches the scene based on radio button that is pressed
 * Args:
 * 		menuType 	Add, Search, Delete Strings for switching
 * 		username 	Username of person logged in
 * =======================================
 */

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NavigationBar extends HBox {

    public NavigationBar(String username, Boolean isManager) {
        // Welcome label
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-padding:20;");
        HBox.setMargin(welcomeLabel, new Insets(0, 50, 0, 0));

        // Radio buttons
        RadioButton searchBtn = new RadioButton("Search");
        RadioButton addBtn = new RadioButton("Add");
        RadioButton deleteBtn = new RadioButton("Delete");
        RadioButton changeBtn = new RadioButton("Change");

        // Assign userData for logic
        searchBtn.setUserData("Search");
        addBtn.setUserData("Add");
        deleteBtn.setUserData("Delete");
        changeBtn.setUserData("Change");

        // Toggle group
        ToggleGroup crudButtons = new ToggleGroup();
        searchBtn.setToggleGroup(crudButtons);
        addBtn.setToggleGroup(crudButtons);
        deleteBtn.setToggleGroup(crudButtons);
        changeBtn.setToggleGroup(crudButtons);

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
        changeBtn.setStyle("-fx-background-color: #702963;" + buttonStyle);

        // Margins
        HBox.setMargin(searchBtn, new Insets(0, 10, 0, 0));
        HBox.setMargin(addBtn, new Insets(0, 10, 0, 0));
        HBox.setMargin(deleteBtn, new Insets(0,10,0,0));

        // Add components - re format this for guest vs user login
        if (username != "Guest") {
        	getChildren().addAll(welcomeLabel, searchBtn, addBtn, deleteBtn, changeBtn);
        }
        else {
        	getChildren().addAll(welcomeLabel, searchBtn); // Remove add and delete for guest user
        }
        
        // Scene switch listener
        crudButtons.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                String menuType = newToggle.getUserData().toString(); // Grabs "Search" , "Add", || "Delete" from user data for indexing what new page to open
                switchScene(menuType, username, isManager);
            }
        });
    }

    // Switch scenes based on button pressed - Search, Add, Delete
    private void switchScene(String menuType, String username, Boolean isManager) {
        // Get the current stage from any node in NavigationBar
        Stage stage = (Stage) getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();
        
        // Replace the scene
        Scene scene = new Scene(new MainMenuPane(username, menuType, isManager), width, height);
        stage.setScene(scene);
        stage.setTitle(menuType);
    }

}
