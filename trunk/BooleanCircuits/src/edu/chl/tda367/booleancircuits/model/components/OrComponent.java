package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * Represents a OR-gate
 * 
 * @author Jennifer
 * 
 */
public class OrComponent extends AbstractCircuitGate {

	public OrComponent() {
		super.createInputs(2);
		super.createOutputs(1);
	}

	@Override
	public void updateOutput() {

		List<ComponentInput> input = super.getInputs();
		super.setOutput(0, input.get(0).getInputValue()
				|| input.get(1).getInputValue());
	}

}
