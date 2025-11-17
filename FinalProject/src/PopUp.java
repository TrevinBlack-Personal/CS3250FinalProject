import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PopUp extends VBox{
	private String content = "";
	
	public void PopUp(String content) {
		this.content = content;
				
		Label contentLabel = new Label(content);
		
		HBox buttonBox = new HBox();

	}
	

}
