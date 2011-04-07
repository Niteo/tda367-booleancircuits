package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;


public class ConstantGate extends AbstractCircuitGate {
	private boolean constant;
	
	public ConstantGate(boolean value){
		constant = value;
		super.createOutputs(2);
	}
	
	@Override
	public void updateOutput() {
		super.setOutput(0, constant);
	}
	
}
