package model;

import java.util.ArrayList;

/**
 * The class which holds and manages the simulated memory
 * 
 * @author
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
	 * Class constructor
	 */
	public MemoryManager() {
		memory = new ArrayList<MemBlock>();
		waitingQueue = new ArrayList<Process>();
		/**
		 * To be added - the initial memory diagram - the initial memory space - the
		 * assumed size of memory, for all diagram box creation
		 */
	}

	/**
	 * Attempts to add a new process via best fit algorithm Adds to the waiting
	 * queue if necessary
	 * 
	 * @param new process
	 * @return status of new process + status of waiting queue
	 */
	public boolean addBestFit(Process p) {
		return false;
	}

	/**
	 * Attempts to add a new process via first fit algorithm Adds to the waiting
	 * queue if necessary
	 * 
	 * @param new process
	 * @return status of new process + status of waiting queue
	 * @author Brandon Ruiz
	 */
	public boolean addFirstFit(Process p) {
		for (MemBlock mb : memory) {
			if (mb instanceof Process == false) {
				if (mb.getSize() > p.getSize()) {
					mb.setSize(mb.getSize() - p.getSize());
					mb.setAddress(mb.getAddress() + p.getSize());
					memory.add(memory.indexOf(mb), p);
					return true;
				} else if (mb.getSize() == p.getSize()) {
					mb = p;
					return true;
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
	 * @param new process
	 * @return status of new process + status of waiting queue
	 */
	public boolean addWorstFit(Process p) {
		return false;
	}

	/**
	 * Removes process from memory or the waiting queue based on id
	 * 
	 * @param id of process to remove
	 * @return status of removal
	 * @author Brandon Ruiz and Peter Vukas
	 */
	public boolean remove(String id) {
		for (MemBlock mb : memory) {
			if (mb.getId().equals(id)) {
				memory.remove(mb);
				return true;
			}
		}
		if (id.substring(0, 1).equals("A")) {
			for (Process p : waitingQueue) {
				if (p.getId() == id) {
					waitingQueue.remove(p);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Compacts empty space Attempts to add processes from waiting queue
	 * 
	 * @return compaction message + status of waiting queue
	 */
	public boolean compactAddQueue() {
		return false;
	}

}
