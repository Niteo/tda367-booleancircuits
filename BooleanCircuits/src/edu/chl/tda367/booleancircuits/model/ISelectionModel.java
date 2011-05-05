package edu.chl.tda367.booleancircuits.model;

import java.util.Collection;
import java.util.List;

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
	public void selectAllComponents(Collection<AbstractCircuitGate> c);

	/**
	 * Selects the given component in the active workspace.
	 * 
	 * @param g
	 *            AbstractCircuitGate the gate to select
	 */
	public void selectComponent(AbstractCircuitGate g);

	/**
	 * Determinates if a component is currently selected.
	 * 
	 * @param g
	 *            AbstractCircuitGate
	 * @return boolean
	 */
	public boolean isSelectedComponent(AbstractCircuitGate g);

	/**
	 * Removes all elements that are unused.
	 */
	public void removeUnusedElements();
	
	/**
	 * Returns a list of the selected components.
	 * @return List
	 */
	public List<AbstractCircuitGate> getSelectedComponents();
}
