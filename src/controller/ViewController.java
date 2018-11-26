package controller;

import java.util.ArrayList;

import javafx.application.Application;
import model.Process;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MemoryManager;
import view.MemoryManagerPane;

/**
 * @author Peter Vukas
 *
 */
public class ViewController extends Application {
	private MemoryManagerPane mmp;
	private MemoryManager mm;
	private ArrayList<Process> availableProcesses;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader f = MemoryManagerPane.constructable();
		MemoryManagerPane mmp = f.getController();
		mmp.setOnActions(new HandleRun(), new HandleCompact(), new HandleRemove(), new HandleProcessSelected());
		Scene scene = new Scene(mmp);
		primaryStage.setScene(scene);
		primaryStage.show();

		mm = new MemoryManager(2000000);
		mmp.refreshMemoryDisplay(mm.exportData());

		availableProcesses = new ArrayList<Process>();
		for (int i = 0; i < mmp.numberOfProcesses(); i++) {
			availableProcesses.add(new Process(0, (String.format("Process %2d", i + 1))));
		}
		availableProcesses.sort(availableProcesses.get(0));
	}

	private Process findProcess(String associatedName) {
		for (int i = 0; i < availableProcesses.size(); i++) {
			if (availableProcesses.get(i).associatedName.equals(mmp.selectedProcess())) {
				return availableProcesses.get(i);
			}
		}
		return null;
	}

	/**
	 * Adds the process to the memory manager based on the selected algorithm and
	 * updates the memory display
	 * 
	 * @author Peter Vukas
	 */
	public class HandleRun implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			boolean hasNoError = false;
			String errorMessage = "Unknown Error";
			try {
				if (mmp.getProcessSize() >= 0) {
					Process p = findProcess(mmp.selectedProcess());
					if (p != null) {
						if (p.isInMemory() == true) {
							hasNoError = false;
							errorMessage = "Process is already in memory";
						} else {
							if (mmp.selectedAlgorithm().equals("First Fit")) {
								hasNoError = mm.addFirstFit(p);
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
							} else if (mmp.selectedAlgorithm().equals("Best Fit")) {
								hasNoError = mm.addBestFit(p);
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
							} else if (mmp.selectedAlgorithm().equals("Worst Fit")) {
								hasNoError = mm.addWorstFit(p);
							} else {
								hasNoError = false;
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
								errorMessage = "No Algorithm is currently selected";
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				hasNoError = false;
				errorMessage = "Invalid Process Size";
			}
			if (hasNoError == false) {
				mmp.setAlert(errorMessage);
			} else {
				mmp.hideAlert();
				mmp.refreshMemoryDisplay(mm.exportData());
			}
		}

	}

	/**
	 * Compacts memory.
	 * 
	 * @author Peter Vukas
	 *
	 */
	public class HandleCompact implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mm.compactAddQueue();
			mmp.refreshMemoryDisplay(mm.exportData());
		}

	}

	/**
	 * Removes the Process selected in the Process ComboBox from memory
	 * 
	 * @author Peter Vukas
	 *
	 */
	public class HandleRemove implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Process p = findProcess(mmp.selectedProcess());
			if (p != null && p.isInMemory() == true) {
				boolean success = mm.remove(p.getId());
				if (success == false) {
					mmp.setAlert("This Process isn't In Memory Yet");
				} else {
					mmp.hideAlert();
					mmp.refreshMemoryDisplay(mm.exportData());
				}
			}
		}

	}

	/**
	 * @author Peter Vukas
	 *
	 */
	public class HandleProcessSelected implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Populate the size

		}

	}

}