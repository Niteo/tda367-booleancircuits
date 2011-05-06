package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

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
	 * @param offset
	 *            Point offset values
	 * @param canvasSize
	 *            Dimension of the canvas
	 */
	public void drawBackground(Graphics2D g, Point offset, Dimension canvasSize);

	/**
	 * Draws a gate.
	 * 
	 * @param g
	 *            Graphics2D object to draw with
	 * @param gate
	 *            the gate to draw
	 * @param offset
	 *            Point offset values
	 */
	public void drawGate(Graphics2D g, IAbstractCircuitGate gate, Point offset);

	/**
	 * Sets the background
	 * 
	 * @param background
	 *            IBackground
	 */
	public void setBackground(IBackground background);
	

	/**
	 * sets the standard to the US standard
	 */
	public void setUsStandard(boolean standard);
	
}
