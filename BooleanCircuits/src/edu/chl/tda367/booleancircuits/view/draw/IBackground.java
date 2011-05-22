package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.*;

/**
 * Strategy interface for drawing backgrounds
 * 
 * @author Kaufmann
 * 
 */
public interface IBackground {
	/**
	 * Draws the background
	 * 
	 * @param g
	 *            graphics object to draw with
	 * @param offset
	 *            offset value
	 * @param canvasSize
	 *            size of the area to draw
	 */
	public void draw(Graphics g, Point offset, Dimension canvasSize);
}
