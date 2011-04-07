package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * 
 * @author Jennifer Represents an AND-gate Creates input and outputs and updates
 *         the output
 */
public final class AndGate extends AbstractCircuitGate {

	/**
	 * A new AND-gate is created depending on the number of inputs
	 * 
	 * @param Number
	 *            of inputs to the AND-gate
	 */
	public AndGate(int noOfInputs) {
		super.createInputs(noOfInputs);
		super.createOutputs(1);
	}

	/**
	 * sets the output depending on the inputs to a AND-component
	 */
	@Override
	protected void updateOutput() {
		List<ComponentInput> input = super.getInputs();

		for (ComponentInput i : input) {
			if (i.getInputValue() == false) {
				super.setOutput(0, false);
			}
			super.setOutput(0, true);
		}

	}
}
