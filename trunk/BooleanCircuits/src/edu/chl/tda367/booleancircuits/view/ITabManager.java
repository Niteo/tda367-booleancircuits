package edu.chl.tda367.booleancircuits.view;

import javax.swing.JTabbedPane;

import edu.chl.tda367.booleancircuits.view.implementation.Canvas;
import edu.chl.tda367.booleancircuits.view.implementation.TabPanel;

/**
 * An interface to describe a tab manager.
 * 
 * @author Boel
 * 
 */
public interface ITabManager {

	/**
	 * Adds a new tab to the tabbedPane.
	 * 
	 * @param name
	 *            String displayed name of the tab
	 * @param canvas
	 *            Canvas the canvas connected to the tab
	 */
	public void addTab(String name, Canvas canvas);

	/**
	 * Returns the tabbedPane.
	 * 
	 * @return JTabbedPane the tabbedPane containing the tabs
	 */

	public JTabbedPane getTabbedPane();

	/**
	 * Removes a tab from the tabbedPane.
	 * 
	 * @param i
	 *            int index of the tab
	 */
	public void removeTab(int i);

	/**
	 * Removes all tabs from the tabbedPane.
	 */
	public void removeAllTabs();

	/**
	 * Sets the selected tab.
	 * 
	 * @param i
	 *            int index of the selected tab
	 */
	public void setSelectedTabIndex(int i);

	/**
	 * Returns the number of tabs on the tabbedPane.
	 * 
	 * @return int number tabs
	 */
	public int getTabCount();

	/**
	 * Returns the tabPanel at a given index.
	 * 
	 * @param i
	 *            int index of the tabPanel
	 * @return TabPanel
	 */
	public TabPanel getTabPanel(int i);

	/**
	 * Returns the last added tabPanel.
	 * 
	 * @return TabPanel
	 */
	public TabPanel getLastTabPanel();

	/**
	 * Repaints and revalidates the tabbed pane.
	 */
	public void updateTabbedPane();

}
