/**
 * 
 */
package edu.chl.tda367.booleancircuits.model.components;

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
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString(){
		return "NAND";
	}

}
