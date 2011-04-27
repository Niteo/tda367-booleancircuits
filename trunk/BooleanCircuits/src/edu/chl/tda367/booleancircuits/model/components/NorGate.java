package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

public final class NorGate extends AbstractCircuitGate {

	/**
	 * Represents a NOR gate.
	 * 
	 * @param inputs
	 *            the amount of inputs for the gate
	 */
	public NorGate(int inputs) {
		super.createIO(inputs, 1);
	}

	@Override
	protected void updateOutput() {
		boolean output = false;
		for (GateInput c : super.getInputs()) {
			if (c.getInputValue() == true) {
				output = true;
				break;
			}
		}

		super.setOutput(0, !output);
	}
	
	@Override
	public String toString(){
		return "NOR";
	}

	@Override
	public AbstractCircuitGate clone() {
		NorGate gate = new NorGate(this.getNoOfInputs());
		gate.setOutput(0, this.getOutputValue(0));
		int port = 0;
		for(GateInput gi : this.getInputs()){
			gate.connectInput(port++, gi.getInputComponent(), gi.getInputPort());
		}
		
		return gate;
	}

}