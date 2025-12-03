import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {

    public static void show(String text) {
        Platform.runLater(() -> {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Message");

            Label label = new Label(text);
            HBox root = new HBox(label);
            root.setSpacing(20);
            root.setStyle("-fx-padding: 20;");

            popupStage.setScene(new Scene(root));
            popupStage.show();
        });
    }
}
