package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * 
 * @author Jennifer Represents an AND-gate Creates input and outputs and updates
 *         the output
 */
public class AndGate extends AbstractCircuitGate {

	public AndGate(int noOfInputs) {
		super.createInputs(noOfInputs);
		super.createOutputs(1);
	}

	/**
	 * sets the output depending on the inputs to a AND-component
	 */
	@Override
	public void updateOutput() {
		List<ComponentInput> input = super.getInputs();

		super.setOutput(0, input.get(0).getInputValue()
				&& input.get(1).getInputValue());

	}
}
