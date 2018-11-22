package model;

/**
 * A class that represents a process in memory/memory in use
 * 
 * @author Brandon Ruiz
 *
 */
public class Process extends MemBlock {
	/**
	 * Class constructor
	 * 
	 * @param size
	 * @param address
	 */
	public Process(int size) {
		this.size = size;
		id = String.format("P%d", idCounter++);
	}
}