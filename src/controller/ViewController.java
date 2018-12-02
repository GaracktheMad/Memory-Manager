package controller;

import java.util.ArrayList;

import javafx.application.Application;
import model.Process;
import view.FXMLFix;
import view.MemoryManagerPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MemoryManager;

/**
 * Manages the Memory Manager and Memory Manager Pane operations
 * 
 * @author Peter Vukas
 *
 */
public class ViewController extends Application {
	/**
	 * Memory Manager display
	 */
	private MemoryManagerPane mmp;
	/**
	 * Memory manager data
	 */
	private MemoryManager mm;
	/**
	 * A list of all processes in "memory"
	 */
	private ArrayList<Process> availableProcesses;
	/**
	 * The initially passed stage value.
	 */
	private Stage ps;
	private final int DEFAULT_MEMORY_SIZE = 20000; 

	@Override
	public void start(Stage primaryStage) throws Exception {
		ps = primaryStage;
		reset(ps, DEFAULT_MEMORY_SIZE);
	}

	private void reset(Stage primaryStage, int memorySize) throws Exception {
		FXMLFix f = new FXMLFix();
		mmp = f.controller();
		mmp.setOnActions(new HandleRun(), new HandleCompact(), new HandleRemove(), new HandleProcessSelected(),
				new HandleClear(), new HandleResize());
		Scene scene = new Scene(f.getPane());
		primaryStage.setScene(scene);
		primaryStage.show();

		mm = new MemoryManager(memorySize);
		mmp.refreshMemoryDisplay(mm.exportDiagramData());
		mmp.setWaitingQueue(mm.exportWaitingQueueData());

		availableProcesses = new ArrayList<Process>();
		for (int i = 0; i < 10; i++) {
			availableProcesses.add(new Process(0, (String.format("Process %d", i + 1))));
		}
		availableProcesses.sort(availableProcesses.get(0));
		ArrayList<String> alan = new ArrayList<String>();
		for (Process p : availableProcesses) {
			alan.add(p.associatedName);
		}

		mmp.populateProcessList(alan);
		mmp.currentSize(memorySize);
	}

	/**
	 * Matches the process name to the process object.
	 * 
	 * @param associatedName The name of the process in the Process combo box
	 * @return The Process object associated to the name
	 */
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
					if (p != null) { // Makes sure the process exists in the program. If it doesn't there was a
										// programming error
						if (p.isInMemory() == true) { // Checks if the process is on the memory stack
							hasNoError = false;
							errorMessage = "Process is already in memory";
						} else {
							p.setSize(Integer.valueOf(mmp.getProcessSize()));
							if (mmp.selectedAlgorithm().equals("First Fit")) { // Uses first fit
								hasNoError = mm.addFirstFit(p);
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
							} else if (mmp.selectedAlgorithm().equals("Best Fit")) {// Uses best fit
								hasNoError = mm.addBestFit(p);
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
							} else if (mmp.selectedAlgorithm().equals("Worst Fit")) {// Uses worst fit
								hasNoError = mm.addWorstFit(p);
								errorMessage = "This process is too large to be added to memory.\nIt has been added to the waiting queue";
							} else {
								hasNoError = false;
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
				mmp.refreshMemoryDisplay(mm.exportDiagramData());
				mmp.setDisableAddBtn(true);
				mmp.setDisableRemoveBtn(false);
			}
			mmp.setWaitingQueue(mm.exportWaitingQueueData());
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
			mmp.refreshMemoryDisplay(mm.exportDiagramData());
			mmp.setWaitingQueue(mm.exportWaitingQueueData());
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
					mmp.refreshMemoryDisplay(mm.exportDiagramData());
					mmp.setWaitingQueue(mm.exportWaitingQueueData());
					mmp.setDisableAddBtn(false);
					mmp.setDisableRemoveBtn(true);
				}
			}
		}

	}

	/**
	 * Dynamically disables and enables the add/remove buttons and populates the
	 * size text field based on whether the currently selected process is in memory
	 * 
	 * @author Peter Vukas
	 *
	 */
	public class HandleProcessSelected implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Process working = findProcess(mmp.selectedProcess());
			if (working.isInMemory() == false) {
				mmp.setDisableAddBtn(false);
				mmp.setDisableRemoveBtn(true);
				mmp.setSizeText(null);
			} else {
				mmp.setDisableAddBtn(true);
				mmp.setDisableRemoveBtn(false);
				mmp.setSizeText(String.valueOf(working.getSize()));
			}
		}

	}

	/**
	 * Resets the program and all its elements to their default state.
	 * 
	 * @author Peter Vukas
	 *
	 */
	public class HandleClear implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			try {
				reset(ps, DEFAULT_MEMORY_SIZE);
			} catch (Exception e) {
				System.exit(0);
			}
		}
	}

	/**
	 * Resets the program with the desired size of memory.
	 * 
	 * @author Peter Vukas
	 *
	 */
	public class HandleResize implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			try {
				reset(ps, mmp.resizeMemoryValue());
			} catch (Exception e) {
				System.exit(0);
			}
		}
	}

}