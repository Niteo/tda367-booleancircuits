package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Represents an AND-gate Creates input and outputs and updates the output
 * 
 * @author Jennifer
 */
public final class AndGate extends AbstractCircuitGate {

	/**
	 * A new AND-gate is created depending on the number of inputs
	 * 
	 * @param inputs
	 *            Number of inputs to the AND-gate
	 */
	public AndGate(int inputs) {
		super(inputs, 1);
	}

	/**
	 * sets the output depending on the inputs to a AND-component
	 */
	@Override
	protected void updateOutput() {
		List<IGateInput> input = super.getInputs();

		for (IGateInput i : input) {
			if (i.getInputValue() == false) {
				super.setOutput(0, false);
				return;
			}
			super.setOutput(0, true);
		}
	}
	
	
	@Override
	public String toString(){
		return "AND";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new AndGate(getNoOfInputs());
	}
}
