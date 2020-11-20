package edu.neu.csye6200.absim;

import java.util.ArrayList;
import java.util.Observable;

public class ABSimulation extends Observable implements Runnable {

	private Thread thread;

	private Thread thread1;

	private boolean done;

	private int[][] matrix = new int[8][8];//Matrix to hold initial Values

	//Initial BoatLocations
	private int initialXAxisBoatLocation;
	private int initialYAxisBoatLocation;

	private boolean flag;

	//OilLocations
	public int oilXlocation;
	public int oilYlocation;

	Boat boat = new Boat("The Fighter", initialXAxisBoatLocation, initialYAxisBoatLocation, 400);

	ABRule abRule = new ABRule();

	//All oil spill locations are stored in this arrayList
	ArrayList<ArrayList<Integer>> updatedOilSpillLocations = new ArrayList<ArrayList<Integer>>();

	OceanGrid oceanGrid = new OceanGrid();

	OilSpill oilSpill;

	public ABSimulation() {

		matrix = oceanGrid.getOceanMatrix();

		thread = null;
		thread1 = null;
		done = false;
		initialXAxisBoatLocation = 0;
		initialYAxisBoatLocation = 0;
		oilXlocation = 0;
		oilYlocation = 0;
	}

	//Handling Threads, Updating Boat Location and Remove oilSpill Locations from the ocean
	public void boatMovement(int x, int y) throws InterruptedException {

		flag = oceanGrid.oil(oilXlocation, oilYlocation);
		if (flag == true) {
			//Collecting time
			Thread.sleep(2000L);
			oceanGrid.removeOilSpillLocations(x, y);
		}
		//If Boat Speed in 20 mph
		if (abRule.getCtr2() == 1) {
			Thread.sleep(500L);
			oceanGrid.updateBoat(x, y);
		} else if (abRule.getCtr2() == 2) {
			Thread.sleep(100L);
			oceanGrid.updateBoat(x, y);
		}

	}

	@Override
	public void run() {

		int[][] loc = null;
		updatedOilSpillLocations = oceanGrid.getOilSpillLocations();

		while (updatedOilSpillLocations.size() > 0 && !done) {
			try {

				oilXlocation = updatedOilSpillLocations.get(0).get(0);
				oilYlocation = updatedOilSpillLocations.get(0).get(1);
				
				matrix = oceanGrid.getOceanMatrix();

				loc = boat.boatMovement(oilXlocation, oilYlocation,oceanGrid.island);
				
				boatMovement(loc[0][0], loc[0][1]);
				
				setChanged();
				Thread.sleep(500L);
				notifyObservers(matrix);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		thread = null;
		thread1 = null;
	}

	//To Stop Simulation
	public void stopSim() {

		System.out.println("Stop the simulation");
		if (thread == null)
			return; // defensive coding in case the thread is null
		done = true;
		oilSpill.start = false;

	}

	//To Start Simulation
	public void startSim() {
		System.out.println("Starting the simulation");
		done = false; // reset the done flag.
		if (thread != null)
			return; // A thread is already running

		boat = new Boat("The Fighter", initialXAxisBoatLocation, initialYAxisBoatLocation, 400);
		oceanGrid = new OceanGrid();
		matrix = oceanGrid.getOceanMatrix();
		thread = new Thread(this); // Create a worker thread
		thread.start();

		oilSpill = new OilSpill(oceanGrid);
		thread1 = new Thread(oilSpill);//controls OilSpill
		thread1.start();

	}

}
