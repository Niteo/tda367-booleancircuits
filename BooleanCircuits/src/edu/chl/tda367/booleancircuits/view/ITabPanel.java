package edu.chl.tda367.booleancircuits.view;

import javax.swing.JButton;

/**
 * An interface to describe a tab panel.
 * 
 * @author Boel
 * 
 */

public interface ITabPanel {

	/**
	 * Sets the title of the tab panel.
	 * 
	 * @param title
	 *            String
	 */
	public void setTabPanelTitle(String title);

	/**
	 * Returns the close button
	 * 
	 * @return JButton
	 */
	public JButton getCloseButton();

}
