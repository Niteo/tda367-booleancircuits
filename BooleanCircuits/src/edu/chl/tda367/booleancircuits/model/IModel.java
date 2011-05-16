package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
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
	public void addComponent(ICircuitGate component, Point position);

	/**
	 * Adds components to the model.
	 * 
	 * @param component
	 *            the components to add
	 */
	public void addComponents(Collection<ICircuitGate> components);
	
	/**
	 * Returns a list of components.
	 * 
	 * @return a list of components
	 */
	public Collection<ICircuitGate> getComponents();

	/**
	 * Get component.
	 * 
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public ICircuitGate getComponent(Point position);

	/**
	 * Removes the specified components in the collection.
	 * 
	 * @param list
	 *            Collection
	 */
	public void removeComponents(Collection<ICircuitGate> list);

	/**
	 * Removes the specified component.
	 * 
	 * @param g
	 *            IAbstractCircuitGate
	 */
	public void removeComponent(ICircuitGate g);

	/**
	 * Returns the name of the workspace.
	 */
	public String toString();
	
	/**
	 * Updates the model's component's boolean values. 
	 */
	public void updateComponents();
}