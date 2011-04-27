package edu.chl.tda367.booleancircuits.controller;

import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * Controller for ModelManager.
 * @author Kaufmann
 */

public final class MasterController {
	
	private ModelManager mm = null;
	
	/**
	 * Returns an instance of a MasterController
	 * @param mm the ModelManager to control
	 * @throws NullPOinterException if mm is null
	 */
	public MasterController(ModelManager mm){
		if(mm == null){
			throw new NullPointerException("mm must not be null!");
		} else {
			this.mm = mm;
		}
	}
	
	/* ##########################
	 * #       WORKSPACES       #
	 * ##########################
	 * */
	/**
	 * Closes the currently active workspace.
	 */
	public void closeActiveWorkspace(){
		mm.closeActiveWorkspace();
	}
	
	/**
	 * Closes all workspaces.
	 */
	public void closeAllWorkspaces(){
		mm.closeAllWorkspaces();
	}
	
	/**
	 * Closes a specific workspace selected by int.
	 * @param i int number of the workspace
	 */
	public void closeWorkspace(int i){
		mm.closeWorkspace(i);
	}
	
	/**
	 * Creates a new blank workspace.
	 */
	public void newWorkspace(){
		mm.newWorkspace();
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
	
	/**
	 * Sets the currently active workspace.
	 * @param i int index of the workspace
	 */
	public void setActiveWorkspace(int i){
		mm.setActiveWorkspace(i);
	}
	
	/* ##########################
	 * #       COMPONENTS       #
	 * ##########################
	 * */
	/**
	 * Adds a CircuitComponent in the palette to the
	 * specified coordinate in the active workspace.
	 * @param component the component to add
	 * @param coord the coordinate to add to
	 */
	public void addComponent(AbstractCircuitGate component, Coordinate coord){
		throw new UnsupportedOperationException();
	}

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