package modelB;

import javafx.scene.layout.BorderPane;

/**
 * A class that represents memory space in RAM
 * 
 * @author Brandon Ruiz
 *
 */
public class MemBlock {
	
	/**
	 * Size in memory
	 */
	private int size;
	
	/**
	 * Address in memory
	 */
	private int address;

	/**
	 * Class constructor
	 * 
	 * @param size
	 * @param address
	 */
	public MemBlock(int size, int address) {
		this.size = size;
		this.address = address;
	}
	
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
	 * Create a box representing this memory space
	 * To be used in the output diagram created in the MemoryManager
	 * 
	 * @return pictoral representation
	 */
	public BorderPane getDiagramBox() {
		return new BorderPane();
	}
	
}
