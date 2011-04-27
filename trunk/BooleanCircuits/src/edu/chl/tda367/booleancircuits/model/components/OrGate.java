package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * Represents a OR-gate
 * 
 * @author Jennifer
 * 
 */
public class OrGate extends AbstractCircuitGate {

	/**
	 * Creates an instance of an OR-gate.
	 * 
	 * @param inputs
	 *            number of inputs of the gate
	 */
	public OrGate(int inputs) {
		super.createIO(inputs, 1);
	}

	@Override
	protected void updateOutput() {
		boolean output = false;
		for (GateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				output = true;
				break;
			}
		}
		super.setOutput(0, output);
	}
	
	@Override
	public String toString(){
		return "OR";
	}

	@Override
	public AbstractCircuitGate clone() {
		OrGate gate = new OrGate(this.getNoOfInputs());
		gate.setOutput(0, this.getOutputValue(0));
		int port = 0;
		for(GateInput gi : this.getInputs()){
			gate.connectInput(port++, gi.getInputComponent(), gi.getInputPort());
		}
		
		return gate;
	}
}
