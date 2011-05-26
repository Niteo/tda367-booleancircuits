package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.*;

public interface ICircuit {

	/**
	 * Adds a component to the circuit.
	 * 
	 * @param component
	 *            the new component to add
	 * @param position
	 *            position to add component at
	 */
	public void addComponent(IGateWrapper component, Point position);

	/**
	 * Adds components to the circuit.
	 * 
	 * @param component
	 *            the components to add
	 */
	public void addComponents(Collection<IGateWrapper> components);

	/**
	 * Clocks all clocks in the circuit.
	 */
	public void clock();

	/**
	 * Get component.
	 * 
	 * @param position
	 *            the coordinate for the component
	 * @return the gate found. Null if no gate was found
	 */
	public IGateWrapper getComponent(Point position);

	/**
	 * Returns a list of components.
	 * 
	 * @return a list of components
	 */
	public Collection<IGateWrapper> getComponents();

	/**
	 * Returns the corresponding IGateWrapper that contains the given gate.
	 * 
	 * @param gate
	 *            ICircuitGate
	 * @return IGateWrapper
	 */
	public IGateWrapper getGateWrapper(ICircuitGate gate);

	/**
	 * Returns the amount of components in the circuit.
	 * 
	 * @return amount of components
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
	 *            the component to remove
	 */
	public void removeComponent(IGateWrapper g);

	/**
	 * Removes the specified components.
	 * 
	 * @param list
	 *            the components to remove
	 */
	public void removeComponents(Collection<IGateWrapper> list);

	/**
	 * Updates the circuit's components' boolean values.
	 */
	public void updateComponents();
}