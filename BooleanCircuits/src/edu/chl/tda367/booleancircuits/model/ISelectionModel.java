package edu.chl.tda367.booleancircuits.model;

import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * An interface to describe a selection model.
 * 
 * @author Boel
 * 
 */
public interface ISelectionModel {

	/**
	 * Selects all components in the collection.
	 * 
	 * @param c
	 *            Collection
	 */
	public void selectComponents(Collection<ICircuitGate> c);

	/**
	 * Selects the given component in the active workspace.
	 * 
	 * @param g
	 *            IAbstractCircuitGate the gate to select
	 * @param multiSelect
	 * 			  boolean if false, all selected components will be deselected
	 */
	public void selectComponent(ICircuitGate g, boolean multiSelect);

	/**
	 * Determinates if a component is currently selected.
	 * 
	 * @param g
	 *            AbstractCircuitGate
	 * @return boolean
	 */
	public boolean isSelectedComponent(ICircuitGate g);

	/**
	 * Removes all elements that are unused.
	 */
	public void removeUnusedElements();
	
	/**
	 * Returns a list of the selected components.
	 * @return List
	 */
	public List<ICircuitGate> getSelectedComponents();
}
