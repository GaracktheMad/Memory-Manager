package model;

import java.util.Comparator;

/**
 * A class that represents memory space in RAM
 * 
 * @author Brandon Ruiz
 *
 */
public abstract class MemBlock implements Comparator<MemBlock> {
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
	 * @param size new size
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
	 * @param size new address
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
	 *         diagram. Order is as follows: ID, Address, Size
	 */
	public String getDiagramData() {
		return String.format("%s,%8d,%8d", id, address, size);
	}

	@Override
	public int compare(MemBlock arg0, MemBlock arg1) {
		if (arg0.address < arg1.address) {
			return -1;
		} else if (arg0.address > arg1.address) {
			return 1;
		} else {
			return 0;
		}
	}

}
