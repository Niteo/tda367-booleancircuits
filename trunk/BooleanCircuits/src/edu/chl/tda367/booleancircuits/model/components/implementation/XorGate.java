package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Class to represent a XOR gate.
 * 
 * @author Kaufmann
 * 
 */
public final class XorGate extends AbstractCircuitGate {
	/**
	 * Returns an instance of a XorGate.
	 * 
	 * @param inputs
	 *            number of inputs of the gate
	 */
	public XorGate(final int inputs) {
		super(inputs, 1);
	}

	@Override
	public String toString() {
		return "XOR";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new XorGate(getNoOfInputs());
	}

	@Override
	protected void updateOutput() {
		int nPositives = 0;
		for (IGateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				if (++nPositives > 1) {
					break;
				}
			}
		}

		super.setOutput(0, nPositives == 1);
	}
}