package edu.chl.tda367.booleancircuits.controller;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

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
	 * @param coord
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
	 * Closes a specific workspace selected by int.
	 *
	 * @param i
	 *            int number of the workspace
	 * @return returns true if workspace has been closed successfully
	 */
	public boolean closeWorkspace(int i);

	/**
	 * Connects component with another component. First call specifies input
	 * component, second specifiec output.
	 *
	 * @param g
	 *            component to connect. Enter null to clear component memory.
	 * @param port
	 *            port to connect
	 */
	public void connectComponent(ICircuitGate g, int port);

	/**
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public void copySelectedComponents();

	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public void cutSelectedComponents();

	/**
	 * Imports workspace into active workspace
	 *
	 * @param file
	 */
	public void importWorkspace();

	/**
	 * Creates a new blank workspace.
	 */
	public void newWorkspace();

	/**
	 * Opens the specified workspace.
	 *
	 * @param path
	 *            Path to the workspace to open.
	 */
	public void openWorkspace();

	/**
	 * Pastes the selected components to the active workspace from the
	 * clipboard.
	 */
	public void pasteSelectedComponents();

	/**
	 * Pastes the selected components to the active workspace from the
	 * clipboard.
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
	 *            IAbstractCircuitGate
	 */
	public void removeComponent(ICircuitGate g);

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
	 * @param coord
	 *            the coordinate to select from
	 * @param multiSelect
	 *            boolean if false, all selected components will be deselected
	 */
	public void selectComponent(Point position, boolean multiSelect);

	/**
	 * selects all components in selection square
	 *
	 * @param pos1
	 * @param pos2
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
	public void setChosenComponent(ICircuitGate g);

	/**
	 * Toggles the clock timer.
	 */
	public void toggleClockTimer();

	/**
	 * Undo the latest action
	 */
	public void undo();

}