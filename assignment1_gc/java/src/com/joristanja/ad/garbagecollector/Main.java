package com.joristanja.ad.garbagecollector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Entry point for the Garbage Collector problem
 * for Algorithms and Datastructures practical assignment 1
 * @author Joris van Vugt & Tanja Crijns
 *
 */
public class Main {

	/**
	 * Read the parameters of the problem from stdin
	 * and prints possible or impossible to stdout
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] firstLine = scanner.nextLine().split(" ");
		int n = Integer.valueOf(firstLine[0]);
		int m = Integer.valueOf(firstLine[1]);
		int k = Integer.valueOf(firstLine[2]);
		City nijmegen = new City(n, m);
		for (int i = 0; i < n; i++) {
			String[] nextStreet = scanner.nextLine().split(" ");
			nijmegen.makeStreet(Integer.valueOf(nextStreet[0]), 
					Integer.valueOf(nextStreet[1]));
		}
		
		BinPlacer bp =  new BinPlacer(nijmegen, k);
		if(bp.canPlaceAllBins()) {
			System.out.println("possible");
		} else {
			System.out.println("impossible");
		}
		scanner.close();			
	}
	
	/**
	 * Code for read and testing a lot of problems from files
	 */
	public static void test() {
		Scanner scanner = null;
		try {
			for(int size = 0; size < 3; size++) {
				for (int num = 1; num < (size != 2 ? 7 : 9); num++) {
					String file = (size == 0 ? "small" : (size == 1 ? "big" : "extra")) + "_" + num;
					scanner = new Scanner(new File("../test_samples/" + file + ".in"));
					String[] firstLine = scanner.nextLine().split(" ");
					int n = Integer.valueOf(firstLine[0]);
					int m = Integer.valueOf(firstLine[1]);
					int k = Integer.valueOf(firstLine[2]);
					City nijmegen = new City(n, m);
					for (int i = 0; i < n; i++) {
						String[] nextStreet = scanner.nextLine().split(" ");
						nijmegen.makeStreet(Integer.valueOf(nextStreet[0]), Integer.valueOf(nextStreet[1]));
					}
					
					BinPlacer bp =  new BinPlacer(nijmegen, k);
					
					scanner = new Scanner(new File("../test_samples/" + file + ".out"));
					String out = scanner.nextLine();
					boolean isPossible = bp.canPlaceAllBins();
					if (out.equals("possible") && isPossible || out.equals("impossible") && !isPossible) {
						System.out.println(file + ": success");
					} else {
						System.err.println(file + ": failed");
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		scanner.close();
	}

}
