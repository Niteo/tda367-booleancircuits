package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.Model;

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
	public void addWorkspace(Model workspace);

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
	public Model getActiveWorkspaceModel();

	/**
	 * Returns all workspaces.
	 * 
	 * @return list of all workspaces.
	 */
	public List<Model> getWorkspaces();
	
	/**
	 * Adds a CircuitComponent in the palette to the
	 * specified coordinate in the active workspace.
	 * @param component the component to add
	 * @param coord the coordinate to add to
	 */
	public void addComponent(AbstractCircuitGate component, Point position);
	
	/**
	 * Removes the components in the provided collection
	 * @param list collection of components to remove
	 */
	public void removeComponents(List<AbstractCircuitGate> list);
}
