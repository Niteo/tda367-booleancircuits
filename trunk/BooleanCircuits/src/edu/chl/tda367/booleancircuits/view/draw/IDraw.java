package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Graphics;

/**
 * A drawing interface.
 * 
 * @author Boel
 * 
 */
public interface IDraw {

	/**
	 * Draws a background.
	 * @param g graphics
	 */
	public void drawBackground(Graphics g);

	/**
	 * Draws a gate.
	 * @param g graphics
	 */
	public void drawGate(Graphics g);
	
}
