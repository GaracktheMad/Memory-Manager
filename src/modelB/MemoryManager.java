package modelB;

import java.util.ArrayList;

import javafx.scene.layout.VBox;

/**
 * The class which holds and manages the simulated memory
 * 
 * @author
 *
 */
public class MemoryManager {
	
	/**
	 * The memory array
	 */
	private ArrayList<Process> memory;
	
	/**
	 * The waiting queue
	 */
	private ArrayList<Process> waitingQueue;
	
	/**
	 * The diagram of memory
	 */
	private VBox diagram;

	/**
	 * Class constructor
	 */
	public MemoryManager() {
		memory = new ArrayList<Process>();
		waitingQueue = new ArrayList<Process>();
		diagram = new VBox();
		/**
		 * To be added
		 * - the initial memory diagram
		 * - the initial memory space
		 * - the assumed size of memory, for all diagram box creation
		 */
	}
	
	/**
	 * Diagram getter
	 * 
	 * @return diagram
	 */
	public VBox getDiagram() {
		return diagram;
	}

	/**
	 * Attempts to add a new process via best fit algorithm
	 * Adds to the waiting queue if necessary
	 * 
	 * @param new process
	 * @return status of new process + status of waiting queue
	 */
	public String addBestFit(Process p) {
		return "";
	}
	
	/**
	 * Attempts to add a new process via first fit algorithm
	 * Adds to the waiting queue if necessary
	 * 
	 * @param new process
	 * @return status of new process + status of waiting queue
	 */
	public String addFirstFit(Process p) {
		return "";
	}
	
	/**
	 * Attempts to add a new process via worst fit algorithm
	 * Adds to the waiting queue if necessary
	 * 
	 * @param new process
	 * @return status of new process + status of waiting queue
	 */
	public String addWorstFit(Process p) {
		return "";
	}
	
	/**
	 * Removes process from memory or the waiting queue based on id
	 * 
	 * @param id of process to remove
	 * @return status of removal
	 */
	public String remove(String id) {
		return "";
	}
	
	/**
	 * Compacts empty space
	 * Attempts to add processes from waiting queue
	 * 
	 * @return compaction message + status of waiting queue
	 */
	public String compactAddQueue() {
		return "";
	}

}
