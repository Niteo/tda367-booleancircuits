package edu.chl.tda367.booleancircuits.model.components;

import edu.chl.tda367.booleancircuits.model.components.*;

/**
 * Class representing a XNOR gate
 * @author Kaufmann
 *
 */
public final class XnorGate extends AbstractCircuitGate {

	/**
	 * Returns an instance of a XnorGate
	 * @param inputs amount of inputs for the gate
	 */
	public XnorGate(int inputs){
		super.createIO(inputs, 1);
	}

	@Override
	protected void updateOutput() {
		int nPositives = 0;
		for (GateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				if(++nPositives > 1){
					break;
				}
			}
		}

		super.setOutput(0, nPositives != 1);
	}
	
	@Override
	public String toString(){
		return "XNOR";
	}

	@Override
	public AbstractCircuitGate clone() {
		XnorGate gate = new XnorGate(this.getNoOfInputs());
		gate.setOutput(0, this.getOutputValue(0));
		int port = 0;
		for(GateInput gi : this.getInputs()){
			gate.connectInput(port++, gi.getInputComponent(), gi.getInputPort());
		}
		
		return gate;
	}
}
