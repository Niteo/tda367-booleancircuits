package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

public final class NorGate extends AbstractCircuitGate {

	/**
	 * Represents a NOR gate.
	 * 
	 * @param inputs
	 *            the amount of inputs for the gate
	 */
	public NorGate(final int inputs) {
		super(inputs, 1);
	}

	@Override
	public String toString() {
		return "NOR";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new NorGate(getNoOfInputs());
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
}