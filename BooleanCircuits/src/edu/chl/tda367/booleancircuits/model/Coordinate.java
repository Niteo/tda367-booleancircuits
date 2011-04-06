package edu.chl.tda367.booleancircuits.model;

/**
 * Simple class representing a coordinate.
 * @author Kaufmann
 */
public class Coordinate {
	public int x;
	public int y;
	
	/**
	 * Constructor for Coordinate.
	 * @param x position on the X-axis
	 * @param y position on the Y-axis
	 */
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x-position of the coordinate
	 * @return an integer representing the x-position
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the y-position of the coordinate
	 * @return an integer representing the y-position
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Clones the current coordinate.
	 * @return a clone of the current coordinate.
	 */
	@Override
	public Coordinate clone(){
		return new Coordinate(x, y);
	}
}
