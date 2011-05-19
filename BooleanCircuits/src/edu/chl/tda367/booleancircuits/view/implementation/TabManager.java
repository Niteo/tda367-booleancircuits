package edu.chl.tda367.booleancircuits.view.implementation;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
	public void addTab(String name, Canvas canvas) {
		JScrollPane scrollPane = new JScrollPane(canvas.getCanvas());
		tabbedPane.addTab(name, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabPanel(name));
	}

	@Override
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	@Override
	public void removeTab(int i) {
		tabbedPane.remove(i);
	}

	@Override
	public void removeAllTabs() {
		tabbedPane.removeAll();
	}

	@Override
	public void setSelectedTabIndex(int i) {
		tabbedPane.setSelectedIndex(i);
	}

	@Override
	public int getTabCount() {
		return tabbedPane.getTabCount();
	}

	@Override
	public TabPanel getTabPanel(int i) {
		return ((TabPanel) tabbedPane.getTabComponentAt(i));
	}

	@Override
	public TabPanel getLastTabPanel() {
		return ((TabPanel) tabbedPane.getTabComponentAt(tabbedPane
				.getTabCount() - 1));
	}

	@Override
	public void updateTabbedPane() {
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}
}
