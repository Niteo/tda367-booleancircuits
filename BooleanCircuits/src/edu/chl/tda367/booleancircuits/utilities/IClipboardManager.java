package edu.chl.tda367.booleancircuits.utilities;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;

/**
 * An interface that represents a clipboard manager.
 * 
 * @author Boel
 * 
 */
public interface IClipboardManager {

	/**
	 * A list of the objects to copy.
	 * 
	 * @param gate
	 *            IAbstractCircuitGate
	 */
	public void copy(List<IAbstractCircuitGate> gate);

	/**
	 * Returns a list of objects to be pasted.
	 * 
	 * @return IAbstractCircuitGate
	 */
	public List<IAbstractCircuitGate> paste();

	/**
	 * Returns a list of components that were last pasted
	 * 
	 * @return IAbstractCircuitGate
	 */
	public List<IAbstractCircuitGate> getLastPastedComponents();

}
