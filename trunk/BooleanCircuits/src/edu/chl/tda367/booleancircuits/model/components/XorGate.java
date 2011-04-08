package edu.chl.tda367.booleancircuits.model.components;

/**
 * Class to represent a XOR gate.
 * @author Kaufmann
 *
 */
public final class XorGate extends AbstractCircuitGate {
	/**
	 * Returns an instance of a XorGate.
	 * @param inputs number of inputs of the gate
	 */
	public XorGate(int inputs){
		super.createOutputs(1);
		super.createInputs(inputs);
	}

	@Override
	protected void updateOutput() {
		int nPositives = 0;
		for (GateInput c : super.getInputs()) {
			if (c.getInputValue()) {
				if(++nPositives > 1)
					break;
			}
		}

		super.setOutput(0, nPositives == 1);
	}

}