package edu.chl.tda367.booleancircuits.view;

import java.awt.*;
import javax.swing.*;

import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.ModelManager;

/**
 * A class that represents a workspace with a panel and potentially several
 * tabs.
 * 
 * @author Boel
 * 
 */
public class CenterStage {

	private JPanel centerStagePanel = new JPanel();
	private Tab tab = new Tab();
	private JTabbedPane tabbedPane = tab.getTabbedPane();

	/** Returns an instance of Canvas. */
	public CenterStage() {
		centerStagePanel.add(tabbedPane);
	}

	private void newTab(String s) {
		tab.addTab(s, new Canvas());
	}

	public void update(ModelManager mm) {
		int selected = tabbedPane.getSelectedIndex();
		tabbedPane.removeAll();
		for (Model m : mm.getWorkspaces()) {
			newTab(m.toString());
		}
		tabbedPane.setSelectedIndex(selected);
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}

	/**
	 * Returns the panel of the canvas.
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel() {
		return centerStagePanel;
	}

}
