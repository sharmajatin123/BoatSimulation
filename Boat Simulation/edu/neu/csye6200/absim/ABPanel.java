package edu.neu.csye6200.absim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ABPanel extends JPanel implements Observer {

	//Initial Matrix is provided to Panel
	public int[][] matrix = { { -2, 0, 0, 0, -1, -1, -1, 0 }, { 0, 0, 0, 0, -1, -1, -1, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0 },{ 0, 0, 0, 0, 100, 100, 0, 0 }, 
			{ 0, 0, 0, 0, 100, 100, 0, 0 }, { -1, -1, -1, -1, 0, 0, 0, 0 },
			{ -1, -1, -1, -1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

	/**
	 * 
	 */
	private static final long serialVersionUID = 1114191735225446630L;
	/**
	 * 
	 */
	OceanGrid ocean;

	private ABSimulation abSimulation;

	public ABPanel() {

	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		drawCanvas(g);
	}

	private void drawCanvas(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		for (int y = 0; y < matrix[0].length; y++) {
			for (int x = 0; x < matrix.length; x++) {

				switch (matrix[x][y]) {

				case -1:
					g2d.setColor(Color.green);
					g2d.fillRect(y * 30, x * 30, 30, 30);
					break;
				case -2:
					g2d.setColor(Color.yellow);
					g2d.fillRect(y * 30, x * 30, 30, 30);
					break;
				case 0:
					g2d.setColor(Color.blue);
					g2d.fillRect(y * 30, x * 30, 30, 30);
					break;
				default:
					g2d.setColor(Color.darkGray);
					g2d.fillRect(y * 30, x * 30, 30, 30);
				}
				
			}
		}
	}

	@Override
	public void update(Observable o, Object matrix1) {
		matrix = (int[][]) matrix1;
		repaint();// Paint GUI again
	}

}
