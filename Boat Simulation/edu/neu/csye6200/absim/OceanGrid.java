package edu.neu.csye6200.absim;

import java.util.ArrayList;
import java.util.HashSet;

public class OceanGrid {
	
	ArrayList<ArrayList<Integer>> island = new ArrayList<ArrayList<Integer>>();
	
	
	private int xaxis = 0,yaxis = 0;

	public int[][] oceanMatrix = new int[8][8];
	
	HashSet<ArrayList<Integer>> hashSet = new 	HashSet<ArrayList<Integer>>();
	
	ArrayList<ArrayList<Integer>> oilSpillLocations = new ArrayList<ArrayList<Integer>>();
	

	public OceanGrid() {
		matrix();
		
	}

	/**
	 * @return the oilSpillLocations
	 */
	public ArrayList<ArrayList<Integer>> getOilSpillLocations() {
		return oilSpillLocations;
	}
	
	//2d Matrix
	public void matrix() {
		for(int i = 0; i < oceanMatrix.length; i++) {
			for(int j = 0; j < oceanMatrix[0].length; j++) {
				if(i == 0 && j == 0) {
					oceanMatrix[i][j] = -2;
				} else if((i >= 0 && i <= 1 && j >= 4 && j <= 6) || (i >= 5 && i <= 6 && j >= 0 && j <= 3)) {
					oceanMatrix[i][j] = -1;
					ArrayList<Integer> temp = new ArrayList<>();
					temp.add(i);
					temp.add(j);
					island.add(temp);
				} else if( i >= 3 && i <= 4 && j >= 4 && j <= 5) {
					oceanMatrix[i][j] = 100;
					ArrayList<Integer> temp = new ArrayList<>();
					temp.add(i);
					temp.add(j);
					oilSpillLocations.add(temp);
					hashSet.add(temp);
				} else {
					oceanMatrix[i][j] = 0;
				}
			}
		}
	}

	//Check whether the boat have reached oilSpill location or not
	public boolean oil(Integer x, Integer y) {
		
		if (xaxis == x && yaxis == y) {
			return true;
		}
		return false;
	}

	//Remove Oil Spill Location from arrayList and HashSet
	public void removeOilSpillLocations(int x, int y) {
		
		if (x == oilSpillLocations.get(0).get(0) && y == oilSpillLocations.get(0).get(1)) {
			
			oilSpillLocations.remove(0);
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(x);
			temp.add(y);
			hashSet.remove(temp);
			oceanMatrix[x][y] = 0;
			
		}
		
	}

	//Update Boat new Location
	public void updateBoat(int x, int y) {
		oceanMatrix[xaxis][yaxis] = 0;
		oceanMatrix[x][y] = -2;
		xaxis = x;
		yaxis = y;
	}

	//Start Oil SPilling in ocean
	public void startSpillingOil() {
		int size = oilSpillLocations.size();
		for(int i = 0; i < size ; i++) {
			
			if(oilSpillLocations.get(i).get(0) < oceanMatrix.length - 1 && oilSpillLocations.get(i).get(1) < oceanMatrix[0].length - 1) {
				
				if(oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1)] > 50) {
					
					if(oceanMatrix[oilSpillLocations.get(i).get(0) - 1][oilSpillLocations.get(i).get(1) + 1] < 50  && 
							oceanMatrix[oilSpillLocations.get(i).get(0) - 1][oilSpillLocations.get(i).get(1) + 1] >= 0) {
						oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1)] -= 3;
						oceanMatrix[oilSpillLocations.get(i).get(0) - 1][oilSpillLocations.get(i).get(1) + 1] += 3;
						
						ArrayList<Integer> temp = new ArrayList<>();
						temp.add(oilSpillLocations.get(i).get(0) - 1);
						temp.add(oilSpillLocations.get(i).get(1) + 1);
						
						if(!hashSet.contains(temp)) {
							oilSpillLocations.add(temp);
							hashSet.add(temp);
						}
						
					} 
					if(oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1) + 1] < 50 && 
							oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1) + 1] >= 0) {
						oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1)] -= 3;
						oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1) + 1] += 3;
						
						ArrayList<Integer> temp = new ArrayList<>();
						temp.add(oilSpillLocations.get(i).get(0));
						temp.add(oilSpillLocations.get(i).get(1) + 1);
						
						if(!hashSet.contains(temp)) {
							oilSpillLocations.add(temp);
							hashSet.add(temp);
						}
					} 
					if(oceanMatrix[oilSpillLocations.get(i).get(0) + 1][oilSpillLocations.get(i).get(1) + 1] < 50 && 
							oceanMatrix[oilSpillLocations.get(i).get(0) + 1][oilSpillLocations.get(i).get(1) + 1] >= 0) {
						oceanMatrix[oilSpillLocations.get(i).get(0)][oilSpillLocations.get(i).get(1)] -= 3;
						oceanMatrix[oilSpillLocations.get(i).get(0) + 1][oilSpillLocations.get(i).get(1) + 1] += 3;
						
						ArrayList<Integer> temp = new ArrayList<>();
						temp.add(oilSpillLocations.get(i).get(0) + 1);
						temp.add(oilSpillLocations.get(i).get(1) + 1);
						
						if(!hashSet.contains(temp)) {
							oilSpillLocations.add(temp);
							hashSet.add(temp);
						}
					}
				}
			}
		}
	}

	/**
	 * @return the oceanMatrix
	 */
	public int[][] getOceanMatrix() {
		return oceanMatrix;
	}
	

}
