package edu.neu.csye6200.absim;

public class ABRule {
	
	private static int ctr1 = 1;//Controls Boat Path Till Oil Shortest Path, Row then Column , Column then row
	private static int ctr2 = 1;//Controls Boat Speed 

	/**
	 * @return the ctr2
	 */
	public static int getCtr2() {
		return ctr2;
	}


	/**
	 * @param ctr2 the ctr2 to set
	 */
	public static void setCtr2(int ctr2) {
		ABRule.ctr2 = ctr2;
	}


	/**
	 * @return the ctr
	 */
	public static int getCtr1() {
		return ctr1;
	}
	

	/**
	 * @param ctr the ctr to set
	 */
	public static void setCtr1(int ctr) {
		ABRule.ctr1 = ctr;
	}

}
