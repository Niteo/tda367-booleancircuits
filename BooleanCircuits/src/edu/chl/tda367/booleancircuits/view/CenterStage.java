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
		centerStagePanel.setLayout(new GridLayout(1,1));
	}

	private void newTab(String s) {
		tab.addTab(s, new Canvas());
	}

	/**
	 * Updates the view by repainting the workspace.
	 * @param modelManager
	 */
	public void update(ModelManager modelManager) {
		int selected = modelManager.getActiveWorkspaceIndex();
		tabbedPane.removeAll();
		for (Model model : modelManager.getWorkspaces()) {
			newTab(model.toString());
		}
		if(modelManager.getWorkspaces().size()>0){
			tabbedPane.setSelectedIndex(selected);
		}
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
	
	/**
	 * Returns the tab.
	 * @return Tab
	 */
	public Tab getTab() {
		return tab;
	}

}
