package edu.neu.csye6200.absim;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


public abstract class ABApp implements ActionListener {

	protected JFrame frame;
	//protected MenuManager menuMgr;
	
	public ABApp() {
		System.out.println("Boat Simulation Starts Now");
		initGUI();
	}
	

	/**
	 * Initialize the Graphical User Interface
	 */
    public void initGUI() {
    	
    	
    	frame = new JFrame();
		frame.setTitle("Boat Simulation");

		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //JFrame.DISPOSE_ON_CLOSE)
		
		
		frame.setLayout(new BorderLayout());
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		frame.add(getCenterPanel(), BorderLayout.CENTER);
		
		
    }
 
    
    /**
     * Override this method to provide the control panel panel.
     * @return a JPanel, which contains the north content of of your application
     */
    public abstract JPanel getNorthPanel();
    
    /**
     * Override this method to provide the main content panel.
     * @return a JPanel, which contains the main content of of your application
     */
    public abstract JPanel getCenterPanel();


    /**
     * A convenience method that uses the Swing dispatch thread to show the UI.
     * This prevents concurrency problems during component initialization.
     */
    
    public void showUI() {
    	
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	frame.setVisible(true);
            	
            }
        });
    	
    }
    
    
    /**
     * Shut down the application
     */
    public void exit() {
    	frame.dispose();
    	System.exit(0);
    }

    /**
     * Override this method to show an About Dialog
     */
    public void showHelp() {
    	
    }
 
}