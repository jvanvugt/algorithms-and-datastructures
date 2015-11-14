package com.joristanja.ad.garbagecollector;
/**
 * A city with intersections and streets
 * @author Joris van Vugt & Tanja Crijns
 *
 */
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

	/**
	 * Add a street to the city from intersection i to intersection j
	 * @param i
	 * @param j
	 */
	public void makeStreet(int i, int j) {
		streets[nStreets++] = new Street(intersections[i - 1], intersections[j - 1]);
		intersections[i - 1].addAdjacent(intersections[j - 1]);
		intersections[j - 1].addAdjacent(intersections[i - 1]);
	}

	/**
	 * @return The number of streets in the city
	 */
	public int getNumStreets() {
		return nStreets;
	}

	/**
	 * @return An array of all streets in this city
	 */
	public Street[] getStreets() {
		return streets;
	}

	/**
	 * @return An array of all intersections in this city
	 */
	public Intersection[] getIntersections() {
		return intersections;
	}
}
