package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.implementation.Model;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.view.ICenterStage;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;

/**
 * A class that represents a workspace with a panel and potentially several
 * tabs.
 * 
 * @author Boel
 * 
 */
public class CenterStage implements ICenterStage {

	private JPanel centerStagePanel = new JPanel();
	private List<IModel> tabIdList = new LinkedList<IModel>();
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

	private void newTab(String s, IModel m, ISelectionModel sm) {
		Canvas canvas = new Canvas(m, sm);
		canvas.addPropertyChangeListener(mainWindow);
		tabManager.addTab(s, canvas);
		TabPanel tabPanel = tabManager.getLastTabPanel();
		tabPanel.getCloseButton().setAction(closeWorkspace);
		tabPanel.getCloseButton().setToolTipText("Close");
	}

	public synchronized void update(ModelManager modelManager) {

		List<IModel> modelList = modelManager.getWorkspaces();

		for (int i = 0; i < modelList.size(); i++) {
			if (!tabIdList.contains(modelList.get(i))) {
				tabIdList.add(modelList.get(i));
				newTab(modelList.get(i).toString(),
						modelManager.getActiveWorkspaceModel(),
						modelManager.getActiveSelectionModel());
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

	public JPanel getPanel() {
		return centerStagePanel;
	}

	public TabManager getTabManager() {
		return tabManager;
	}

	public void setBackground(IBackground background) {
		Canvas.setBackground(background);
		tabManager.updateTabbedPane();
	}

	public void setUSStandard(boolean bool) {
		Canvas.setUSStandard(bool);
		tabManager.updateTabbedPane();
	}

}
