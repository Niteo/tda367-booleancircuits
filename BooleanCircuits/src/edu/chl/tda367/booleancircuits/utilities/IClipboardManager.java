package edu.chl.tda367.booleancircuits.utilities;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * An interface that represents a clipboard manager.
 * 
 * @author Boel
 * 
 */
public interface IClipboardManager {

	/**
	 * A list of the components to copy.
	 * 
	 * @param gate
	 *            IAbstractCircuitGate
	 */
	public void copy(List<IGateWrapper> gate);

	/**
	 * Returns a list of components that were last pasted
	 * 
	 * @return IAbstractCircuitGate
	 */
	public List<IGateWrapper> getLastPastedComponents();

	/**
	 * Returns a list of components to be pasted.
	 * 
	 * @return IAbstractCircuitGate
	 */
	public List<IGateWrapper> paste();

}
