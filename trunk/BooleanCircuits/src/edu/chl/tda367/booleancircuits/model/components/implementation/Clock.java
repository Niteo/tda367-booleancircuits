package edu.chl.tda367.booleancircuits.model.components.implementation;

public class Clock extends AbstractCircuitGate{
	private boolean constant;
	
	public Clock(){
		super.createIO(0, 1);
	}
	
	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}
	
	public void toggleClock(){
		constant = !constant;
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		Clock c = new Clock();
		c.constant = this.constant;
		return c;
	}
	
	@Override
	public String toString() {
		return "CLOCK";
	}
}