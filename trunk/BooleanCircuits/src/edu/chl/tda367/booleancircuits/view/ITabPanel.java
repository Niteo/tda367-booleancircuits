package edu.chl.tda367.booleancircuits.view;

import javax.swing.JButton;

/**
 * An interface describing a tab panel.
 * 
 * @author Boel
 * 
 */

public interface ITabPanel {

	/**
	 * Returns the close button
	 * 
	 * @return close button
	 */
	public JButton getCloseButton();

	/**
	 * Sets the title of the tab panel.
	 * 
	 * @param title
	 *            title to set
	 */
	public void setTabPanelTitle(String title);

}
