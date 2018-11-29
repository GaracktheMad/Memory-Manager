package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("javadoc")
public class FXMLFix {
	private FXMLLoader f;
	private BorderPane p;

	public FXMLFix() throws IOException {
		f = new FXMLLoader();
		p = new BorderPane();
		f.setRoot(p);
		p = f.load(getClass().getResource("Manager.fxml").openStream());
	}

	public MemoryManagerPane controller() {
		return f.getController();
	}
	
	public BorderPane getPane() {
		return p;
	}

}
