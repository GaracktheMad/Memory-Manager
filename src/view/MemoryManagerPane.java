package view;

import java.util.ArrayList;

import controller.ViewController.HandleCompact;
import controller.ViewController.HandleRemove;
import controller.ViewController.HandleRun;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	public void setOnActions(HandleRun handleRun, HandleCompact handleCompact, HandleRemove handleRemove) {
		addBtn.setOnAction(handleRun);
		compactionBtn.setOnAction(handleCompact);
		removeBtn.setOnAction(handleRemove);
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
	 */
	private Rectangle memoryBlock(String datum) {
		Rectangle rectangle = new Rectangle();
		// TODO Logic to add the string data into the rectangle
		return rectangle;
	}
}
