package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * Represents a constant value, determined at creation. This class uses no
 * inputs.
 * 
 * @author Kaufmann
 * 
 */
public class ConstantGate extends AbstractCircuitGate {
	private boolean constant;

	/**
	 * Returns an instance of a constant gate.
	 * 
	 * @param value
	 *            the boolean value that the gate will output
	 */
	public ConstantGate(boolean value) {
		constant = value;
		super.createInputs(0);
		super.createOutputs(1);
	}

	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}

}
