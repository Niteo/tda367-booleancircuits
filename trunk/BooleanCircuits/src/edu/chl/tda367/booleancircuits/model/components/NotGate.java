package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * Represents a NOT-gate. Outputs the inverse of the input.
 * 
 * @author Jennifer
 */
public final class NotGate extends AbstractCircuitGate {
	public NotGate() {
		super.createIO(1, 1);
	}

	/**
	 * Sets the output depending on the inputs to a NOT-component
	 */
	@Override
	protected void updateOutput() {
		List<GateInput> input = super.getInputs();
		super.setOutput(0, !input.get(0).getInputValue());
	}
	
	@Override
	public String toString(){
		return "NOT";
	}
}