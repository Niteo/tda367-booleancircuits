package edu.chl.tda367.booleancircuits.controller;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * Controller for ModelManager.
 *
 * @author Kaufmann
 */
public interface IMasterController {
	/**
	 * Adds a CircuitComponent in the palette to the specified coordinate in the
	 * active workspace.
	 *
	 * @param position
	 *            the coordinate to add to
	 */
	public void addComponent(Point position);

	/**
	 * Closes the currently active workspace.
	 */
	public void closeActiveWorkspace();

	/**
	 * Closes all workspaces.
	 *
	 * @return returns true if all workspaces has been closed successfully
	 */
	public boolean closeAllWorkspaces();

	/**
	 * Closes a specific workspace selected by index
	 *
	 * @param i
	 *            index of the workspace
	 * @return returns true if workspace has been closed successfully
	 */
	public boolean closeWorkspace(int i);

	/**
	 * Connects component with another component. First call specifies input
	 * component, second specifiec output.
	 *
	 * @param g
	 *            component to connect.
	 * @param port
	 *            port to connect
	 */
	public void connectComponent(IGateWrapper g, int port);

	/**
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public void copySelectedComponents();

	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public void cutSelectedComponents();

	/**
	 * Imports saved workspace into active workspace
	 */
	public void importWorkspace();

	/**
	 * Creates a new blank workspace.
	 */
	public void newWorkspace();

	/**
	 * Lets the user choose a circuit, then opens it into a new workspace.
	 */
	public void openWorkspace();

	/**
	 * Pastes clipboard components to the currently active workspace.
	 */
	public void pasteSelectedComponents();

	/**
	 * Pastes clipboard components to the currently active workspace.
	 *
	 * @param position
	 *            Point position in the active model
	 */
	public void pasteSelectedComponents(Point position);

	/**
	 * Redo the latest undoed action
	 */
	public void redo();

	/**
	 * Removes the given component if it exists in the active model.
	 *
	 * @param g
	 *            IGateWrapper
	 */
	public void removeComponent(IGateWrapper g);

	/**
	 * Removes the currently selected component(s) in the active workspace.
	 */
	public void removeSelectedComponents();

	/**
	 * Saves the active workspace.
	 *
	 * @param saveAs
	 *            forces use of save dialog
	 */
	public void saveActiveWorkspace(boolean saveAs);

	/**
	 * Saves all workspaces.
	 */
	public void saveAllWorkspaces();

	/**
	 * Selects all components in the active workspace.
	 */
	public void selectAllComponents();

	/**
	 * Selects the first occurance of a component at the given coordinate in the
	 * active workspace
	 *
	 * @param position
	 *            the coordinate to select from
	 * @param multiSelect
	 *            boolean if false, all selected components will be deselected
	 */
	public void selectComponent(Point position, boolean multiSelect);

	/**
	 * Selects all components existing in the square created by two points
	 *
	 * @param pos1
	 *            start point
	 * @param pos2
	 *            end point
	 */
	public void selectComponents(Point pos1, Point pos2);

	/**
	 * Sets the currently active workspace.
	 *
	 * @param i
	 *            int index of the workspace
	 */
	public void setActiveWorkspace(int i);

	/**
	 * Sets the component chosen in the palette.
	 *
	 * @param g
	 *            AbstractCircuitGate
	 */
	public void setChosenComponent(IGateWrapper g);

	/**
	 * Toggles the clock timer.
	 */
	public void toggleClockTimer();

	/**
	 * Undo the latest action
	 */
	public void undo();

}