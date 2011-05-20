package edu.chl.tda367.booleancircuits.model;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

/**
 * An interface to describe a selection model.
 * 
 * @author Boel
 * 
 */
public interface ISelectionModel {

	/**
	 * Returns number of components currently selected.
	 * 
	 * @return integer representing the amount of components selected
	 */
	public int getNumberOfComponents();

	/**
	 * Returns a list of the selected components.
	 * 
	 * @return List
	 */
	public List<ICircuitGate> getSelectedComponents();

	/**
	 * Determinates if a component is currently selected.
	 * 
	 * @param g
	 *            AbstractCircuitGate
	 * @return boolean
	 */
	public boolean isSelectedComponent(ICircuitGate g);

	/**
	 * Removes a component from the selected list.
	 * 
	 * @param g
	 *            ICircuitGate component to remove
	 */
	public void removeComponent(ICircuitGate g);

	/**
	 * Removes a collection of components from the selected list.
	 * 
	 * @param c
	 *            Collection components to remove
	 */
	public void removeComponents(Collection<ICircuitGate> c);

	/**
	 * Selects the given component in the active workspace.
	 * 
	 * @param g
	 *            IAbstractCircuitGate the gate to select
	 * @param multiSelect
	 *            boolean if false, all selected components will be deselected
	 */
	public void selectComponent(ICircuitGate g, boolean multiSelect);

	/**
	 * Selects all components in the collection.
	 * 
	 * @param c
	 *            Collection
	 */
	public void selectComponents(Collection<ICircuitGate> c);
}
