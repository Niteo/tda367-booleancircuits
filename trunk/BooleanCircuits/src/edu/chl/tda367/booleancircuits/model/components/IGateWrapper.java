package edu.chl.tda367.booleancircuits.model.components;

import edu.chl.tda367.booleancircuits.common.IPositionable;

public interface IGateWrapper extends ICircuitGate, IPositionable {

	/**
	 * Returns a clone of a ICircuitGate.
	 *
	 * @return ICircuitGate
	 */
	public ICircuitGate getGateClone();

	/**
	 * Returns the ICircuitGate
	 *
	 * @return ICircuitGate
	 */
	public ICircuitGate getGate();

	/**
	 * Returns the name of the gate.
	 */
	@Override
	public String toString();

}
