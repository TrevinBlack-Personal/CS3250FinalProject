
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends BorderPane {
	public Login() {
		
		// Top Pane
		VBox topPane = new VBox();
		topPane.setPrefHeight(50);
		
		Image logo = new Image("Mining Company Logo.png");
		ImageView logoView = new ImageView(logo);
		logoView.setFitHeight(200);
		logoView.setFitWidth(200);
		topPane.setAlignment(Pos.BASELINE_CENTER);
		topPane.getChildren().add(logoView);
		
		setTop(topPane);
		
		// Empty panes for spacing
		// Left Pane
		Pane leftPane = new Pane();
		leftPane.setPrefWidth(50);
		setLeft(leftPane);
		
		// Right Pane
		Pane rightPane = new Pane();
		rightPane.setPrefWidth(50);
		setRight(rightPane);
		
		// Center pane. Text boxes for username & password
		VBox textFieldPane = new VBox();
		textFieldPane.setAlignment(Pos.CENTER);
		
		TextField usernameText = new TextField();
		usernameText.setPromptText("Username");
		usernameText.setStyle("-fx-max-width:400;");
		
		TextField passwordText = new TextField();
		passwordText.setPromptText("Password");
		passwordText.setStyle("-fx-max-width:400;");


		textFieldPane.getChildren().addAll(usernameText, passwordText);

		// Bottom Pane for login buttons
		HBox buttonPane = new HBox();
		Button loginButton = new Button("Login");

		Button guestButton = new Button("Login as Guest");
		buttonPane.getChildren().addAll(loginButton, guestButton);
		buttonPane.setAlignment(Pos.TOP_CENTER);
		buttonPane.setPrefHeight(75);
		
		// Temp method call
		guestButton.setOnAction(e -> openMainMenu());
		
		setCenter(textFieldPane);
		setBottom(buttonPane);


	}

	// Temporary Method for opening a new window
	private void openMainMenu() {
	    Stage mainMenu = new Stage();  // Create a new window
	    Scene scene = new Scene(new MainMenuPane("Guest", "Search"), 500, 500); // Create the scene with your pane
	    
	    mainMenu.setScene(scene);
	    mainMenu.setTitle("Main Menu");
	    mainMenu.show();
	    
	    Stage mainMenu2 = new Stage();  // Create a new window
	    Scene scene2 = new Scene(new MainMenuPane("Guest", "Add"), 500, 500); // Create the scene with your pane
	    
	    mainMenu2.setScene(scene2);
	    mainMenu2.setTitle("Main Menu");
	    mainMenu2.show();
	    
	    Stage mainMenu3 = new Stage();  // Create a new window
	    Scene scene3 = new Scene(new MainMenuPane("Guest", "Delete"), 500, 500); // Create the scene with your pane
	    
	    mainMenu3.setScene(scene3);
	    mainMenu3.setTitle("Main Menu");
	    mainMenu3.show();
	    
	    
	}

}
