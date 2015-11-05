
public class City {
	private Street[] streets;
	private Intersection[] intersections;
	private int nStreets = 0;

	public City(int n, int m) {
		streets = new Street[n];
		intersections = new Intersection[m];
		for (int i = 0; i < intersections.length; i++) {
			intersections[i] = new Intersection();
		}
	}

	public void makeStreet(int i, int j) {
		streets[nStreets++] = new Street(intersections[i - 1], intersections[j - 1]);
		intersections[i - 1].addAdjacent(intersections[j - 1]);
		intersections[j - 1].addAdjacent(intersections[i - 1]);
	}

	public int getNumStreets() {
		return nStreets;
	}

	public Street[] getStreets() {
		return streets;
	}

	public Intersection[] getIntersections() {
		return intersections;
	}
}
