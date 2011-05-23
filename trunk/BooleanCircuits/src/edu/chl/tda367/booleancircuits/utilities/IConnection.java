package edu.chl.tda367.booleancircuits.utilities;

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
	 * Returns the number of end ports.
	 *
	 * @return int
	 */
	public int getNoOfEndPorts();

}
