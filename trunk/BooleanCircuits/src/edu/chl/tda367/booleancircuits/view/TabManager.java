package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * A class to represent a tab.
 * 
 * @author Boel
 * 
 */
public final class TabManager {

	private JTabbedPane tabbedPane = new JTabbedPane();
	private Canvas canvas;

	public TabManager() {
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	/**
	 * Adds a new tab to the tabbedPane.
	 * 
	 * @param name
	 *            String displayed name of the tab
	 * @param canvas
	 *            Canvas the canvas connected to the tab
	 */
	public void addTab(String name, Canvas canvas) {
		this.canvas = canvas;
		JScrollPane scrollPane = new JScrollPane(canvas.getCanvas());
		tabbedPane.addTab(name, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabPanel(name));
	}

	/**
	 * Returns the tabbedPane.
	 * 
	 * @return JTabbedPane the tabbedPane containing the tabs
	 */

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Removes a tab from the tabbedPane.
	 * 
	 * @param i
	 *            int index of the tab
	 */
	public void removeTab(int i) {
		tabbedPane.remove(i);
	}

	/**
	 * Removes all tabs from the tabbedPane.
	 */
	public void removeAllTabs() {
		tabbedPane.removeAll();
	}

	/**
	 * Sets the selected tab.
	 * 
	 * @param i
	 *            int index of the selected tab
	 */
	public void setSelectedTabIndex(int i) {
		tabbedPane.setSelectedIndex(i);
	}

	/**
	 * Returns the number of tabs on the tabbedPane.
	 * 
	 * @return int number tabs
	 */
	public int getTabCount() {
		return tabbedPane.getTabCount();
	}

	/**
	 * Returns the tabPanel at a given index.
	 * 
	 * @param i
	 *            int index of the tabPanel
	 * @return TabPanel
	 */
	public TabPanel getTabPanel(int i) {
		return ((TabPanel) tabbedPane.getTabComponentAt(i));
	}

	/**
	 * Returns the last added tabPanel.
	 * 
	 * @return TabPanel
	 */
	public TabPanel getLastTabPanel() {
		return ((TabPanel) tabbedPane.getTabComponentAt(tabbedPane
				.getTabCount() - 1));
	}

	public void updateTabbedPane() {
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}
}
