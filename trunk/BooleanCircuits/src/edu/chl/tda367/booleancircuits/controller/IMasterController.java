package edu.chl.tda367.booleancircuits.controller;

import java.awt.Point;
import java.io.File;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;

/**
 * Controller for ModelManager.
 * 
 * @author Kaufmann
 */
public interface IMasterController {
	/**
	 * Closes the currently active workspace.
	 */
	public void closeActiveWorkspace();

	/**
	 * Closes all workspaces.
	 */
	public void closeAllWorkspaces();

	/**
	 * Closes a specific workspace selected by int.
	 * 
	 * @param i
	 *            int number of the workspace
	 */
	public void closeWorkspace(int i);

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
	 * Sets the currently active workspace.
	 * 
	 * @param i
	 *            int index of the workspace
	 */
	public void setActiveWorkspace(int i);

	/**
	 * Undo the latest action
	 */
	public void undo();

	/**
	 * Redo the latest undoed action
	 */
	public void redo();

	/**
	 * Adds a CircuitComponent in the palette to the specified coordinate in the
	 * active workspace.
	 * 
	 * @param coord
	 *            the coordinate to add to
	 */
	public void addComponent(Point position);

	/**
	 * Removes the currently selected component(s) in the active workspace.
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
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public void copySelectedComponents();

	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public void cutSelectedComponents();

	/**
	 * Pastes the selected components to the active workspace from the
	 * clipboard.
	 */
	public void pasteSelectedComponents();

	/**
	 * Sets the component chosen in the palette.
	 * 
	 * @param g
	 *            AbstractCircuitGate
	 */
	public void setChosenComponent(IAbstractCircuitGate g);

	/**
	 * Connects component with another component. First call specifies input
	 * component, second specifiec output.
	 * 
	 * @param g
	 *            component to connect. Enter null to clear component memory.
	 * @param port
	 *            port to connect
	 */
	public void connectComponent(IAbstractCircuitGate g, int port);
}