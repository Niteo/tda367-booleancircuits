package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * Represents an AND-gate Creates input and outputs and updates the output
 * 
 * @author Jennifer
 */
public final class AndGate extends AbstractCircuitGate {

	/**
	 * A new AND-gate is created depending on the number of inputs
	 * 
	 * @param inputs
	 *            Number of inputs to the AND-gate
	 */
	public AndGate(int inputs) {
		super.createIO(inputs, 1);
	}

	/**
	 * sets the output depending on the inputs to a AND-component
	 */
	@Override
	protected void updateOutput() {
		List<GateInput> input = super.getInputs();

		for (GateInput i : input) {
			if (i.getInputValue() == false) {
				super.setOutput(0, false);
			}
			super.setOutput(0, true);
		}
	}
	
	
	@Override
	public String toString(){
		return "AND";
	}

	@Override
	public AndGate clone() {
		AndGate gate = new AndGate(this.getNoOfInputs());
		gate.setOutput(0, this.getOutputValue(0));
		int port = 0;
		for(GateInput gi : this.getInputs()){
			gate.connectInput(port++, gi.getInputComponent(), gi.getInputPort());
		}
		
		return gate;
	}
}
