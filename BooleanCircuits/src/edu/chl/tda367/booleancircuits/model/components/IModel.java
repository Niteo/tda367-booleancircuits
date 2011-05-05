package edu.chl.tda367.booleancircuits.model.components;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.Constants;

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
	public List<AbstractCircuitGate> getComponents();

	/**
	 * Selects all components in the model.
	 */
	public void selectAllComponents();
	
	/**
	 * Get component
	 * 
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public AbstractCircuitGate getComponent(Point position) ;
	/**
	 * Selects the first found component at the specified coordinate
	 * 
	 * @param position
	 *            the coordinate for the component
	 */
	public void selectComponent(Point position);

	/**
	 * Removes the components that are selected
	 */
	public void removeSelectedComponents() ;

	/**
	 * returns whether a component is selected or not
	 * @param component
	 * @return boolean: true if the given component is selected, in other case false.
	 */
	public boolean isSelectedComponent(AbstractCircuitGate component) ;

	

	/**
	 * Returns the name of the workspace
	 */
	
	public String toString() ;
}
