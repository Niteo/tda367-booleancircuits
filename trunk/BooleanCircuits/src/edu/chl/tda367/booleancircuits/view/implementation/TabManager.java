package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

import edu.chl.tda367.booleancircuits.view.ITabManager;

/**
 * A class to represent a tab.
 * 
 * @author Boel
 * 
 */
public final class TabManager implements ITabManager{

	private JTabbedPane tabbedPane = new JTabbedPane();

	public TabManager() {
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public void addTab(String name, Canvas canvas) {
		JScrollPane scrollPane = new JScrollPane(canvas.getCanvas());
		tabbedPane.addTab(name, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabPanel(name));
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void removeTab(int i) {
		tabbedPane.remove(i);
	}

	public void removeAllTabs() {
		tabbedPane.removeAll();
	}

	public void setSelectedTabIndex(int i) {
		tabbedPane.setSelectedIndex(i);
	}

	public int getTabCount() {
		return tabbedPane.getTabCount();
	}

	public TabPanel getTabPanel(int i) {
		return ((TabPanel) tabbedPane.getTabComponentAt(i));
	}

	public TabPanel getLastTabPanel() {
		return ((TabPanel) tabbedPane.getTabComponentAt(tabbedPane
				.getTabCount() - 1));
	}

	public void updateTabbedPane() {
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}
}
