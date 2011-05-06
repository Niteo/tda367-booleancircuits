package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

public final class NorGate extends AbstractCircuitGate {

	/**
	 * Represents a NOR gate.
	 * 
	 * @param inputs
	 *            the amount of inputs for the gate
	 */
	public NorGate(int inputs) {
		super.createIO(inputs, 1);
	}

	@Override
	protected void updateOutput() {
		boolean output = false;
		for (IGateInput c : super.getInputs()) {
			if (c.getInputValue() == true) {
				output = true;
				break;
			}
		}

		super.setOutput(0, !output);
	}
	
	@Override
	public String toString(){
		return "NOR";
	}
	
	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new NorGate(getNoOfInputs());
	}
}