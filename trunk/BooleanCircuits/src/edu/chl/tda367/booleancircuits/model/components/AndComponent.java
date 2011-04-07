package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;


public class AndComponent extends AbstractCircuitComponent{
	
	public AndComponent(){
		super.createInputs(2);
		super.createOutputs(1);
	}

	@Override
	public void updateStrategy() {
		List<ComponentInput> input = super.getInputs();
		super.setOutput(0,
				input.get(0).getInputValue() && 
				input.get(1).getInputValue());
	}
}
