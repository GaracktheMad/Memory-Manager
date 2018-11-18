package modelB;

import javafx.scene.layout.BorderPane;

/**
 * A class that represents a process in memory/memory in use
 * 
 * @author
 *
 */
public class Process extends MemBlock {
	
	/**
	 * The unchanging id of a process
	 */
	private final String id;
	
	
	/**
	 * Class constructor
	 * 
	 * @param size
	 * @param address
	 * @param id
	 */
	public Process(int size, int address, String id) {
		super(size, address);
		this.id = id;
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
	 * The same results as the super class, but with different aesthetics
	 * 
	 * @see modelB.MemBlock#getDiagramBox()
	 */
	@Override
	public BorderPane getDiagramBox() {
		return new BorderPane();
	}

}
