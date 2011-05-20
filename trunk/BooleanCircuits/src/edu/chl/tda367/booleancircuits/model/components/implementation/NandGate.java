/**
 *
 */
package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * A class representing a NANDgate.
 * 
 * @author Kaufmann
 */
public final class NandGate extends AbstractCircuitGate {

	/**
	 * Creates an instance of a NAND-gate.
	 * 
	 * @param inputs
	 *            number of inputs of the gate
	 */
	public NandGate(final int inputs) {
		super(inputs, 1);
	}

	@Override
	public String toString() {
		return "NAND";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new NandGate(getNoOfInputs());
	}

	@Override
	protected void updateOutput() {
		List<IGateInput> input = super.getInputs();

		for (IGateInput i : input) {
			if (i.getInputValue() == false) {
				super.setOutput(0, true);
				return;
			}
			super.setOutput(0, false);
		}
	}
}