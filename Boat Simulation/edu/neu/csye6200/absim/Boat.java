package edu.neu.csye6200.absim;

import java.util.ArrayList;

public class Boat {

	private String boatName;
	private int initialXAxisLocation;
	private int initialYAxisLocation;
	private int nextXLocation = 0;
	private int nextYLocation = 0;
	private double loadCapacity;

	public Boat(String boatName, int xAxisLocation, int yAxisLocation, double loadCapacity) {
		this.boatName = boatName;
		this.initialXAxisLocation = xAxisLocation;
		this.initialYAxisLocation = yAxisLocation;
		this.loadCapacity = loadCapacity;
		
	}

	//Controling Boat Movement
	public int[][] boatMovement(int x, int y, ArrayList<ArrayList<Integer>> island) {
		int[][] location = { { nextXLocation, nextYLocation } };

		//If Shortest Path option is selected
		if (ABRule.getCtr1() == 1) {
			xmovement(x, y, island);
			location[0][0] = nextXLocation;
			ymovement(x, y, island);
			location[0][1] = nextYLocation;
		}

		//If First Row then Column option is selected
		if (ABRule.getCtr1() == 2) {
			
			int currentXLocation = nextXLocation;
			if (currentXLocation != x) {
				xmovement(x, y, island);
				if (nextXLocation == currentXLocation) {
					if (nextYLocation != y) {
						ymovement(x, y, island);
						location[0][1] = nextYLocation;
					} else {
						location[0][1] = y;
					}
				}
				location[0][0] = nextXLocation;
			} 
			else {
				location[0][0] = x;
				if (nextYLocation != y) {
					ymovement(x, y, island);
					location[0][1] = nextYLocation;
				} else {
					location[0][1] = y;
				}
			}
		
		}

		//If First Column then Row option is selected
		if (ABRule.getCtr1() == 3) {
			int currentYLocation = nextYLocation;
			if (currentYLocation != y) {
				ymovement(x, y, island);
				if (nextYLocation == currentYLocation) {
					if (nextXLocation != x) {
						xmovement(x, y, island);
						location[0][0] = nextXLocation;
					} else {
						location[0][0] = x;
					}
				}
				location[0][1] = nextYLocation;
			} else {
				int currentXLocation = nextXLocation;

				if (nextXLocation != x) {
					xmovement(x, y, island);
					location[0][0] = nextXLocation;
				} else {
					location[0][0] = x;
				}
			}
		}

		return location;
	}

	//Movement of Boat in X-axis
	private void xmovement(int x, int y, ArrayList<ArrayList<Integer>> island) {
		if (nextXLocation < x && !checkIsland(nextXLocation + 1, nextYLocation, island)) {
			nextXLocation += 1;
		} else if (nextXLocation > x && !checkIsland(nextXLocation - 1, nextYLocation, island)) {
			nextXLocation -= 1;
		}
	}

	//Movement of boat in Y-axis
	private void ymovement(int x, int y, ArrayList<ArrayList<Integer>> island) {
		if (nextYLocation < y && !checkIsland(nextXLocation, nextYLocation + 1, island)) {
			if (checkIsland(x, y, island)) {
				System.out.println(x + " & " + y);

			}
			nextYLocation += 1;
		} else if (nextYLocation > y && !checkIsland(nextXLocation, nextYLocation - 1, island)) {
			nextYLocation -= 1;
		}
	}

	//Checking whether the next location is island or not
	private boolean checkIsland(int x, int y, ArrayList<ArrayList<Integer>> island) {
		for (ArrayList<Integer> temp : island) {
			if (temp.get(0) == x && temp.get(1) == y) {

				return true;
			}
		}
		return false;
	}
}
