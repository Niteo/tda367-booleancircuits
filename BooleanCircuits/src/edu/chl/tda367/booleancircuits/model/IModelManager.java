package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public interface IModelManager {

	/**
	 * Creates a new workspace and makes it active.
	 */
	public void newWorkspace();

	/**
	 * Adds a new workspace and makes it active.
	 * 
	 * @param workspace
	 *            the workspace to be added
	 */
	public void addWorkspace(IModel workspace);

	/**
	 * Closes the active workspace.
	 */
	public void closeActiveWorkspace();

	/**
	 * Closes all workspaces.
	 */
	public void closeAllWorkspaces();

	/**
	 * Closes a specific workspace.
	 * 
	 * @param i
	 *            int index of the workspace
	 */
	public void closeWorkspace(int i);

	/**
	 * Sets the currently active workspace.
	 * 
	 * @param i
	 *            int index of the workspace
	 */
	public void setActiveWorkspace(int i);

	/**
	 * Returns the index of the active workspace.
	 * 
	 * @return int index of the workspace
	 */
	public int getActiveWorkspaceIndex();

	/**
	 * Returns the active workspace model.
	 * 
	 * @return Model the active workspace to return.
	 */
	public IModel getActiveWorkspaceModel();

	/**
	 * returns the active selection model.
	 * 
	 * @return ISelectionModel the active selection model
	 */
	public ISelectionModel getActiveSelectionModel();

	/**
	 * Returns all workspaces.
	 * 
	 * @return list of all workspaces.
	 */
	public Collection<IModel> getWorkspaces();

	/**
	 * Adds a CircuitComponent in the palette to the specified coordinate in the
	 * active workspace.
	 * 
	 * @param component
	 *            the component to add
	 * @param coord
	 *            the coordinate to add to
	 */
	public void addComponent(IAbstractCircuitGate component, Point position);

	/**
	 * Removes the selected components.
	 */
	public void removeSelectedComponents();

	/**
	 * Removes the given component if it exists in the active model.
	 * 
	 * @param g
	 *            IAbstractCircuitGate
	 */
	public void removeComponent(IAbstractCircuitGate g);

	/**
	 * Selects all components in the collection.
	 * 
	 */
	public void selectAllComponents();

	/**
	 * Selects the component at the given point in the active workspace.
	 * 
	 * @param position
	 *            Point The point of the component
	 */
	public void selectComponent(Point position);
	
	/**
	 * Determinates if a component is currently selected.
	 * 
	 * @param g
	 *            AbstractCircuitGate
	 * @return boolean
	 */
	public boolean isSelectedComponent(AbstractCircuitGate g);
	
	/**
	 * Connects to components in the active model.
	 * @param componentIn the component who's input is to be connected
	 * @param componentOut the component who's output is to be connected
	 * @param portIn the port of the input to be used
	 * @param portOut the port of the output to be used
	 */
	public void connectComponents(IAbstractCircuitGate componentIn,
			IAbstractCircuitGate componentOut, int portIn, int portOut);
}
