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
		super.createInputs(inputs);
		super.createOutputs(1);
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
}
