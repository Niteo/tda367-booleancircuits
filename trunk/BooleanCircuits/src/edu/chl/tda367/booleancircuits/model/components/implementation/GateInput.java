package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents input-ports in a gate.
 * @author Kaufmann
 *
 */
public final class GateInput implements IGateInput {
	private AbstractCircuitGate inputComponent;
	private int inputComponentPort;
	
	/**
	 * Returns an instance of GateInput.
	 */
	public GateInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}

	public AbstractCircuitGate getInputComponent(){
		return inputComponent;
	}

	public int getInputPort(){
		return inputComponentPort;
	}
	
	public void setInputComponent(AbstractCircuitGate component, int port){
		this.inputComponent = component;
	}
	
	public boolean getInputValue(){
		return inputComponent.getOutputValue(inputComponentPort);
	}
}
