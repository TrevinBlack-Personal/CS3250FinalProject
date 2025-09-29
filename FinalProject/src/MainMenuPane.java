
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainMenuPane extends BorderPane{
	
	
	public MainMenuPane(String Username, String menuType) {
			// top pane - navigation
			NavigationBar navigationBar = new NavigationBar(Username);
			navigationBar.setAlignment(Pos.BASELINE_CENTER);
			navigationBar.setPrefHeight(25);
			setTop(navigationBar);
			
			// Left Pane - search functions
			// Eventually add a function here to change the left pane based on whats selected in navigation
			if (menuType == "Search") {
				SearchInventory searchInventory = new SearchInventory();
				searchInventory.setStyle("-fx-background-color:#4682B4;" + "-fx-border-color:black");
				searchInventory.setPrefWidth(150);
				setLeft(searchInventory);
				searchInventory.setAlignment(Pos.BASELINE_CENTER);
			}
			else if (menuType == "Add") {
				AddInventory addInventory = new AddInventory();
				addInventory.setStyle("-fx-background-color:#4682B4;" + "-fx-border-color:black");
				addInventory.setPrefWidth(150);
				setLeft(addInventory);
				addInventory.setAlignment(Pos.BASELINE_CENTER);
			}
			else if (menuType == "Delete") {
				DeleteInventory deleteInventory = new DeleteInventory();
				deleteInventory.setStyle("-fx-background-color:#4682B4;" + "-fx-border-color:black");
				deleteInventory.setPrefWidth(150);
				setLeft(deleteInventory);
				deleteInventory.setAlignment(Pos.BASELINE_CENTER);
			}
			
			// Center Pane
			BorderPane resultsPane = new ResultsPane();
			resultsPane.setStyle("-fx-background-color:#4682B4;"+ "-fx-border-color:black");
			setCenter(resultsPane);
			
			// Bottom Pane
			Pane bottomPane = new Pane();
			bottomPane.setStyle("-fx-background-color:white;");
			bottomPane.setPrefHeight(25);
			setBottom(bottomPane);
	}
}
