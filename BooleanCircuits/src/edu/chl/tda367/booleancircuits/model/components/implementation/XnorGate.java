package edu.chl.tda367.booleancircuits.model.components.implementation;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Class representing a XNOR gate
 * 
 * @author Kaufmann
 * 
 */
public final class XnorGate extends AbstractCircuitGate {

	/**
	 * Returns an instance of a XnorGate
	 * 
	 * @param inputs
	 *            amount of inputs for the gate
	 */
	public XnorGate(final int inputs) {
		super(inputs, 1);
	}

	@Override
	public String toString() {
		return "XNOR";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new XnorGate(getNoOfInputs());
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

		super.setOutput(0, nPositives != 1);
	}
}
