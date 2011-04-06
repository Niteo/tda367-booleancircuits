package edu.chl.tda367.booleancircuits.controller;

import edu.chl.tda367.booleancircuits.model.Coordinate;

/**
 * Main controller in BooleanCircuits.
 * @author Kaufmann
 */

public final class MasterController {
	/* ##########################
	 * #       WORKSPACES       #
	 * ##########################
	 * */

	/**
	 * Closes the currently active workspace.
	 */
	public void closeActiveWorkspace(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Creates a new blank workspace.
	 */
	public void newWorkspace(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Opens the specified workspace.
	 * @param path Path to the workspace to open.
	 */
	public void openWorkspace(String path){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Saves the active workspace.
	 */
	public void saveActiveWorkspace(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Saves the active workspace as a component.
	 */
	public void saveActiveWorskpaceAsComponent(String path){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Saves all workspaces.
	 */
	public void saveAllWorkspaces(){
		throw new UnsupportedOperationException();
	}
	
	/* ##########################
	 * #       COMPONENTS       #
	 * ##########################
	 * */
	/**
	 * Adds the currently selected component in the palette to the
	 * specified coordinate in the active workspace.
	 * @param coord the coordinate to add to
	 */
	public void addComponent(Coordinate coord){
		throw new UnsupportedOperationException();
	}
	
	//public abstract void useComponent(Component);
	// TODO: Above method
	/**
	 * Removes the currently selected component(s) in the active workspace.
	 */
	public void removeSelectedComponents(){
		throw new UnsupportedOperationException();
	}
	/**
	 * Selects all components in the active workspace.
	 */
	public void selectAllComponents(){
		throw new UnsupportedOperationException();
	}
	/**
	 * Selects the first occurance of a component at the given coordinate
	 * in the active workspace
	 * @param coord the coordinate to select from
	 */
	public void selectComponent(Coordinate coord){
		throw new UnsupportedOperationException();
	}
	
	/* ##########################
	 * #      CutCopyPaste      #
	 * ##########################
	 * */
	/**
	 * Copies the selected components in the active workspace to clipboard.
	 */
	public void copySelectedComponents(){
		throw new UnsupportedOperationException();
	}
	/**
	 * Cut the selected components in the active workspace to clipboard.
	 */
	public void cutSelectedComponents(){
		throw new UnsupportedOperationException();
	}
	/**
	 * Pastes the selected components to the active workspace from the clipboard.
	 * @param coord the coordinate to paste to
	 */
	public void pasteSelectedComponent(Coordinate coord){
		throw new UnsupportedOperationException();
	}
}