package model;

/**
 * A class that represents memory space in RAM
 * 
 * @author Brandon Ruiz
 *
 */
public abstract class MemBlock {
	protected int idCounter = 0;

	/**
	 * The unchanging id of a process
	 */
	protected String id;

	/**
	 * Size in memory
	 */
	protected int size;

	/**
	 * Address in memory
	 */
	protected int address;

	/**
	 * Size getter
	 * 
	 * @return this size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Size setter
	 * 
	 * @param new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Address getter
	 * 
	 * @return this address
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * Address setter
	 * 
	 * @param new address
	 */
	public void setAddress(int address) {
		this.address = address;
	}

	/**
	 * id getter
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Create a box representing this memory space To be used in the output diagram
	 * created in the MemoryManager
	 * 
	 * @return Comma delimited string representing all the data needed to create a
	 *         diagram
	 */
	public abstract String getDiagramData();

}
