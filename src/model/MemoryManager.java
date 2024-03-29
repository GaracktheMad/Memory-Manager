package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The class which holds and manages the simulated memory
 * 
 * @author Brandon Ruiz and Peter Vukas
 *
 */
public class MemoryManager {

	/**
	 * The memory array
	 */
	private ArrayList<MemBlock> memory;

	/**
	 * The waiting queue
	 */
	private ArrayList<Process> waitingQueue;
	/**
	 * The size (KB) of memory
	 */
	public final int size;

	/**
	 * Class constructor
	 * 
	 * @param memorySize Integer value of the maximum number of space this memory
	 *                   manager will handle. Cannot be changed.
	 */
	public MemoryManager(int memorySize) {
		memory = new ArrayList<MemBlock>();
		waitingQueue = new ArrayList<Process>();
		memory.add(new EmptySpace(memorySize, 0));
		size = memorySize;
	}

	/**
	 * Attempts to add a new process via best fit algorithm Adds to the waiting
	 * queue if necessary
	 * 
	 * @param p new process
	 * @return True if added to memory, false if on waiting queue
	 * @author Peter Vukas
	 */
	public boolean addBestFit(Process p) {
		int entryPoint = -1; // Current best fitting position to enter
		int temp = -1; // Value of the hole that would result from the last best choice
		int index = -1; // Index of the hole to replace in memory array
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i) instanceof EmptySpace) {
				if (memory.get(i).getSize() >= p.getSize()) {
					if (entryPoint == -1 || temp > memory.get(i).getSize() - p.getSize()) {
						entryPoint = memory.get(i).getAddress();
						temp = memory.get(i).getSize() - p.getSize();
						index = i;
					}
				}
			}
		}
		if (index < 0) {
			if (waitingQueue.size() > 0) {
				for (Process p2 : waitingQueue) {
					if (p2.getId() == p.getId()) {
						waitingQueue.remove(p2);
						waitingQueue.add(p);
						return false;
					}
				}
			}
			waitingQueue.add(p);
			return false;
		} else {
			EmptySpace e = (EmptySpace) memory.get(index);
			p.setAddress(e.getAddress());
			if (p.getSize() >= e.getSize()) {
				memory.remove(e);
			} else {
				e.setAddress(p.getAddress() + p.getSize());
				e.setSize(e.getSize() - p.getSize());
			}
			memory.add(p);
			memory.sort(p);
			p.inMemory = true;
			waitingQueue.remove(p);
			return true;
		}
	}

	/**
	 * Attempts to add a new process via first fit algorithm Adds to the waiting
	 * queue if necessary
	 * 
	 * @param p new process
	 * @return True if added to memory, false if on waiting queue
	 * @author Brandon Ruiz and Peter Vukas
	 */
	public boolean addFirstFit(Process p) {
		for (MemBlock mb : memory) {
			if (mb instanceof EmptySpace == true) {
				if (mb.getSize() > p.getSize()) {
					p.setAddress(mb.getAddress());
					mb.setAddress(p.getAddress() + p.getSize());
					mb.setSize(mb.getSize() - p.getSize());
					memory.add(p);
					waitingQueue.remove(p);
					memory.sort(p);
					p.inMemory = true;
					return true;
				} else if (mb.getSize() == p.getSize()) {
					p.setAddress(mb.getAddress());
					memory.remove(mb);
					memory.add(p);
					memory.sort(p);
					p.inMemory = true;
					waitingQueue.remove(p);
					return true;
				}
			}
		}
		if (waitingQueue.size() > 0) {
			for (Process p2 : waitingQueue) {
				if (p2.getId() == p.getId()) {
					waitingQueue.remove(p2);
					waitingQueue.add(p);
					return false;
				}
			}
		}
		waitingQueue.add(p);
		return false;
	}

	/**
	 * Attempts to add a new process via worst fit algorithm Adds to the waiting
	 * queue if necessary
	 * 
	 * @author Peter Vukas
	 * @param p new process
	 * @return True if added to memory, false if on waiting queue
	 */
	public boolean addWorstFit(Process p) {
		int entryPoint = -1; // Current best fitting position to enter
		int temp = -1; // Value of the hole that would result from the last best choice
		int index = -1; // Index of the hole to replace in memory array
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i) instanceof EmptySpace) {
				if (memory.get(i).getSize() >= p.getSize()) {
					if (entryPoint == -1 || temp < memory.get(i).getSize() - p.getSize()) {
						entryPoint = memory.get(i).getAddress();
						temp = memory.get(i).getSize() - p.getSize();
						index = i;
					}
				}
			}
		}
		if (index < 0) {
			if (waitingQueue.size() > 0) {
				for (Process p2 : waitingQueue) {
					if (p2.getId() == p.getId()) {
						waitingQueue.remove(p2);
						waitingQueue.add(p);
						return false;
					}
				}
			}
			waitingQueue.add(p);
			return false;
		} else {
			EmptySpace e = (EmptySpace) memory.get(index);
			p.setAddress(e.getAddress());
			if (p.getSize() >= e.getSize()) {
				memory.remove(e);
			} else {
				e.setAddress(p.getAddress() + p.getSize());
				e.setSize(e.getSize() - p.getSize());
			}
			memory.add(p);
			memory.sort(p);
			p.inMemory = true;
			waitingQueue.remove(p);
			return true;
		}
	}

	/**
	 * Removes process from memory or the waiting queue based on id
	 * 
	 * @param id of process to remove
	 * @return status of removal
	 * @author Brandon Ruiz and Peter Vukas
	 */
	public boolean remove(String id) {
		if (id.substring(0, 1).equals("P")) {
			for (MemBlock mb : memory) {
				if (mb.getId().equals(id)) {
					EmptySpace e = new EmptySpace(mb.getSize(), mb.getAddress());
					memory.remove(mb);
					memory.add(e);
					memory.sort(e);
					Process p = (Process) mb;
					p.inMemory = false;
					for (int i = 0; i < memory.size() - 1; i++) {
						if (memory.get(i) instanceof EmptySpace && memory.get(i + 1) instanceof EmptySpace) {
							// Combine 2 separate Empty Spaces into 1
							memory.get(i).setSize(memory.get(i + 1).getSize() + memory.get(i).getSize());
							memory.remove(i + 1);
							--i;
						}
					}
					return true;
				}
			}
			for (Process p : waitingQueue) {
				if (p.getId() == id) {
					waitingQueue.remove(p);
					for (int i = 0; i < memory.size() - 1; i++) {
						if (memory.get(i) instanceof EmptySpace && memory.get(i + 1) instanceof EmptySpace) {
							// Combine 2 separate Empty Spaces into 1
							memory.get(i).setSize(memory.get(i + 1).getSize() + memory.get(i).getSize());
							memory.remove(i + 1);
							--i;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Compacts empty space. Attempts to add processes from waiting queue
	 * 
	 * @author Peter Vukas and Brandon Ruiz
	 */
	public void compactAddQueue() {
		for (int i = 0; i < memory.size() - 1; i++) {
			if (memory.get(i) instanceof EmptySpace && memory.get(i + 1) instanceof EmptySpace) {
				// Combine 2 separate Empty Spaces into 1
				memory.get(i).setSize(memory.get(i + 1).getSize() + memory.get(i).getSize());
				memory.remove(i + 1);
				--i;
			} else if (memory.get(i) instanceof EmptySpace && memory.get(i + 1) instanceof Process) {
				/*
				 * int temp; // Swaps the location of a Process and Empty Space, forcing empty
				 * space further // down the list. temp = memory.get(i).getAddress();
				 * memory.get(i).setAddress(memory.get(i + 1).getAddress());
				 */
				memory.get(i + 1).setAddress(memory.get(i).getAddress());
				memory.get(i).setAddress(memory.get(i + 1).getAddress() + memory.get(i + 1).getSize());
				Collections.swap(memory, i, i + 1);
			}
		}

		while (waitingQueue.size() > 0) {
			Process p = waitingQueue.get(0);
			for (MemBlock mb : memory) {
				if (mb instanceof EmptySpace == true) {
					if (mb.getSize() > p.getSize()) {
						p.setAddress(mb.getAddress());
						mb.setAddress(p.getAddress() + p.getSize());
						mb.setSize(mb.getSize() - p.getSize());
						memory.add(p);
						waitingQueue.remove(p);
						memory.sort(p);
						p.inMemory = true;
						p = null;
						break;
					} else if (mb.getSize() == p.getSize()) {
						p.setAddress(mb.getAddress());
						memory.remove(mb);
						memory.add(p);
						memory.sort(p);
						p.inMemory = true;
						waitingQueue.remove(p);
						p = null;
						break;
					}
				}
			}
			if (p != null)
				break;
		}

	}

	/**
	 * @author Peter Vukas
	 * @return ArrayList of all objects in memory, whether they be processes or
	 *         empty space
	 */
	public ArrayList<String> exportDiagramData() {
		ArrayList<String> exporter = new ArrayList<String>();
		memory.sort(memory.get(0));
		for (MemBlock mb : memory) {
			exporter.add(mb.getDiagramData());
		}
		return exporter;
	}

	/**
	 * Exports list of data for the waiting queue
	 * 
	 * @return String ArrayList of the name and id of all processes in the waiting
	 *         queue
	 * @author Brandon Ruiz
	 */
	public ArrayList<String> exportWaitingQueueData() {
		ArrayList<String> waitingInfo = new ArrayList<String>();
		for (Process p : waitingQueue) {
			waitingInfo.add(p.getWaitingQueueData());
		}
		return waitingInfo;
	}

}
