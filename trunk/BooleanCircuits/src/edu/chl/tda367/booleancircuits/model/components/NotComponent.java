/**
 * 
 */
package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * @author Jennifer
 * represents a NOT-gate
 * Creates input and outputs and updates the output
 */
public class NotComponent extends AbstractCircuitGate {
	public NotComponent(){
		super.createInputs(1);
		super.createOutputs(1);
	}

/**
 * sets the output depending on the inputs to a NOT-component
 */
	@Override
	public void updateOutput() {
		List<ComponentInput> input = super.getInputs();
		if(input.get(1).getInputValue()==true){
			super.setOutput(1, false);
		}
			super.setOutput(1, true);		
		
	}
}
