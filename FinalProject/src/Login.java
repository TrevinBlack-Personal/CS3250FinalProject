/* =======================================
 *  Login Class pane. Simple login screen with user validation & guest login
 *  
 *  Constructor:
 *  		Login() 	Sets up the objects for the pane
 *  
 *  Methods:
 *  	openMainMenuGuest() 	Logs into the system using a guest login
 *  	openMainMenuUser() 		Logs into the system using a user login
 * =======================================
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
		VBox.setMargin(logoView, new Insets(50, 0, 0, 0)); 
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
		
		// Center pane. Text boxes for email & password
		VBox textFieldPane = new VBox();
		textFieldPane.setAlignment(Pos.CENTER);
		
		// Email Text Field
		TextField emailText = new TextField();
		emailText.setPromptText("Email");
		emailText.setStyle("-fx-max-width:400;");
		
		// Password Text Field
		PasswordField passwordText = new PasswordField();
		passwordText.setPromptText("Password");
		passwordText.setStyle("-fx-max-width:400;");


		textFieldPane.getChildren().addAll(emailText, passwordText);

		// Bottom Pane for login buttons
		HBox buttonPane = new HBox();
		Button loginButton = new Button("Login");

		Button guestButton = new Button("Login as Guest");
		buttonPane.getChildren().addAll(loginButton, guestButton);
		buttonPane.setAlignment(Pos.TOP_CENTER);
		buttonPane.setPrefHeight(75);
		
		
		// Method call for guest login
		guestButton.setOnAction(e -> openMainMenuGuest());
		
		// Method call for user login
		loginButton.setOnAction(e -> {
			String Email = emailText.getText();
			String Password = passwordText.getText();
			openMainMenuUser(Email, Password);
		});
		
		setCenter(textFieldPane);
		setBottom(buttonPane);


	}

	// Login the guest
	private void openMainMenuGuest() {
		
		SeederMain.runSeeder(false);
		
	    Stage mainMenu = (Stage) getScene().getWindow();
	    Scene scene = new Scene(new MainMenuPane("Guest", "Search", false), 500, 500); 
	    
	    
	    mainMenu.setScene(scene);
	    mainMenu.setTitle("Main Menu");
	    
	}
	
	// Login the user 
	private void openMainMenuUser(String Email, String Password) {
	    String name = loginValidation.validateLogin(Email, Password);
	    Boolean isManager = loginValidation.checkManager(Email, Password);
	    
		if (name != null) {
			
			SeederMain.runSeeder(isManager);
			
			Stage mainMenu = (Stage) getScene().getWindow();
		    Scene scene = new Scene(new MainMenuPane(name, "Search", isManager), 500, 500); 
		    
		    mainMenu.setScene(scene);
		    mainMenu.setTitle("Main Menu");
	    }
		else {
			System.out.println("Error. Login()");
		}
	    
		

	}

}
