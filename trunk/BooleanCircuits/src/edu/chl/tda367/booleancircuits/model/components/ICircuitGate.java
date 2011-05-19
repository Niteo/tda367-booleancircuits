package edu.chl.tda367.booleancircuits.model.components;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

public interface ICircuitGate {

	/**
	 * Returns true if gate is connected to the specified gate
	 *
	 * @param gate
	 *            the gate specified
	 * @throws IllegalArgumentException
	 */
	public boolean connectsTo(ICircuitGate gate)
			throws IllegalArgumentException;

	/**
	 * Returns a list of all components this component is recoupled to.
	 *
	 * @return a list of components recoupled to
	 */
	public Collection<ICircuitGate> getRecoupledTo();

	/**
	 * Retrieves the inputs of the component
	 *
	 * @return inputs of the component
	 */
	public List<IGateInput> getInputs();

	/**
	 * Overwrites gate with another one, copying all properties.
	 *
	 * @param gate
	 *            the gate to override with
	 */
	public void overwriteGate(ICircuitGate gate);

	/**
	 * Connects a specific input of this component with a specific output of
	 * another component.
	 *
	 * @param inputPort
	 *            the input port of this component to connect
	 * @param component
	 *            the component to connect this component to
	 * @param outputPort
	 *            the output port to connect to
	 */
	public void connectInput(int inputPort, ICircuitGate component,
			int outputPort);

	/**
	 * Retrieves the value of a specific port on this component
	 *
	 * @param index
	 *            the port to retrieve from
	 * @return
	 */
	public boolean getOutputValue(int index);

	/**
	 * Gets the number of inputs
	 *
	 * @return number of inputs
	 */
	public int getNoOfInputs();

	/**
	 * Gets the number of outputs
	 *
	 * @return number of outputs
	 */
	public int getNoOfOutputs();

	/**
	 * Updates this components output based on its input.
	 *
	 * @return boolean true if output changed
	 */
	public boolean update();

	/**
	 * Calculates the component's tier.
	 *
	 * @return the tier of the component
	 */
	public int getComponentTier();

	/**
	 * Gets the position of the gate
	 *
	 * @return a reference to the position of the gate
	 */
	public Point getPosition();

	/**
	 * Sets the position of the gate
	 *
	 * @param coordinates
	 *            of the gate
	 */
	public void setPosition(Point position);

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
	 * Returns a copy of the gate.
	 */
	public ICircuitGate clone();

	/**
	 * Returns the name of the gate.
	 */
	@Override
	public abstract String toString();

	/**
	 * Returns the outputs.
	 *
	 * @return boolean
	 */
	public abstract boolean[] getOutputs();
}
