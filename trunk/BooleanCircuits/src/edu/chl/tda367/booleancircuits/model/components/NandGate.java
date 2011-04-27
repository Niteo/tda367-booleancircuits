/**
 * 
 */
package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

/**
 * A class representing a NANDgate.
 * @author Kaufmann
 */
public final class NandGate extends AbstractCircuitGate {

	/**
	 * Creates an instance of a NAND-gate.
	 * 
	 * @param inputs
	 *            number of inputs of the gate
	 */
	public NandGate(int inputs){
		super.createIO(inputs, 1);
	}
	
	@Override
	protected void updateOutput() {
		List<GateInput> input = super.getInputs();

		for (GateInput i : input) {
			if (i.getInputValue() == false) {
				super.setOutput(0, true);
			}
			super.setOutput(0, false);
		}
	}
	
	@Override
	public String toString(){
		return "NAND";
	}
	
	@Override
	protected AbstractCircuitGate emptyGateClone() {
		return new NandGate(getNoOfInputs());
	}
}