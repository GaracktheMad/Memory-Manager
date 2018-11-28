package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author Peter Vukas
 *
 */
public class MemoryManagerPane extends BorderPane {

	@FXML
	private VBox memVBox;
	@FXML
	private ComboBox<String> pcmbBox;
	@FXML
	private ComboBox<String> algorithmCmbBox;
	@FXML
	private Button addBtn;
	@FXML
	private Button compactionBtn;
	@FXML
	private Button removeBtn;
	@FXML
	private TextField sizeBox;
	@FXML
	private Label statusLbl;

	/**
	 * Creates an FXML loader of the Manager.FXML
	 * 
	 * @return The created loader
	 */
	public static FXMLLoader constructable() {
		return new FXMLLoader(MemoryManagerPane.class.getResource("Manager.fxml"));
	}

	@FXML
	void initialize() {
		assert memVBox != null : "fx:id=\"memVBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert pcmbBox != null : "fx:id=\"pcmbBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert algorithmCmbBox != null : "fx:id=\"algorithmCmbBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert compactionBtn != null : "fx:id=\"compactionBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert removeBtn != null : "fx:id=\"removeBtn\" was not injected: check your FXML file 'Manager.fxml'.";

		algorithmCmbBox.getItems().add("First Fit");
		algorithmCmbBox.getItems().add("Best Fit");
		algorithmCmbBox.getItems().add("Worst Fit");
		populateProcessList(10);
	}

	/**
	 * Makes the alert label invisible. Does not wipe current data.
	 */
	public void hideAlert() {
		statusLbl.setVisible(false);
	}

	/**
	 * Reveals a message on the screen in bold underlined red to the user
	 * 
	 * @param message String to be displayed
	 */
	public void setAlert(String message) {
		statusLbl.setVisible(true);
		statusLbl.setText(message);
	}

	/**
	 * Disables or enables the add button
	 * 
	 * @param isDisable If true, the button will be disabled
	 */
	public void setDisableAddBtn(boolean isDisable) {
		addBtn.setDisable(isDisable);
	}

	/**
	 * Set the text to be displayed in the size box.
	 * 
	 * @param size The text to be displayed. If this value is null, the box will be
	 *             cleared.
	 */
	public void setSizeText(String size) {
		if (size == null) {
			sizeBox.clear();
		} else {
			sizeBox.setText(size);
		}
	}

	/**
	 * Disables or enables the remove button
	 * 
	 * @param isDisable If true, the button will be disabled
	 */
	public void setDisableRemoveBtn(boolean isDisable) {
		removeBtn.setDisable(isDisable);
	}

	/**
	 * Gets the disabled state of the add button
	 * 
	 * @return True if the button is disabled
	 */
	public boolean addIsDisable() {
		return addBtn.isDisable();
	}

	/**
	 * Gets the disabled state of the remove button
	 * 
	 * @return True if the button is disabled
	 */
	public boolean removeIsDisable() {
		return removeBtn.isDisable();
	}

	/**
	 * Adds action events to the three buttons displayed on this pane.
	 * 
	 * @param handleRun     Action event which will be performed when the button
	 *                      labeled "Add" is clicked
	 * @param handleCompact Action event which will be performed when the button
	 *                      labeled "Compact" is clicked
	 * @param handleRemove  Action event which will be performed when the button
	 *                      labeled "Remove" is clicked
	 */
	public void setOnActions(EventHandler<ActionEvent> handleRun, EventHandler<ActionEvent> handleCompact,
			EventHandler<ActionEvent> handleRemove, EventHandler<ActionEvent> handleProcessSelected) {
		addBtn.setOnAction(handleRun);
		compactionBtn.setOnAction(handleCompact);
		removeBtn.setOnAction(handleRemove);
		pcmbBox.setOnAction(handleProcessSelected);
	}

	/**
	 * Refreshes the memory display with all elements in the array list
	 * 
	 * @param als An array list of Strings of data. Each string should represent the
	 *            data for 1 memory object. The format is comma delimited in the
	 *            order: "Name,StartAddress,Size"
	 */
	public void refreshMemoryDisplay(ArrayList<String> als) {
		memVBox.getChildren().clear();
		for (String s : als) {
			memVBox.getChildren().add(memoryBlock(s));
		}
	}

	/**
	 * @param datum String of data to be contained in this memory block. The format
	 *              is comma delimited in the order: "Name,StartAddress,Size"
	 * @return Formatted rectangle
	 * @author Brandon Ruiz
	 */
	private Rectangle memoryBlock(String datum) {
		Rectangle rectangle = new Rectangle();
		// TODO reformat this code, and possibly change the return type to a border
		// pane, to work correctly
		/*
		 * String[] tokens = datum.split(","); BorderPane bp = new BorderPane();
		 * rectangle.setWidth(20); rectangle.setHeight(Integer.parseInt(tokens[2]));
		 * rectangle.setStroke(Color.BLACK); if (tokens[0] != "Empty Space") {
		 * rectangle.setFill(Color.SILVER); bp.getChildren().addAll(rectangle, new
		 * Text(tokens[0])); } else { rectangle.setFill(null);
		 * sp.getChildren().add(rectangle); } bp.setCenter(sp); Text address = new
		 * Text(tokens[0]); bp.setTop(address); BorderPane.setAlignment(address,
		 * Pos.TOP_LEFT);
		 */
		return rectangle;
	}

	/**
	 * Fills the list combo box with processes using the auto generated names
	 * "Process #" where the # is their index + 1
	 * 
	 * @param number Number of processes to populate the list with
	 */
	public void populateProcessList(int number) {
		pcmbBox.getItems().clear();
		for (int i = 0; i < number; i++) {
			pcmbBox.getItems().add(String.format("Process %2d", i + 1));
		}
	}

	/**
	 * Gets the user selected value in the Process combo box
	 * 
	 * @return The currently selected process
	 */
	public String selectedProcess() {
		return pcmbBox.getValue();
	}

	/**
	 * Gets the user selected value in the Algorithm combo box
	 * 
	 * @return The currently selected algorithm. Values include: "First Fit", "Best
	 *         Fit", "Worst Fit"
	 */
	public String selectedAlgorithm() {
		return algorithmCmbBox.getValue();
	}

	/**
	 * Names in the Process Combo Box are generated by the following method: for(int
	 * i = 0; i < number; i++) { pcmbBox.getItems().add(String.format("Process %2d",
	 * i + 1)); }
	 * 
	 * @return The number of processes in the process combo box
	 */
	public int numberOfProcesses() {
		return pcmbBox.getItems().size();
	}

	/**
	 * Gets the value in the process size box
	 * 
	 * @return Absolute value of the size in the box
	 * @throws NumberFormatException Thrown if an invalid value is given
	 */
	public int getProcessSize() throws NumberFormatException {
		return Math.abs(Integer.valueOf(sizeBox.getText()));
	}
}
