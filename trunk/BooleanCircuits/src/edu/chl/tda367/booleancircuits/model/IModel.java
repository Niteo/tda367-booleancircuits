package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

public interface IModel {

	/**
	 * Adds a component to the model.
	 * 
	 * @param component
	 *            the new component to add
	 * @param
	 */
	public void addComponent(AbstractCircuitGate component, Point position);

	/**
	 * Returns a list of components
	 * 
	 * @return a list of components
	 */
	public Collection<AbstractCircuitGate> getComponents();

	
	/**
	 * Get component
	 * 
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public AbstractCircuitGate getComponent(Point position) ;

	/**
	 * Removes the specified components in the collection
	 */
	public void removeComponents(Collection<AbstractCircuitGate> list) ;

	/**
	 * Returns the name of the workspace
	 */	
	public String toString() ;
}