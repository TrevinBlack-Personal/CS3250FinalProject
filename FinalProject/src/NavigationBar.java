	
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class NavigationBar extends HBox {
	public NavigationBar(String Username) {
		Label welcomeLabel = new Label("Welcome, " + Username + "!");
		welcomeLabel.setStyle("-fx-padding:20;");
		getChildren().add(welcomeLabel);
		
		// Radio buttons 
        RadioButton searchBtn = new RadioButton("Search");
        RadioButton addBtn = new RadioButton("Add");
        RadioButton deleteBtn = new RadioButton("Delete");

        // Toggle group
        ToggleGroup crudButtons = new ToggleGroup();
        searchBtn.setToggleGroup(crudButtons);
        addBtn.setToggleGroup(crudButtons);
        deleteBtn.setToggleGroup(crudButtons);

        searchBtn.setStyle(
        	    "-fx-background-color: #3498db;" +
        	    "-fx-text-fill: white;" +
        	    "-fx-background-radius: 5;" +
        	    "-fx-padding: 8 15 8 15;" +
        	    "-fx-border-color: transparent;" +
        	    "-fx-cursor: hand;" +
        	    "-fx-shape: null;" 
        	);      
        addBtn.setStyle(
        	    "-fx-background-color: #27ae60;" +
        	    "-fx-text-fill: white;" +
        	    "-fx-background-radius: 5;" +
        	    "-fx-padding: 8 15 8 15;" +
        	    "-fx-border-color: transparent;" +
        	    "-fx-cursor: hand;" +
        	    "-fx-shape: null;" 
        	);
        deleteBtn.setStyle(
        	    "-fx-background-color: #e74c3c;" +
        	    "-fx-text-fill: white;" +
        	    "-fx-background-radius: 5;" +
        	    "-fx-padding: 8 15 8 15;" +
        	    "-fx-border-color: transparent;" +
        	    "-fx-cursor: hand;" +
        	    "-fx-shape: null;" 
        	);
        // add everything
        getChildren().addAll(searchBtn, addBtn, deleteBtn);
        HBox.setMargin(welcomeLabel, new Insets(0,50,0,0));
        HBox.setMargin(searchBtn, new Insets(0,10,0,0));
        HBox.setMargin(addBtn, new Insets(0,10,0,0));

	}
}

