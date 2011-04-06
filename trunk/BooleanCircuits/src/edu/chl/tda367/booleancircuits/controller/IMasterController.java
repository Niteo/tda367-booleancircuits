package edu.chl.tda367.booleancircuits.controller;

/**
 * 
 * @author Kaufmann
 * Interface for the main controller in BooleanCircuits
 */

public interface IMasterController {
	/* ##########################
	 * #       WORKSPACES       #
	 * ##########################
	 * */

	/**
	 * Closes the currently active workspace.
	 */
	public abstract void closeActiveWorkspace();
	
	/**
	 * Creates a new blank workspace.
	 */
	public abstract void newWorkspace();
	
	/**
	 * Opens the specified workspace.
	 * @param path Path to the workspace to open.
	 */
	public abstract void openWorkspace(String path);
	
	/**
	 * Saves the active workspace.
	 */
	public abstract void saveActiveWorkspace();
	
	/**
	 * Saves the active workspace as a component.
	 */
	public abstract void saveActiveWorskpaceAsComponent(String path);
	
	/**
	 * Saves all workspaces.
	 */
	public abstract void saveAllWorkspaces();
	
	/* ##########################
	 * #       COMPONENTS       #
	 * ##########################
	 * */
	/**
	 * Adds the currently selected component in the palette to the
	 * specified coordinate in the active workspace.
	 * @param x The x-coordinate to add to
	 * @param y The y-coordinate to add to
	 */
	public abstract void addComponent(int x, int y);
	
	//public abstract void useComponent(Component);
	// TODO: Above method
	/**
	 * Removes the currently selected component(s) in the active workspace.
	 */
	public abstract void removeSelectedComponents();
	/**
	 * Selects all components in the active workspace.
	 */
	public abstract void selectAllComponents();
	/**
	 * Selects the first occurance of a component in the given coordinate
	 * in the active workspace
	 * @param x The x-coordinate to select from
	 * @param y The y-coordinate to select from
	 */
	public abstract void selectComponent(int x, int y);
	
	/* ##########################
	 * #      CutCopyPaste      #
	 * ##########################
	 * */
	/**
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public abstract void copySelectedComponents();
	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public abstract void cutSelectedComponents();
	/**
	 * Pastes the selected components to the active workspace from the clipboard.
	 * @param x The x-coordinate to paste to
	 * @param y The y-coordinate to paste to
	 */
	public abstract void pasteSelectedComponent(int x, int y);
}