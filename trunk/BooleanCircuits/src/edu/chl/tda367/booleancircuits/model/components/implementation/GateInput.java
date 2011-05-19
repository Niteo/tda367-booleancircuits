package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents input-ports in a gate.
 *
 * @author Kaufmann
 *
 */
public final class GateInput implements IGateInput {
	private ICircuitGate inputComponent;
	private int inputComponentPort;

	/**
	 * Returns an instance of GateInput.
	 */
	public GateInput() {
		inputComponent = null;
		inputComponentPort = 0;
	}

	@Override
	public ICircuitGate getInputComponent() {
		return inputComponent;
	}

	@Override
	public int getInputPort() {
		return inputComponentPort;
	}

	@Override
	public void setInputComponent(ICircuitGate component, int port) {
		this.inputComponent = component;
	}

	@Override
	public boolean getInputValue() {
		if (inputComponent == null) {
			return false;
		} else {
			return inputComponent.getOutputValue(inputComponentPort);
		}
	}

	@Override
	public void reset() {
		inputComponent = null;
		inputComponentPort = 0;
	}
}
