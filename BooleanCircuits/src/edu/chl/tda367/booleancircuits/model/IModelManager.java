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
public interface IModelManager {

	/**
	 * Adds an IGateWrapper to the specified coordinate in the active workspace.
	 *
	 * @param component
	 *            the component to add
	 * @param position
	 *            the position to add to
	 */
	public void addComponent(IGateWrapper component, Point position);

	/**
	 * Adds a list of components to the active workspace.
	 *
	 * @param component
	 *            List the components to add
	 */
	public void addComponents(List<IGateWrapper> component);

	/**
	 * Adds a list of components to the active workspace. Position is relative
	 * to the list components' positions.
	 *
	 * @param components
	 *            the components to add
	 * @param position
	 *            the position the components will be placed around
	 */
	public void addComponents(List<IGateWrapper> components, Point position);

	/**
	 * Adds a new workspace and makes it active.
	 *
	 * @param workspace
	 *            the workspace to be added
	 */
	public void addWorkspace(IModelWrapper workspace);

	/**
	 * Clocks the active model with a clock pulse.
	 */
	public void clockActiveModel();

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
	 *            index of the workspace to close
	 */
	public void closeWorkspace(int i);

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
	public void connectComponents(IGateWrapper componentIn,
			IGateWrapper componentOut, int portIn, int portOut);

	/**
	 * Returns the active selection model.
	 *
	 * @return the active selection model
	 */
	public ISelectionModel getActiveSelectionModel();

	/**
	 * Returns the index of the active workspace.
	 *
	 * @return index of the active workspace
	 */
	public int getActiveWorkspaceIndex();

	/**
	 * Returns the active workspace.
	 *
	 * @return the active workspace
	 */
	public IModelWrapper getActiveWorkspaceModel();

	/**
	 * Returns the specified workspace.
	 *
	 * @param i
	 *            the index of the workspace to return
	 */
	public IModelWrapper getWorkspace(int i);

	/**
	 * Returns all workspaces.
	 *
	 * @return collection of all workspaces
	 */
	public Collection<IModelWrapper> getWorkspaces();

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
	 * Creates a new workspace and makes it active.
	 */
	public void newWorkspace();

	/**
	 * Removes the given component if it exists in the active model.
	 *
	 * @param g
	 *            component to remove
	 */
	public void removeComponent(IGateWrapper g);

	/**
	 * Removes the selected components.
	 */
	public void removeSelectedComponents();

	/**
	 * Selects all components in the active workspace
	 *
	 */
	public void selectAllComponents();

	/**
	 * Selects first found component at the given point in the active workspace.
	 *
	 * @param position
	 *            the point of the component
	 * @param multiSelect
	 *            if false, all selected components will be deselected
	 */
	public void selectComponent(Point position, boolean multiSelect);

	/**
	 * Selects all components existing in the active workspace.
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
	 * Sets the currently active workspace.
	 *
	 * @param i
	 *            index of the workspace to select
	 */
	public void setActiveWorkspace(int i);

	/**
	 * Returns the corresponding IGateWrapper that contains the given gate.
	 *
	 * @param gate
	 *            ICircuitGate
	 * @return IGateWrapper
	 */
	public IGateWrapper getGateWrapper(ICircuitGate gate);
}
