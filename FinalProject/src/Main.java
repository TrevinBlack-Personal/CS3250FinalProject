/* =======================================
 * Main Method for initializing project
 * =======================================
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Welcome to my final project!");

        // Initialize database once when app starts

        // TO DO : Modify Seeder so that it loads once the person logs in. Based on user/ guest / or manager
        //SeederMain.runSeeder();

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
