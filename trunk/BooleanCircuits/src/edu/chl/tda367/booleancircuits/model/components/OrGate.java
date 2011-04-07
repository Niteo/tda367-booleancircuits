package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

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
	public OrGate(int inputs) {
		super.createInputs(inputs);
		super.createOutputs(1);
	}

	@Override
	public void updateOutput() {
		List<ComponentInput> input = super.getInputs();

		boolean output = false;
		for (ComponentInput c : input) {
			if (c.getInputValue() == true) {
				output = true;
				break;
			}
		}

		super.setOutput(0, output);
	}
}
