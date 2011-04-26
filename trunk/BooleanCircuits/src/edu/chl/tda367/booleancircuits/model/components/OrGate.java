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
	protected void updateOutput() {
		boolean output = false;
		for (GateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				output = true;
				break;
			}
		}
		super.setOutput(0, output);
	}
	
	@Override
	public String toString(){
		return "OR";
	}
}
