package edu.chl.tda367.booleancircuits.common;

import java.awt.Point;

public interface IPositionable {

	/**
	 * Gets the position of the gate
	 * 
	 * @return a reference to the position of the gate
	 */
	public Point getPosition();

	/**
	 * updates the position of the gate
	 * 
	 * @param deltaX
	 *            : the difference in the x-axis between the current position
	 *            and the old position
	 * @param deltaY
	 *            : the difference in the y-axis between the current position
	 *            and the old position
	 */
	public void move(int deltaX, int deltaY);

	/**
	 * Sets the position of the gate
	 * 
	 * @param coordinates
	 *            of the gate
	 */
	public void setPosition(Point position);

}
