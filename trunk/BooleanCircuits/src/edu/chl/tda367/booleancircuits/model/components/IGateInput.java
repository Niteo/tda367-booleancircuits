package edu.chl.tda367.booleancircuits.model.components;


public interface IGateInput {

	/**
	 * Returns the gate connected to the port.
	 *
	 * @return the connected gate
	 */
	public ICircuitGate getInputComponent();

	/**
	 * Returns the port connected to.
	 *
	 * @return int: the connected port
	 */
	public int getInputPort();

	/**
	 * Connects a gate to a port.
	 *
	 * @param component
	 *            the gate to connect
	 * @param port
	 *            the port to connect to
	 */
	public void setInputComponent(ICircuitGate component, int port);

	/**
	 * Returns the output of the connected component.
	 *
	 * @return boolean: output
	 */
	public boolean getInputValue();

	/**
	 * Resets the input so that it is not connected to anything.
	 */
	public void reset();
}
