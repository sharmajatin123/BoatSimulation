package edu.neu.csye6200.absim;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NameApp extends ABApp {
	
		//Creating Panel, Button, ComboBox Object
		private JPanel northPanel;
		private JButton startBtn;
		private JButton stopBtn;
		
		private JComboBox<String> comboBox;
		
		private JComboBox<String> comboBox1;
		
		private ABPanel abPanel;
		
		private static ABSimulation abSim;
		
		private ABRule abRule;
		

	public NameApp() {
		
		frame.setSize(600,600);
		frame.setBackground(Color.gray);
		
		init();
		
		showUI();
		
		abSim.addObserver(abPanel);
	}
	
	//Calling ABSimulation CLass
	public void init() {
		abSim = new ABSimulation();
	}

	
	public static void main(String[] args) {
		
		new NameApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	//NORTH PANEL
	@Override
	public JPanel getNorthPanel() {
		northPanel = new JPanel(); // Create a small canvas
		northPanel.setLayout(new FlowLayout()); // Flow controls

		startBtn = new JButton("Start");
		startBtn.addActionListener(this); // Make my application listen to the button
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start pressed");
				abSim.startSim();
			}
		});
		
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(this);
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop pressed");
				abSim.stopSim();
			}
		});
		
		//Boat Movement ComboBox
		comboBox = new JComboBox();
		comboBox.addItem("Shortest Path");
		comboBox.addItem("First Row Then Column");
		comboBox.addItem("First Column Then Row");
		
		 comboBox.addActionListener(new ActionListener() {//add actionlistner to listen for change
	            @Override
	            public void actionPerformed(ActionEvent e) {    
	                String s = (String) comboBox.getSelectedItem();//get the selected item    
	                switch (s) {//check for a match
	                    case "Shortest Path":
	                        abRule.setCtr1(1);                            
	                        break;
	                    case "First Row Then Column":
	                    	abRule.setCtr1(2);                            
	                        break;
	                    case "First Column Then Row":
	                    	abRule.setCtr1(3);
	                        break;                        
	                }
	            }
	        });
		
		
		 //Boat Speed Combo Box
		comboBox1 = new JComboBox();
		comboBox1.addItem("20 mph");
		comboBox1.addItem("40 mph");
		
		comboBox1.addActionListener(new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {    
                String s = (String) comboBox1.getSelectedItem();//get the selected item    
                switch (s) {//check for a match
                    case "20 mph":
                        abRule.setCtr2(1);                            
                        break;
                    case "40 mph":
                    	abRule.setCtr2(2);                            
                        break;                       
                }
            }
        });
		
		
	
	// Lay out the panel	
		northPanel.add(startBtn);
		northPanel.add(stopBtn);
		
		northPanel.add(new JLabel("Boat Path Till Oil"));
		northPanel.add(comboBox);
		
		northPanel.add(new JLabel("Boat Speed"));
		northPanel.add(comboBox1);
		
		northPanel.setBackground(Color.gray);	
		return northPanel;
	}

	@Override
	public JPanel getCenterPanel() {
		
		abPanel = new ABPanel();
		return abPanel;
	}

}
