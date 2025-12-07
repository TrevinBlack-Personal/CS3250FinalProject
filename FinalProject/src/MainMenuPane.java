/* =======================================
 * Parent class for the gridpane. Sets top, bottom, left, right, center
 * =======================================
 */

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainMenuPane extends BorderPane {

    public MainMenuPane(String Username, String menuType, Boolean isManager) {
        // Top Pane - navigation
        NavigationBar navigationBar = new NavigationBar(Username, isManager);
        navigationBar.setAlignment(Pos.BASELINE_CENTER);
        navigationBar.setPrefHeight(25);
        setTop(navigationBar);

        // Center Pane - results
        ResultsPane resultsPane = new ResultsPane();
        resultsPane.setStyle("-fx-background-color:#4682B4; -fx-border-color:black");
        setCenter(resultsPane);

        // Left Pane - menu selection
        switch (menuType) {
            case "Search" -> {
                SearchInventory searchInventory = new SearchInventory(isManager);
                searchInventory.setStyle("-fx-background-color:#4682B4; -fx-border-color:black");
                searchInventory.setPrefWidth(150);
                setLeft(searchInventory);

                // Connect callback to ResultsPane for search property
                searchInventory.setOnSearch((tableName, searchTerm) -> {
                    resultsPane.displayResults(tableName, searchTerm);
                });

            }
            case "Add" -> {
                AddInventory addInventory = new AddInventory(isManager);
                addInventory.setStyle("-fx-background-color:#4682B4; -fx-border-color:black");
                addInventory.setPrefWidth(150);
                setLeft(addInventory);
                addInventory.setAlignment(Pos.BASELINE_CENTER);
                
                addInventory.setOnTableSelected(tableName -> {
                    resultsPane.displayResults(tableName, null);  // no search term, just show all rows
                });
                
            }
            case "Delete" -> {
                DeleteInventory deleteInventory = new DeleteInventory(isManager);
                deleteInventory.setStyle("-fx-background-color:#4682B4; -fx-border-color:black");
                deleteInventory.setPrefWidth(150);
                setLeft(deleteInventory);
                deleteInventory.setAlignment(Pos.BASELINE_CENTER);
                
                deleteInventory.setOnTableSelected(tableName -> {
                	resultsPane.displayResults(tableName, null);
                });
                
                deleteInventory.setOnDeletePressed(() -> {
                    resultsPane.deleteSelectedRow();
                });
            }
            case "Change" -> {
            	ChangeInventory changeInventory = new ChangeInventory(isManager);
                changeInventory.setStyle("-fx-background-color:#4682B4; -fx-border-color:black");
                changeInventory.setPrefWidth(150);
                setLeft(changeInventory);
                changeInventory.setAlignment(Pos.BASELINE_CENTER);
                
                changeInventory.setOnTableSelected(tableName -> {
                	resultsPane.displayResults(tableName, null);
                });
            }
        }

        // Bottom Pane
        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color:white;");
        bottomPane.setPrefHeight(25);
        setBottom(bottomPane);
    }
}
