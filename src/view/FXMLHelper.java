package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Simplifies the FXML loading process of the manager pane, and allows this FXML
 * document to be loaded by other packages
 * 
 * @author Peter Vukas
 *
 */
public class FXMLHelper {
	private FXMLLoader f;
	private BorderPane p;

	/**
	 * Creates an FXMLLoader and attaches it to a borderpane.
	 * 
	 * @throws IOException Thrown if the FXML file has been deleted.
	 */
	public FXMLHelper() throws IOException {
		f = new FXMLLoader();
		p = new BorderPane();
		f.setRoot(p);
		p = f.load(getClass().getResource("Manager.fxml").openStream());
	}

	/**
	 * Retrieves the controller from the FXMLLoader
	 * 
	 * @return The controller associated with this FXML file
	 */
	public MemoryManagerPane getController() {
		return f.getController();
	}

	/**
	 * @return The Borderpane generated by the Manager.fxml
	 */
	public BorderPane getPane() {
		return p;
	}

}