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
import edu.chl.tda367.booleancircuits.view.draw.DottedBackground;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;

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
	private TabManager tabManager = new TabManager();
	private Action closeWorkspace;
	private MainWindow mainWindow;

	/** Returns an instance of Canvas. */
	public CenterStage(Action closeWorkspace, MainWindow mainWindow) {
		centerStagePanel.add(tabManager.getTabbedPane());
		centerStagePanel.setLayout(new GridLayout(1, 1));
		this.closeWorkspace = closeWorkspace;
		this.mainWindow = mainWindow;
	}

	private void newTab(String s, Model m) {
		Canvas canvas = new Canvas();
		canvas.setModel(m);
		canvas.addPropertyChangeListener(mainWindow);
		tabManager.addTab(s, canvas);
		TabPanel tabPanel = tabManager.getLastTabPanel();
		tabPanel.getCloseButton().setAction(closeWorkspace);
	}

	/**
	 * Updates the view by repainting the workspace.
	 * 
	 * @param modelManager
	 */
	public synchronized void update(ModelManager modelManager) {

		List<Model> modelList = modelManager.getWorkspaces();

		for (int i = 0; i < modelList.size(); i++) {
			if (!tabIdList.contains(modelList.get(i))) {
				tabIdList.add(modelList.get(i));
				newTab(modelList.get(i).toString(),
						modelManager.getActiveWorkspaceModel());
			}
		}

		List<Integer> removeList = new ArrayList<Integer>();
		for (int i = 0; i < tabIdList.size(); i++) {
			if (!modelList.contains(tabIdList.get(i))) {
				removeList.add(i);
			}
		}

		if (modelList.isEmpty()) {
			tabIdList.clear();
			tabManager.removeAllTabs();

		} else {
			for (Integer i : removeList) {
				tabIdList.remove((int) i);
				tabManager.removeTab((int) i);
			}
		}

		if (modelManager.getActiveWorkspaceIndex() >= 1) {
			tabManager.setSelectedTabIndex(modelManager
					.getActiveWorkspaceIndex());
		}

		tabManager.updateTabbedPane();
	}

	/**
	 * Returns the panel of the centerStage.
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel() {
		return centerStagePanel;
	}

	/**
	 * Returns the tabManager.
	 * 
	 * @return TabManager
	 */
	public TabManager getTabManager() {
		return tabManager;
	}

	/**
	 * Sets the background of the canvas.
	 * 
	 * @param background
	 *            IBackground
	 */
	public void setBackground(IBackground background) {
		Canvas.setBackground(background);
	}

}
