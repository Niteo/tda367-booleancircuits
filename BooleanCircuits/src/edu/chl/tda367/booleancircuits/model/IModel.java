package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

public interface IModel {
	
	/**
	 * Clocks all clocks in the model.
	 */
	public void clock();
	
	/**
	 * Adds a component to the model.
	 * 
	 * @param component
	 *            the new component to add
	 * @param position
	 * 			  position to add component at
	 */
	public void addComponent(IAbstractCircuitGate component, Point position);

	/**
	 * Returns a list of components.
	 * 
	 * @return a list of components
	 */
	public Collection<IAbstractCircuitGate> getComponents();

	/**
	 * Get component.
	 * 
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public IAbstractCircuitGate getComponent(Point position);

	/**
	 * Removes the specified components in the collection.
	 * 
	 * @param list
	 *            Collection
	 */
	public void removeComponents(Collection<IAbstractCircuitGate> list);

	/**
	 * Removes the specified component.
	 * 
	 * @param g
	 *            IAbstractCircuitGate
	 */
	public void removeComponent(IAbstractCircuitGate g);

	/**
	 * Returns the name of the workspace.
	 */
	public String toString();
	
	/**
	 * Updates the model's component's boolean values. 
	 */
	public void updateComponents();
}