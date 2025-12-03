import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProgressWindow {

    private static Stage stage;
    private static ProgressBar progressBar;
    private static Label percentLabel;

    // Create and show the window
    public static void show(String title) {
        Platform.runLater(() -> {
            progressBar = new ProgressBar(0);
            percentLabel = new Label("0%");

            VBox root = new VBox(10, new Label(title), progressBar, percentLabel);
            root.setStyle("-fx-padding: 20;");

            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        });
    }

    public static void update(double tableNumber, double numberOfTables) {
        Platform.runLater(() -> {
            if (progressBar != null) {

                double progress = tableNumber / numberOfTables;

                progressBar.setProgress(progress);
                percentLabel.setText((int) (progress * 100) + "%");
            }
        });
    }


    // Close window
    public static void close() {
        Platform.runLater(() -> {
            if (stage != null) stage.close();
        });
    }
}
