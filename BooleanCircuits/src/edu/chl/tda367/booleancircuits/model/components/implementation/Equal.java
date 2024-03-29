package edu.chl.tda367.booleancircuits.model.components.implementation;

/**
 * A gate which output value is a copy of its input value.
 * 
 * @author Kaufmann
 * 
 */
public final class Equal extends AbstractCircuitGate {

	/**
	 * Creates a new EqualGate.
	 */
	public Equal() {
		super(1, 1);
	}

	@Override
	public String toString() {
		return "EQUAL";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new Equal();
	}

	@Override
	protected void updateOutput() {
		super.setOutput(0, super.getInputs().get(0).getInputValue());
	}

}
