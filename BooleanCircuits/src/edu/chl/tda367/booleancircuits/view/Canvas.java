package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A class where the components are drawn.
 * 
 * @author Boel
 * 
 */
public class Canvas {

	private int x;
	private int y;

	private JPanel panel;

	public Canvas() {

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 60, 50);
				g.drawString("Yo", x, y);
			}
		};

		panel.setBackground(Color.WHITE);

	}

	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}

	/**
	 * updates canvas.
	 * 
	 * @param x
	 *            , X-coordinate for mouse location
	 * @param y
	 *            , Y-coordinate for mouse location
	 */
	public void update(int x, int y) {
		this.x = x;
		this.y = y;

		panel.repaint();
	}
}
