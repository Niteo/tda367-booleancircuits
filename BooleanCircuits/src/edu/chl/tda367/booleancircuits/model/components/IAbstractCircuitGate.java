package edu.chl.tda367.booleancircuits.model.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

public interface IAbstractCircuitGate {


	/**
	 * Overwrites gate with another one, copying all properties.
	 * @param gate the gate to override with
	 */
	public void overwriteGate(AbstractCircuitGate gate);
	
	/**
	 * Connects a specific input of this component with a specific output of another component.
	 * @param inputPort the input port of this component to connect
	 * @param component the component to connect this component to
	 * @param outputPort the output port to connect to
	 */
	public void connectInput(int inputPort, AbstractCircuitGate component, int outputPort);
	
	/**
	 * Retrieves the value of a specific port on this component
	 * @param index the port to retrieve from
	 * @return 
	 */
	public boolean getOutputValue(int index);
	
	/**
	 * Gets the number of inputs
	 * @return number of inputs
	 */
	public int getNoOfInputs();
	
	/**
	 * Gets the number of outputs
	 * @return number of outputs
	 */
	public int getNoOfOutputs();
	
	/**
	 * Updates this components output based on its input.
	 */
	public void update();
	
	/**
	 * Calculates the component's tier.
	 * @return the tier of the component
	 */
	public int getComponentTier();
	
	/**
	 * Gets the position of the gate
	 * @return a reference to the position of the gate
	 */
	public Point getPosition();
	
	/**
	 * Sets the position of the gate
	 * @param coordinates of the gate
	 */
	public void setPosition(Point position);
	
	/**
	 * updates the position of the gate
	 * @param deltaX: the difference in the x-axis between the current position and the old position
	 * @param deltaY: the difference in the y-axis between the current position and the old position
	 */
	public void move(int deltaX, int deltaY);
	

	/**
	 * Returns a copy of the gate.
	 */
	public AbstractCircuitGate clone();
	
	

	
	/**
	 * Returns the name of the gate.
	 */
	public abstract String toString();
}

