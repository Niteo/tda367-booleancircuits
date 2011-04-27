package edu.chl.tda367.booleancircuits.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
	private List<Model> tabIdList = new LinkedList<Model>();
	private Tab tab = new Tab();
	private JTabbedPane tabbedPane = tab.getTabbedPane();
	private Action closeWorkspace;

	/** Returns an instance of Canvas. */
	public CenterStage(Action closeWorkspace) {
		centerStagePanel.add(tabbedPane);
		centerStagePanel.setLayout(new GridLayout(1, 1));
		this.closeWorkspace = closeWorkspace;
	}

	private void newTab(String s) {
		tab.addTab(s, new Canvas());
		TabPanel tabPanel = (TabPanel) tab.getTabbedPane().getTabComponentAt(
				tab.getTabbedPane().getTabCount() - 1);
		tabPanel.getCloseButton().setAction(closeWorkspace);
	}

	/**
	 * Updates the view by repainting the workspace.
	 * 
	 * @param modelManager
	 */
	public void update(ModelManager modelManager) {
		List<Model> modelList = modelManager.getWorkspaces();
		System.out.println(modelList);
		System.out.println(tabIdList);
		for (int i = 0; i < modelList.size(); i++) {
			if (!tabIdList.contains(modelList.get(i))) {
				tabIdList.add(modelList.get(i));
				newTab(modelList.get(i).toString());
			}
		}

		List<Integer> removeList = new ArrayList<Integer>();
		for (int i = 0; i < tabIdList.size(); i++) {
			if (!modelList.contains(tabIdList.get(i))) {
				removeList.add(i);
			}
		}

		System.out.println("REMOVE " + removeList);
		for (Integer i : removeList) {
			tabIdList.remove((int) i);
			tabbedPane.remove((int) i);
		}

		if (modelManager.getActiveWorkspaceIndex() >= 1) {
			tabbedPane.setSelectedIndex(modelManager.getActiveWorkspaceIndex());
		}

		/*
		 * int selected = modelManager.getActiveWorkspaceIndex();
		 * tabbedPane.removeAll(); for (Model model :
		 * modelManager.getWorkspaces()) { newTab(model.toString(), model); } if
		 * (modelManager.getWorkspaces().size() > 0) {
		 * tabbedPane.setSelectedIndex(selected); }
		 */
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
	 * 
	 * @return Tab
	 */
	public Tab getTab() {
		return tab;
	}

}
