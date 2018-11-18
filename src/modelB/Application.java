package modelB;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Application {
	
	/**
	 * Runs memory manager algorithm based on input process parameters and designated algorithm
	 * Updates label
	 */
	Button run;
	
	/**
	 * Compacts memory in manager and adds processes from waiting queue
	 * Updates label
	 */
	Button compact;
	
	/**
	 * Removes a process of designated id from the queue
	 * Updates label
	 */
	Button remove;
	
	/**
	 * Selects an integer 1 - 10 of which the new process will be created
	 */
	ComboBox<Integer> processId;
	
	/**
	 * Selects the next algorithm to be used
	 */
	ComboBox<String> algorithm;
	
	/**
	 * Allows the user to input the next process size
	 */
	TextField processSize;
	
	/**
	 * Status of user input, algorithm, waiting queue, whatever
	 */
	Label Status;
	
	/**
	 * Avoids two processes of the same id from being used
	 */
	Boolean[] inUse;
	
	/**
	 * The memory manager
	 */
	MemoryManager memoryManager;

}
