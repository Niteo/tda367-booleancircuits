package edu.chl.tda367.booleancircuits.model.components.implementation;

public class Clock extends AbstractCircuitGate {
	private boolean constant;

	public Clock() {
		super(0, 1);
	}

	public void toggleClock() {
		constant = !constant;
	}

	@Override
	public String toString() {
		return "CLOCK";
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		Clock c = new Clock();
		c.constant = this.constant;
		return c;
	}

	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}
}