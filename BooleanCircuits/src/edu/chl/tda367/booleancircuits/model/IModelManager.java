package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
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
	public void addWorkspace(IModelWrapper workspace);

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
	public IModelWrapper getActiveWorkspaceModel();

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
	public Collection<IModelWrapper> getWorkspaces();

	/**
	 * Adds a CircuitComponent in the palette to the specified coordinate in the
	 * active workspace.
	 * 
	 * @param component
	 *            the component to add
	 * @param position
	 *            the position to add to
	 */
	public void addComponent(ICircuitGate component, Point position);

	/**
	 * Adds a list of components to the active workspace. Position is relative
	 * to the list components' position.
	 * 
	 * @param component
	 *            List the components to add
	 */
	public void addComponents(List<ICircuitGate> component);

	/**
	 * Adds a list of components to the active workspace. Positions are relative
	 * to the first given position.
	 * 
	 * @param component
	 *            List the components to add
	 * @param position
	 *            Point the point the first component should be added to
	 */
	public void addComponents(List<ICircuitGate> component, Point position);

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
	public void removeComponent(ICircuitGate g);

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
	 * @param multiSelect
	 *            boolean if false, all selected components will be deselected
	 */
	public void selectComponent(Point position, boolean multiSelect);

	/**
	 * Selects all the components in the square area, defined by two points
	 * 
	 * @param positionStart
	 * @param positionEnd
	 * @param multiSelect
	 */
	public void selectComponents(Point pos1, Point pos2);

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
	public void connectComponents(ICircuitGate componentIn,
			ICircuitGate componentOut, int portIn, int portOut);

	/**
	 * Fires a property changed event manually.
	 */
	public void manualPropertyChanged();

	/**
	 * Clocks the active model with a clock pulse.
	 */
	public void clockActiveModel();
}
