package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * A class which manages Models as workspaces.
 *
 * @author Kaufmann
 */
public interface ICircuitManager {

	/**
	 * Adds an IGateWrapper to the specified coordinate in the active circuit.
	 *
	 * @param component
	 *            the component to add
	 * @param position
	 *            the position to add to
	 */
	public void addComponent(IGateWrapper component, Point position);

	/**
	 * Adds a list of components to the active circuit.
	 *
	 * @param component
	 *            List the components to add
	 */
	public void addComponents(List<IGateWrapper> component);

	/**
	 * Adds a list of components to the active circuit. Position is relative
	 * to the list components' positions.
	 *
	 * @param components
	 *            the components to add
	 * @param position
	 *            the position the components will be placed around
	 */
	public void addComponents(List<IGateWrapper> components, Point position);

	/**
	 * Adds a new circuit and makes it active.
	 *
	 * @param circuit
	 *            the circuit to be added
	 */
	public void addCircuit(ICircuitWrapper workspace);

	/**
	 * Clocks the active circuit with a clock pulse.
	 */
	public void clockActiveCircuit();

	/**
	 * Closes the active circuit.
	 */
	public void closeActiveCircuit();

	/**
	 * Closes all circuits.
	 */
	public void closeAllCircuits();

	/**
	 * Closes a specific circuit.
	 *
	 * @param i
	 *            index of the circuit to close
	 */
	public void closeCircuit(int i);

	/**
	 * Connects to components in the active circuit.
	 *
	 * @param componentIn
	 *            the component who's input is to be connected
	 * @param componentOut
	 *            the component who's output is to be connected
	 * @param portIn
	 *            the port of the input to be used
	 * @param portOut
	 *            the port of the output to be used
	 */
	public void connectComponents(IGateWrapper componentIn,
			IGateWrapper componentOut, int portIn, int portOut);

	/**
	 * Returns the active selection model.
	 *
	 * @return the active selection model
	 */
	public ISelectionModel getActiveSelectionModel();

	/**
	 * Returns the index of the active circuit.
	 *
	 * @return index of the active circuit
	 */
	public int getActiveCircuitIndex();

	/**
	 * Returns the active circuit.
	 *
	 * @return the active circuit
	 */
	public ICircuitWrapper getActiveCircuit();

	/**
	 * Returns the specified circuit.
	 *
	 * @param i
	 *            the index of the circuit to return
	 */
	public ICircuitWrapper getCircuit(int i);

	/**
	 * Returns all circuits.
	 *
	 * @return collection of all circuits
	 */
	public Collection<ICircuitWrapper> getCircuits();

	/**
	 * Determines if a component is currently selected.
	 *
	 * @param g
	 *            component to check if selected
	 * @return true if component is selected
	 */
	public boolean isSelectedComponent(IGateWrapper g);

	/**
	 * Fires a property changed event manually.
	 */
	public void manualPropertyChanged();

	/**
	 * Creates a new circuit and makes it active.
	 */
	public void newCircuit();

	/**
	 * Removes the given component if it exists in the active circuit.
	 *
	 * @param g
	 *            component to remove
	 */
	public void removeComponent(IGateWrapper g);

	/**
	 * Removes the selected components from the active circuit.
	 */
	public void removeSelectedComponents();

	/**
	 * Selects all components in the active circuit.
	 *
	 */
	public void selectAllComponents();

	/**
	 * Selects first found component at the given point in the active circuit.
	 *
	 * @param position
	 *            the point of the component
	 * @param multiSelect
	 *            if false, all selected components will be deselected
	 */
	public void selectComponent(Point position, boolean multiSelect);

	/**
	 * Selects all components existing in the active circuit.
	 *
	 * @param list
	 *            components to select
	 */
	public void selectComponents(List<IGateWrapper> list);

	/**
	 * Selects all components existing in the square created by two points
	 *
	 * @param positionStart
	 *            start point
	 * @param positionEnd
	 *            end point
	 */
	public void selectComponents(Point positionStart, Point positionEnd);

	/**
	 * Sets the currently active circuit.
	 *
	 * @param i
	 *            index of the circuit to select
	 */
	public void setActiveCircuit(int i);

	/**
	 * Returns the corresponding IGateWrapper that contains the given gate.
	 *
	 * @param gate
	 *            ICircuitGate
	 * @return IGateWrapper
	 */
	public IGateWrapper getGateWrapper(ICircuitGate gate);
}
