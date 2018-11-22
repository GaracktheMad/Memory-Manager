package model;

public class EmptySpace extends MemBlock {

	public EmptySpace(int size, int address) {
		this.size = size;
		this.address = address;
		id = String.format("E%d", idCounter++);
	}

	@Override
	public String getDiagramData() {
		// TODO Auto-generated method stub
		return null;
	}

}
