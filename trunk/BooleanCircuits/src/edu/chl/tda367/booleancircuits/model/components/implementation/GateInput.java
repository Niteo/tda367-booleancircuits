package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents input-ports in a gate.
 * @author Kaufmann
 *
 */
public final class GateInput implements IGateInput {
	private IAbstractCircuitGate inputComponent;
	private int inputComponentPort;
	
	/**
	 * Returns an instance of GateInput.
	 */
	public GateInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}

	public IAbstractCircuitGate getInputComponent(){
		return inputComponent;
	}

	public int getInputPort(){
		return inputComponentPort;
	}
	
	public void setInputComponent(IAbstractCircuitGate component, int port){
		this.inputComponent = component;
	}
	
	public boolean getInputValue(){
		return inputComponent.getOutputValue(inputComponentPort);
	}
}
