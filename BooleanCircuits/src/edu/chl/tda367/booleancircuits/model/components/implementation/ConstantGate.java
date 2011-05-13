package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

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
		super.createIO(0, 1);
	}

	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}
	
	@Override
	public String toString(){
		return ""+(constant ? 1 : 0);
	}
	
	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new ConstantGate(constant);
	}
}