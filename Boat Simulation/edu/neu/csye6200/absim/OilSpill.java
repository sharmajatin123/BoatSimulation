package edu.neu.csye6200.absim;

/**
 * Basically This class will update oil spill after every 4 seconds
 * @author jshar
 *
 */
public class OilSpill implements Runnable{
	
	public boolean start;
	
	OceanGrid oceanGrid;

	public OilSpill(OceanGrid oceanGrid) {
		start = true;
		this.oceanGrid = oceanGrid;
	}

	@Override
	public void run() {
		
		while(start) {
			
			try {
				Thread.sleep(4000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oceanGrid.startSpillingOil();
		}
		
		
		
	}

}
