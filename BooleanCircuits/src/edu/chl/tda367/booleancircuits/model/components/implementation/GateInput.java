package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents input-ports in a gate.
 * @author Kaufmann
 *
 */
public final class GateInput implements IGateInput {
	private ICircuitGate inputComponent;
	private int inputComponentPort;
	
	/**
	 * Returns an instance of GateInput.
	 */
	public GateInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}

	public ICircuitGate getInputComponent(){
		return inputComponent;
	}

	public int getInputPort(){
		return inputComponentPort;
	}
	
	public void setInputComponent(ICircuitGate component, int port){
		this.inputComponent = component;
	}
	
	public boolean getInputValue(){
		if(inputComponent == null){
			return false;
		} else {
			return inputComponent.getOutputValue(inputComponentPort);
		}
	}
}
