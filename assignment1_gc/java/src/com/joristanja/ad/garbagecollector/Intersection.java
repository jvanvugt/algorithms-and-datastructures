package com.joristanja.ad.garbagecollector;
import java.util.LinkedList;
import java.util.List;

/**
 * An intersection with or without bin
 * @author Joris van Vugt & Tanja Crijns
 *
 */
public class Intersection {
	private boolean bin = false;
	private final List<Intersection> adjacent = new LinkedList<>();

	void addAdjacent(Intersection i) {
		adjacent.add(i);
	}

	public boolean hasBin() {
		return bin;
	}

	public void setBin(boolean b) {
		bin = b;
	}

	/**
	 * @return A list of all adjacent intersections
	 */
	public List<Intersection> getAdjacent() {
		return adjacent;
	}

	/**
	 * Checks if any adjacent intersection already has a bin.
	 * @return True if no adjacent intersection  has a bin. False otherwise.
	 */
	public boolean canHaveBin() {
		for (Intersection a : adjacent) {
			if (a.hasBin()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The number of intersections connected to this intersection by a street
	 */
	public int getNumAdjacent() {
		return adjacent.size();
	}
}
