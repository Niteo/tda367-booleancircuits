package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Point;

public interface IConnection {
	
	/**
	 * Returns the start point of the connection.
	 *
	 * @return Point
	 */
	public Point getStartPoint();

	/**
	 * Returns the end point of the connection.
	 *
	 * @return Point
	 */
	public Point getEndPoint();

	/**
	 * Returns the value of the connection.
	 *
	 * @return boolean
	 */
	public boolean getValue();

	/**
	 * Returns the index of the end port.
	 *
	 * @return int
	 */
	public int getEndPortIndex();

	/**
	 * Returns the number of ports of the end component.
	 *
	 * @return number of ports
	 */
	public int getNoOfEndPorts();

	/**
	 * Returns the number of ports of the start component.
	 * @return number of ports
	 */
	public int getNoOfStartPorts();
	
	/**
	 * Returns the index of the start port.
	 *
	 * @return int
	 */
	public int getStartPortIndex();
}
