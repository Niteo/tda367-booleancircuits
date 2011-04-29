package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Graphics;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * A drawing interface.
 * 
 * @author Boel
 * 
 */
public interface IDraw {

	/**
	 * Draws a background.
	 * 
	 * @param g
	 *            graphics
	 */
	public void drawBackground(Graphics g, int offsetX, int offsetY);

	/**
	 * Draws a gate.
	 * 
	 * @param g
	 *            graphics object to draw with
	 * @param gate
	 *            the gate to draw
	 * @param offsetX
	 *            offset on X-axis
	 * @param offsetY
	 *            offset on Y-axis
	 */
	public void drawGate(Graphics g, AbstractCircuitGate gate, int offsetX,
			int offsetY);

	/**
	 * Sets the background
	 * 
	 * @param background
	 *            IBackground
	 */
	public void setBackground(IBackground background);
}
