package edu.chl.tda367.booleancircuits.controller;

import javax.swing.Action;

/**
 * Controls action events.
 * 
 * @author Boel
 * 
 */
public interface IActionController {

	/**
	 * Returns the newWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getNewWorkspaceAction();

	/**
	 * Returns the closeActiveWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getCloseActiveWorkspaceAction();

	/**
	 * Returns the closeAllWorkspaces action.
	 * 
	 * @return Action
	 */
	public Action getCloseAllWorkspacesAction();

	/**
	 * Returns the openWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getOpenWorkspaceAction();

	/**
	 * Returns the saveActiveWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getSaveActiveWorkspaceAction();

	/**
	 * Returns the saveAllWorkspaces action.
	 * 
	 * @return Action
	 */
	public Action getSaveAllWorkspacesAction();

	/**
	 * Returns the saveAs action.
	 * 
	 * @return Action
	 */
	public Action getSaveAsAction();

	/**
	 * Returns the CopySelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getCopySelectedComponentsAction();

	/**
	 * Returns the cutSelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getCutSelectedComponentsAction();

	/**
	 * Returns the pasteSelectedComponent action.
	 * 
	 * @return Action
	 */
	public Action getPasteSelectedComponentAction();

	/**
	 * Returns the undo action.
	 * 
	 * @return Action
	 */
	public Action getUndoAction();

	/**
	 * Returns the redo action.
	 * 
	 * @return Action
	 */
	public Action getRedoAction();

	/**
	 * Returns the exit action.
	 * 
	 * @return Action
	 */
	public Action getExitAction();

	/**
	 * Returns the removeSelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getRemoveSelectedComponentsAction();

	/**
	 * Returns the selectAllComponents action.
	 * 
	 * @return Action
	 */
	public Action getSelectAllComponentsAction();

	/**
	 * Returns the startClock action.
	 * 
	 * @return Action
	 */
	public Action getStartClockAction();

	/**
	 * Returns the pauseClock action.
	 * 
	 * @return Action
	 */
	public Action getPauseClockAction();

	/**
	 * Returns the importWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getImportWorkspaceAction();
	
	/**
	 * Returns the showAboutBox action
	 * @return Action
	 */
	public Action getShowAboutBoxAction();
	
	/**
	 *  Returns the showHelp action
	 * @return Action
	 */
	public Action getShowHelpAction();
	
}