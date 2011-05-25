package edu.chl.tda367.booleancircuits.common;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public interface ICloneableGate {

	/**
	 * Returns a clone.
	 *
	 * @return Object
	 */
	public ICircuitGate clone();

}
