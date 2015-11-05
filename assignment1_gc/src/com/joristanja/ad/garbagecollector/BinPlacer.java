package com.joristanja.ad.garbagecollector;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Algorithm to check if it is possible two place a number of
 * garbage bins in a city.
 * @author Joris van Vugt & Tanja Crijns
 *
 */
public class BinPlacer {
	private City city;
	private int nBins;
	
	public BinPlacer(City city, int nBins) {
		this.city = city;
		this.nBins = nBins;
	}
	
	/**
	 * Checks if it is possible to place nBins on the intersections of
	 * the city, where no 2 bins can be on adjacent intersections
	 * @return
	 */
	public boolean canPlaceAllBins() {
		LinkedList<Intersection> intersections = new LinkedList<>();
		
		for(Intersection intersection: city.getIntersections()) {
			intersections.add(intersection);
		}
		
		// Sort the intersections by their degree (ascending)
		intersections.sort(new Comparator<Intersection>() {
			@Override
			public int compare(Intersection a, Intersection b) {
				return a.getNumAdjacent() - b.getNumAdjacent();
			}
		});
		
		
		for (int i = 0; i < intersections.size(); i++) {
			LinkedList<Intersection> currentQueue = new LinkedList<>(intersections);
			
			// Put the intersections we've already tried at the end of the queue
			for (int j = 0;j < i; j++) {
				Intersection element = currentQueue.remove();
				currentQueue.add(element);
			}
			
			int currentBins = nBins;
			// Place bins greedily
			while(!currentQueue.isEmpty() && currentBins != 0){
				Intersection current = currentQueue.remove();
				if (current.canHaveBin()){
					currentBins--;
					current.setBin(true);
				}
			}
			// We've placed all bins, thus it is possible
			if(currentBins == 0)
				return true;
			
			// Remove all bins for the next try
			for (Intersection intersection: intersections) {
				intersection.setBin(false);
			}
		}
		// We've exhausted all options
		return false;
	}
}
