import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ResultsPane extends BorderPane {
	public ResultsPane() {
		// This is where I will display the results of the search, add, or delete. Right now just a text
		// field. however I might need to change this to some sort of row / column system to better accommodate
		// a data system
		
		TextArea resultsField = new TextArea();
		setCenter(resultsField);
		resultsField.setEditable(false);
	}
}
