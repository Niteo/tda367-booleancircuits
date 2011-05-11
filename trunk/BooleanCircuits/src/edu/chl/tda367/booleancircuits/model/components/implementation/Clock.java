package edu.chl.tda367.booleancircuits.model.components.implementation;

public class Clock extends AbstractCircuitGate{
	private boolean value;
	
	public Clock(){
		super.createIO(0, 1);
	}
	
	@Override
	protected void updateOutput() {
		super.setOutput(0, value);
	}
	
	public void toggleClock(){
		value = !value;
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