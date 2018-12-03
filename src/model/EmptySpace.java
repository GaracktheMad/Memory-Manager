package model;

/**
 * Denotes an empty space in memory which can be replaced by a process.
 * 
 * @author Peter Vukas
 *
 */
public class EmptySpace extends MemBlock {

	/**
	 * @param size    Size of the space
	 * @param address Base address
	 */
	public EmptySpace(int size, int address) {
		this.size = size;
		this.address = address;
		id = String.format("E%d", idCounter++);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.MemBlock#getDiagramData()
	 */
	@Override
	public String getDiagramData() {
		return String.format("Empty Space,%d,%d", address, size);
	}
}
