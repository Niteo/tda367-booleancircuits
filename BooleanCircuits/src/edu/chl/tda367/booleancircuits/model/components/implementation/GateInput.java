package edu.chl.tda367.booleancircuits.model.components.implementation;

/**
 * Represents input-ports in a gate.
 * @author Kaufmann
 *
 */
public final class GateInput {
	private AbstractCircuitGate inputComponent;
	private int inputComponentPort;
	
	/**
	 * Returns an instance of GateInput.
	 */
	public GateInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}
	
	/**
	 * Returns the gate connected to the port.
	 * @return the connected gate
	 */
	public AbstractCircuitGate getInputComponent(){
		return inputComponent;
	}
	
	/**
	 * Returns the port connected to.
	 * @return the connected port
	 */
	public int getInputPort(){
		return inputComponentPort;
	}
	
	/**
	 * Connects a gate to a port.
	 * @param component the gate to connect
	 * @param port the port to connect to
	 */
	public void setInputComponent(AbstractCircuitGate component, int port){
		this.inputComponent = component;
	}
	
	/**
	 * Returns the output of the connected component.
	 * @return output
	 */
	public boolean getInputValue(){
		return inputComponent.getOutputValue(inputComponentPort);
	}
}
