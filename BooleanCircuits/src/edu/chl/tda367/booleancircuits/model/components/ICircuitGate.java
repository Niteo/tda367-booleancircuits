package edu.chl.tda367.booleancircuits.model.components;

import java.util.Collection;
import java.util.List;

public interface ICircuitGate{

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
	 * Returns true if gate is connected to the specified gate
	 *
	 * @param gate
	 *            the gate specified
	 * @throws IllegalArgumentException
	 */
	public boolean connectsTo(ICircuitGate gate)
			throws IllegalArgumentException;

	/**
	 * Calculates the component's tier.
	 *
	 * @return the tier of the component
	 */
	public int getComponentTier();

	/**
	 * Retrieves the inputs of the component
	 *
	 * @return inputs of the component
	 */
	public List<IGateInput> getInputs();

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
	 * Returns the outputs.
	 *
	 * @return boolean
	 */
	public abstract boolean[] getOutputs();

	/**
	 * Retrieves the value of a specific port on this component
	 *
	 * @param index
	 *            the port to retrieve from
	 * @return port boolean value
	 */
	public boolean getOutputValue(int index);

	/**
	 * Returns all components this component is recoupled to.
	 *
	 * @return components recoupled to
	 */
	public Collection<ICircuitGate> getRecoupledTo();

	/**
	 * Overwrites gate with another one, copying all properties.
	 *
	 * @param gate
	 *            the gate to overwrite with
	 */
	public void overwriteGate(ICircuitGate gate);

	/**
	 * Returns the name of the gate.
	 */
	@Override
	public abstract String toString();

	/**
	 * Updates this components output based on its input.
	 *
	 * @return boolean true if output changed
	 */
	public boolean update();
	
	/**
	 * Checks whether the gate has connected all inputs.
	 * @return true if all inputs are connected.
	 */
	public boolean isFullyConnected();
}
