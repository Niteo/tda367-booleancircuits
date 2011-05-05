package edu.chl.tda367.booleancircuits.controller;

import java.awt.Point;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * Controller for ModelManager.
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
	 * @param i int number of the workspace
	 */
	public void closeWorkspace(int i);
	
	/**
	 * Creates a new blank workspace.
	 */
	public void newWorkspace();
	
	/**
	 * Opens the specified workspace.
	 * @param path Path to the workspace to open.
	 */
	public void openWorkspace(String path);
	
	/**
	 * Saves the active workspace.
	 */
	public void saveActiveWorkspace();
	
	/**
	 * Saves the active workspace as a component.
	 */
	public void saveActiveWorskpaceAsComponent(String path);
	
	/**
	 * Saves all workspaces.
	 */
	public void saveAllWorkspaces();
	
	/**
	 * Sets the currently active workspace.
	 * @param i int index of the workspace
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
	 * Adds a CircuitComponent in the palette to the
	 * specified coordinate in the active workspace.
	 * @param component the component to add
	 * @param coord the coordinate to add to
	 */
	public void addComponent(AbstractCircuitGate component, Point position);

	/**
	 * Removes the currently selected component(s) in the active workspace.
	 */
	public void removeSelectedComponents();
	
	/**
	 * Selects all components in the active workspace.
	 */
	public void selectAllComponents();
	
	/**
	 * Selects the first occurance of a component at the given coordinate
	 * in the active workspace
	 * @param coord the coordinate to select from
	 */
	public void selectComponent(Point position);
	
	/**
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public void copySelectedComponents();
	
	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public void cutSelectedComponents();
	
	/**
	 * Pastes the selected components to the active workspace from the clipboard.
	 * @param coord the coordinate to paste to
	 */
	public void pasteSelectedComponent(Point position);
}