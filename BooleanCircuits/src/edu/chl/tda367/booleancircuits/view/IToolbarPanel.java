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
	 * Returns the cut button.
	 * 
	 * @return JButton
	 */
	public JButton getCutButton();

	/**
	 * Returns the copy button.
	 * 
	 * @return JButton
	 */
	public JButton getCopyButton();

	/**
	 * Returns the new works space button.
	 * 
	 * @return JButton
	 */
	public JButton getNewWorkspaceButton();

	/**
	 * Returns the open file button.
	 * 
	 * @return JButton
	 */
	public JButton getOpenFileButton();

	/**
	 * Returns the paste button.
	 * 
	 * @return JButton
	 */
	public JButton getPasteButton();

	/**
	 * Returns the redo button.
	 * 
	 * @return JButton
	 */
	public JButton getRedoButton();

	/**
	 * Returns the save all button.
	 * 
	 * @return JButton
	 */
	public JButton getSaveAllButton();

	/**
	 * Returns the save as component button.
	 * 
	 * @return JButton
	 */
	public JButton getSaveAsComponentButton();

	/**
	 * Returns the save button.
	 * 
	 * @return JButton
	 */
	public JButton getSaveButton();

	/**
	 * Returns the undo button.
	 * 
	 * @return JButton
	 */
	public JButton getUndoButton();

	/**
	 * Returns the start clock button.
	 * 
	 * @return JButton
	 */
	public JButton getStartClockButton();

	/**
	 * Returns the pause clock button.
	 * 
	 * @return JButton
	 */
	public JButton getPauseClockButton();

	/**
	 * Sets all the toolbar icons.
	 */
	public void initIcons();

}
