package model;

/**
 * A class that represents a process in memory/memory in use
 * 
 * @author Brandon Ruiz and Peter Vukas
 *
 */
public class Process extends MemBlock {
	/**
	 * The name of this process. Cannot be changed.
	 */
	public final String associatedName;

	/**
	 * Flags the Process's state in memory. For use by the controller.
	 */
	protected boolean inMemory = false;

	/**
	 * Class constructor
	 * 
	 * @param size Amount of space this process needs
	 * @param name Associated name to be set
	 */
	public Process(int size, String name) {
		this.size = size;
		id = String.format("P%d", idCounter++);
		associatedName = name;
	}

	/**
	 * @return True if in memory, false if not
	 */
	public boolean isInMemory() {
		return inMemory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MemBlock#getDiagramData()
	 */
	@Override
	public String getDiagramData() {
		return String.format("%s,%d,%d", associatedName, address, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MemBlock#getDiagramData()
	 */
	@Override
	public String getWaitingQueueData() {
		return String.format("%s,%d", associatedName, size);
	}
}