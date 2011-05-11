package edu.chl.tda367.booleancircuits.model.components.implementation;

public class Clock extends AbstractCircuitGate{
	private boolean constant;
	
	public Clock(){
		super.createIO(0, 1);
		System.out.println("NEW ASSCLOCKI");
	}
	
	@Override
	protected void updateOutput() {
		super.setOutput(0, constant);
	}
	
	public void toggleClock(){
		this.constant = true;
	}

	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new Clock();
	}
	
	@Override
	public String toString() {
		return "Clock";
	}
}