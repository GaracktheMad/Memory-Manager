package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
	private TextField sizeBox;

	@FXML
	private ComboBox<String> algorithmCmbBox;

	@FXML
	private Button addBtn;

	@FXML
	private Button compactionBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private TextField currentSizeTxt;

	@FXML
	private Button resizeBtn;

	@FXML
	private ComboBox<Integer> sizesCmboBox;

	@FXML
	private Button clear;

	@FXML
	private VBox waitList;

	@FXML
	private Label statusLbl;

	@FXML
	void initialize() {
		assert memVBox != null : "fx:id=\"memVBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert pcmbBox != null : "fx:id=\"pcmbBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert sizeBox != null : "fx:id=\"sizeBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert algorithmCmbBox != null : "fx:id=\"algorithmCmbBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert compactionBtn != null : "fx:id=\"compactionBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert removeBtn != null : "fx:id=\"removeBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert currentSizeTxt != null : "fx:id=\"currentSizeTxt\" was not injected: check your FXML file 'Manager.fxml'.";
		assert resizeBtn != null : "fx:id=\"resizeBtn\" was not injected: check your FXML file 'Manager.fxml'.";
		assert sizesCmboBox != null : "fx:id=\"sizesCmboBox\" was not injected: check your FXML file 'Manager.fxml'.";
		assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'Manager.fxml'.";
		assert waitList != null : "fx:id=\"waitList\" was not injected: check your FXML file 'Manager.fxml'.";
		assert statusLbl != null : "fx:id=\"statusLbl\" was not injected: check your FXML file 'Manager.fxml'.";

		algorithmCmbBox.getItems().add("First Fit");
		algorithmCmbBox.getItems().add("Best Fit");
		algorithmCmbBox.getItems().add("Worst Fit");
		algorithmCmbBox.getSelectionModel().selectFirst();
		for (int i = 7; i < 20; i++) {
			sizesCmboBox.getItems().add((int) Math.pow(2, i));
		}
		sizesCmboBox.getSelectionModel().selectFirst();

		pcmbBox.getItems().add("Dummy");
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
			EventHandler<ActionEvent> handleRemove, EventHandler<ActionEvent> handleProcessSelected,
			EventHandler<ActionEvent> handleClear, EventHandler<ActionEvent> handleResize) {
		addBtn.setOnAction(handleRun);
		compactionBtn.setOnAction(handleCompact);
		removeBtn.setOnAction(handleRemove);
		pcmbBox.setOnAction(handleProcessSelected);
		clear.setOnAction(handleClear);
		resizeBtn.setOnAction(handleResize);
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
		memVBox.setAlignment(Pos.CENTER);
	}

	/**
	 * Create waiting queue visuals
	 */

	public void setWaitingQueue(ArrayList<String> processes) {
		waitList.getChildren().clear();
		waitList.getChildren().add(new Label("Waiting Queue"));
		for (String s : processes) {
			waitList.getChildren().add(new Label(s));
		}
	}

	/**
	 * @param datum String of data to be contained in this memory block. The format
	 *              is comma delimited in the order: "Name,StartAddress,Size"
	 * @return Formatted rectangle
	 * @author Brandon Ruiz
	 */
	private BorderPane memoryBlock(String datum) {
		String[] tokens = datum.split(",");
		BorderPane bp = new BorderPane();
		Rectangle visual = new Rectangle();
		visual.setWidth(100);
		visual.setHeight(Integer.valueOf(tokens[2].trim()));
		visual.setStroke(Color.BLACK);
		if (!tokens[0].trim().equals("Empty Space")) {
			visual.setFill(Color.CYAN);
			bp.setCenter(visual);
		} else {
			visual.setFill(Color.GRAY);

		}
		Text address = new Text(tokens[1]);
		Text type = new Text(tokens[0]);
		bp.setLeft(address);
		bp.setCenter(visual);
		bp.setRight(type);
		BorderPane.setAlignment(address, Pos.TOP_CENTER);
		BorderPane.setAlignment(type, Pos.TOP_CENTER);
		BorderPane.setAlignment(visual, Pos.CENTER);
		return bp;
	}

	/**
	 * Fills the list combo box with processes using the auto generated names
	 * "Process #" where the # is their index + 1
	 * 
	 * @param alan Array List of all associated names to be added
	 */
	public void populateProcessList(ArrayList<String> alan) {
		pcmbBox.getItems().clear();
		for (String s : alan) {
			pcmbBox.getItems().add(s);
		}
		pcmbBox.getSelectionModel().selectFirst();
	}

	/**
	 * Gets the user selected value in the Process combo box
	 * 
	 * @return The currently selected process
	 */
	public String selectedProcess() {
		return pcmbBox.getValue().trim();
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
		return Math.abs(Integer.valueOf(sizeBox.getText().trim()));
	}

	/**
	 * Gets the integer value of memory the user would like. All values are powers
	 * of 2 starting at 64.
	 * 
	 * @return Integer value selected by the user
	 */
	public int resizeMemoryValue() {
		return sizesCmboBox.getValue();
	}

	/**
	 * Displays to the user the size of memory.
	 * 
	 * @param size Integer size of memory
	 */
	public void currentSize(int size) {
		currentSizeTxt.setText(String.format("Memory Size: %dK", size));
	}
}
