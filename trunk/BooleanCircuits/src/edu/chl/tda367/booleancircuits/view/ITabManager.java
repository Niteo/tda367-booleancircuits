package edu.chl.tda367.booleancircuits.view;

import javax.swing.JTabbedPane;

/**
 * An interface describing a tab manager.
 * 
 * @author Boel
 * 
 */
public interface ITabManager {

	/**
	 * Adds a new tab to the tabbedPane.
	 * 
	 * @param name
	 *            name of the tab
	 * @param canvas
	 *            the canvas connected to the tab
	 */
	public void addTab(String name, ICanvas canvas);

	/**
	 * Returns the last added tabPanel.
	 * 
	 * @return the last added tabPanel
	 */
	public ITabPanel getLastTabPanel();

	/**
	 * Returns the TabbedPane.
	 * 
	 * @return the TabbedPane containing the tabs
	 */

	public JTabbedPane getTabbedPane();

	/**
	 * Returns the number of tabs on the TabbedPane.
	 * 
	 * @return number tabs
	 */
	public int getTabCount();

	/**
	 * Returns the tab panel at a given index.
	 * 
	 * @param i
	 *            int index of the tabPanel
	 * @return the panel at the specified index
	 */
	public ITabPanel getTabPanel(int i);

	/**
	 * Removes all tabs from the tabbedPane.
	 */
	public void removeAllTabs();

	/**
	 * Removes a tab from the tabbedPane.
	 * 
	 * @param i
	 *            index of the tab to remove
	 */
	public void removeTab(int i);

	/**
	 * Sets the selected tab.
	 * 
	 * @param i
	 *            index of the tab to select
	 */
	public void setSelectedTabIndex(int i);

	/**
	 * Repaints and revalidates the tabbed pane.
	 */
	public void updateTabbedPane();

}
