package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
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
	private Action closeWorkspace;
	private IMasterController mc;
	private List<IModel> tabIdList = new LinkedList<IModel>();
	private TabManager tabManager = new TabManager();

	/** Returns an instance of Canvas. */
	public CenterStage(final Action closeWorkspace,
			final IMasterController masterController) {
		mc = masterController;
		centerStagePanel.add(tabManager.getTabbedPane());
		centerStagePanel.setLayout(new GridLayout(1, 1));
		this.closeWorkspace = closeWorkspace;
	}

	@Override
	public JPanel getPanel() {
		return centerStagePanel;
	}

	@Override
	public TabManager getTabManager() {
		return tabManager;
	}

	@Override
	public void setBackground(final IBackground background) {
		Canvas.setBackground(background);
		tabManager.updateTabbedPane();
	}

	@Override
	public void setUSStandard(final boolean bool) {
		Canvas.setUSStandard(bool);
		tabManager.updateTabbedPane();
	}

	@SuppressWarnings("boxing")
	@Override
	public synchronized void update(final ModelManager modelManager) {
		List<IModelWrapper> modelList = modelManager.getWorkspaces();
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
				tabManager.removeTab(i);
			}
		}

		if (modelManager.getActiveWorkspaceIndex() >= 1) {
			tabManager.setSelectedTabIndex(modelManager
					.getActiveWorkspaceIndex());
		}
		if (modelManager.getWorkspaces().size() > 0) {
			for (int i = 0; i < tabManager.getTabCount(); i++) {
				TabPanel panel = tabManager.getTabPanel(i);
				if (panel != null) {
					panel.setTabPanelTitle(modelManager.getWorkspaces().get(i)
							.toString());
				}
			}
		}

		tabManager.updateTabbedPane();
	}

	private void newTab(final String s, final IModel m, final ISelectionModel sm) {
		Canvas canvas = new Canvas(m, sm, mc);
		tabManager.addTab(s, canvas);
		TabPanel tabPanel = tabManager.getLastTabPanel();
		tabPanel.getCloseButton().setAction(closeWorkspace);
		tabPanel.getCloseButton().setToolTipText("Close");
	}

}
