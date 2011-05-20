package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public interface IModel {

	/**
	 * Adds a component to the model.
	 *
	 * @param component
	 *            the new component to add
	 * @param position
	 *            position to add component at
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
	 * Clocks all clocks in the model.
	 */
	public void clock();

	/**
	 * Get component.
	 *
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public ICircuitGate getComponent(Point position);

	/**
	 * Returns a list of components.
	 *
	 * @return a list of components
	 */
	public Collection<ICircuitGate> getComponents();

	/**
	 * Returns the amount of components in the model.
	 *
	 * @return integer representing the amount of components
	 */
	public int getNumberOfComponents();

	/**
	 * Returns true if the circuit has infinite recursion.
	 *
	 * @return boolean
	 */
	public boolean hasInfiniteRecursion();

	/**
	 * Removes the specified component.
	 *
	 * @param g
	 *            IAbstractCircuitGate
	 */
	public void removeComponent(ICircuitGate g);

	/**
	 * Removes the specified components in the collection.
	 *
	 * @param list
	 *            Collection
	 */
	public void removeComponents(Collection<ICircuitGate> list);

	/**
	 * Returns the name of the workspace.
	 */
	@Override
	public String toString();

	/**
	 * Updates the model's component's boolean values.
	 */
	public void updateComponents();
}