package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.*;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

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
	public void drawGate(Graphics2D g, ICircuitGate gate, Point offset);

	/**
	 * Draws a gate's connections.
	 * 
	 * @param g
	 *            Graphics2D object to draw with
	 * @param gate
	 *            the gate to draw
	 * @param offset
	 *            Point offset values
	 */
	public void drawGateConnections(Graphics2D g, ICircuitGate gate,
			Point offset);

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
