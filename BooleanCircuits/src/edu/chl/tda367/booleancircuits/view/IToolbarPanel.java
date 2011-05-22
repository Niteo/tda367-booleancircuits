package edu.chl.tda367.booleancircuits.view;

import javax.swing.JButton;

/**
 * An interface to describe a tool bar panel.
 * 
 * @author Boel
 * 
 */
public interface IToolbarPanel {

	/**
	 * Returns the copy button.
	 * 
	 * @return copy button
	 */
	public JButton getCopyButton();

	/**
	 * Returns the cut button.
	 * 
	 * @return cut button
	 */
	public JButton getCutButton();

	/**
	 * Returns the new workspace button.
	 * 
	 * @return new workspace button
	 */
	public JButton getNewWorkspaceButton();

	/**
	 * Returns the open file button.
	 * 
	 * @return open file button
	 */
	public JButton getOpenFileButton();

	/**
	 * Returns the paste button.
	 * 
	 * @return paste button
	 */
	public JButton getPasteButton();

	/**
	 * Returns the pause clock button.
	 * 
	 * @return pause clock button
	 */
	public JButton getPauseClockButton();

	/**
	 * Returns the redo button.
	 * 
	 * @return redo button
	 */
	public JButton getRedoButton();

	/**
	 * Returns the save all button.
	 * 
	 * @return save all button
	 */
	public JButton getSaveAllButton();

	/**
	 * Returns the save as component button.
	 * 
	 * @return save as component button
	 */
	public JButton getSaveAsComponentButton();

	/**
	 * Returns the save button.
	 * 
	 * @return save button
	 */
	public JButton getSaveButton();

	/**
	 * Returns the start clock button.
	 * 
	 * @return start clock button
	 */
	public JButton getStartClockButton();

	/**
	 * Returns the undo button.
	 * 
	 * @return undo button
	 */
	public JButton getUndoButton();

	/**
	 * Initiates all the toolbar icons.
	 */
	public void initIcons();

}
