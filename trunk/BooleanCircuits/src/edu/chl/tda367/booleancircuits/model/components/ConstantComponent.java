package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;


public class ConstantComponent extends AbstractCircuitComponent {
	private boolean constant;
	
	public ConstantComponent(boolean value){
		constant = value;
		super.createOutputs(2);
	}
	
	@Override
	public void updateOutput() {
		super.setOutput(0, constant);
	}
	
}
