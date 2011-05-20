package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents a OR-gate
 * 
 * @author Jennifer
 * 
 */
public class OrGate extends AbstractCircuitGate {

	/**
	 * Creates an instance of an OR-gate.
	 * 
	 * @param inputs
	 *            number of inputs of the gate
	 */
	public OrGate(final int inputs) {
		super(inputs, 1);
	}

	@Override
	public String toString() {
		return "OR";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new OrGate(getNoOfInputs());
	}

	@Override
	protected void updateOutput() {
		boolean output = false;
		for (IGateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				output = true;
				break;
			}
		}
		super.setOutput(0, output);
	}
}
