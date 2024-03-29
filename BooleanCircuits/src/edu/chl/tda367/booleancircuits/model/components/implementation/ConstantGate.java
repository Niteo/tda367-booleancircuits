package edu.chl.tda367.booleancircuits.model.components.implementation;

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
	public ConstantGate(final boolean value) {
		super(0, 1);
		constant = value;
	}

	@Override
	public String toString() {
		return "" + (constant ? 1 : 0);
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new ConstantGate(constant);
	}

	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}
}