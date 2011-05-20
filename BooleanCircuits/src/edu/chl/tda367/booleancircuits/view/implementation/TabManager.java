package edu.chl.tda367.booleancircuits.view.implementation;

import javax.swing.*;

import edu.chl.tda367.booleancircuits.view.ITabManager;

/**
 * A class to represent a tab.
 * 
 * @author Boel
 * 
 */
public final class TabManager implements ITabManager {

	private JTabbedPane tabbedPane = new JTabbedPane();

	public TabManager() {
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	@Override
	public void addTab(final String name, final Canvas canvas) {
		JScrollPane scrollPane = new JScrollPane(canvas.getCanvas());
		tabbedPane.addTab(name, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabPanel(name));
	}

	@Override
	public TabPanel getLastTabPanel() {
		return ((TabPanel) tabbedPane.getTabComponentAt(tabbedPane
				.getTabCount() - 1));
	}

	@Override
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	@Override
	public int getTabCount() {
		return tabbedPane.getTabCount();
	}

	@Override
	public TabPanel getTabPanel(final int i) {
		return ((TabPanel) tabbedPane.getTabComponentAt(i));
	}

	@Override
	public void removeAllTabs() {
		tabbedPane.removeAll();
	}

	@Override
	public void removeTab(final int i) {
		tabbedPane.remove(i);
	}

	@Override
	public void setSelectedTabIndex(final int i) {
		tabbedPane.setSelectedIndex(i);
	}

	@Override
	public void updateTabbedPane() {
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}
}
