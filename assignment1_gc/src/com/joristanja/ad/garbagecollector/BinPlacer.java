import java.util.Comparator;
import java.util.PriorityQueue;

public class BinPlacer {
	private City city;
	private int nBins;
	
	public BinPlacer(City city, int nBins) {
		this.city = city;
		this.nBins = nBins;
	}
	
	public boolean canPlaceAllBins() {
		Intersection[] intersections = city.getIntersections();
		PriorityQueue<Intersection> intersectionDegree = new PriorityQueue<>(new Comparator<Intersection>() {
			@Override
			public int compare(Intersection a, Intersection b) {
				return a.getNumAdjacent() - b.getNumAdjacent();
			}
		});
		
		for(Intersection intersection: intersections) {
			intersectionDegree.add(intersection);
		}
		
		while(!intersectionDegree.isEmpty() && nBins != 0){
			Intersection current = intersectionDegree.remove();
			if (current.canHaveBin()){
				nBins--;
				current.setBin(true);
			}
			
		}
		return nBins == 0;
	}
}
