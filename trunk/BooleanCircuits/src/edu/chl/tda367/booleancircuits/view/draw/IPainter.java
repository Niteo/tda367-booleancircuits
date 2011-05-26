package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.*;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * A drawing interface.
 * 
 * @author Boel
 * 
 */
public interface IPainter {

	/**
	 * Draws a background.
	 * 
	 * @param g
	 *            graphics object to draw with
	 * @param offset
	 *            offset values
	 * @param canvasSize
	 *            dimension of the canvas
	 */
	public void drawBackground(Graphics2D g, Point offset, Dimension canvasSize);

	/**
	 * Draws a gate.
	 * 
	 * @param g
	 *            graphics object to draw with
	 * @param gate
	 *            the component to draw
	 * @param offset
	 *            offset values
	 */
	public void drawGate(Graphics2D g, IGateWrapper gate, Point offset);

	/**
	 * Draws a gate's connections.
	 * 
	 * @param g
	 *            graphics object to draw with
	 * @param coll
	 *            connections to draw
	 * @param offset
	 *            offset values
	 */
	public void drawGateConnections(Graphics2D g, Collection<IConnection> coll,
			Point offset);

	/**
	 * Sets the background strategy
	 * 
	 * @param background
	 *            IBackground
	 */
	public void setBackground(IBackground background);

	/**
	 * Sets if the US standard should be used.
	 * 
	 * @param value
	 *            set to true if US standard should be used
	 */
	public void setUsStandard(boolean value);

}
