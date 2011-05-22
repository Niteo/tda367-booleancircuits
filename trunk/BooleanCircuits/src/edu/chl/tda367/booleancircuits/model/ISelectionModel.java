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
	 * Determines if a component is currently selected.
	 * 
	 * @param g
	 *            component to check if selected
	 * @return true if given gate is selected
	 */
	public boolean isSelectedComponent(ICircuitGate g);

	/**
	 * Removes a component from the selected list.
	 * 
	 * @param g
	 *            component to remove
	 */
	public void removeComponent(ICircuitGate g);

	/**
	 * Removes a collection of components from the selected list.
	 * 
	 * @param c
	 *            collection of components to remove
	 */
	public void removeComponents(Collection<ICircuitGate> c);

	/**
	 * If it exists in, selects the given component in the active workspace.
	 * 
	 * @param g
	 *            the gate to select
	 * @param multiSelect
	 *            if false, all previously selected components will be
	 *            deselected
	 */
	public void selectComponent(ICircuitGate g, boolean multiSelect);

	/**
	 * Selects all components in the collection in the active workspace.
	 * 
	 * @param c
	 *            collection of components to select
	 */
	public void selectComponents(Collection<ICircuitGate> c);
}
