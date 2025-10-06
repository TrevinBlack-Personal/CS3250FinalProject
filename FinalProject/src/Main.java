import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Welcome to my final project!");

        // Initialize database once when app starts

        SeederMain.runSeeder();

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene loginScene = new Scene(new Login(), 500, 500);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Inventory System");
        primaryStage.show();
    }
}
