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
	 * @param id
	 */
	public Process(int size, int address) {
		this.size = size;
		this.address = address;
		id = String.format("P%d", idCounter++);
	}

	@Override
	public String getDiagramData() {
		// TODO Auto-generated method stub
		return null;
	}

}
