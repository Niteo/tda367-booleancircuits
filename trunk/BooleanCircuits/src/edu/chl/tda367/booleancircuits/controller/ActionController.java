package edu.chl.tda367.booleancircuits.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A class to control action events.
 * 
 * @author Boel
 * 
 */
public class ActionController implements ChangeListener {

	private MasterController mc;

	/**
	 * Returns an instance of ActionController.
	 * 
	 * @param MasterController
	 */
	public ActionController(MasterController masterController) {
		mc = masterController;
	}

	private Action newWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.newWorkspace();
		}

	};

	private Action closeActiveWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mc.closeActiveWorkspace();
		}
	};

	private Action closeAllWorkspacesAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.closeAllWorkspaces();
		}
	};

	private Action openWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO add correct path
			mc.openWorkspace("");
		}
	};

	private Action saveActiveWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveActiveWorkspace();
		}
	};

	private Action saveActiveWorskpaceAsComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO add correct path
			mc.saveActiveWorskpaceAsComponent("");
		}
	};

	private Action saveAllWorkspacesAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveAllWorkspaces();
		}
	};

	private Action copySelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.copySelectedComponents();
		}
	};

	private Action cutSelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.cutSelectedComponents();
		}
	};

	private Action pasteSelectedComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO add correct coordinates
			mc.pasteSelectedComponent(null);
		}
	};

	private Action undoAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO undo
		}
	};

	private Action redoAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO redo
		}
	};

	private Action exitAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	private Action addComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO get coords and component
			mc.addComponent(null, null);
		}
	};

	private Action removeSelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.removeSelectedComponents();
		}
	};

	private Action selectAllComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.selectAllComponents();
		}
	};

	private Action selectComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO get the coords of the selected component
			mc.selectComponent(null);
		}
	};

	/**
	 * Returns the newWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getNewWorkspaceAction() {
		return newWorkspaceAction;
	}

	/**
	 * Returns the closeActiveWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getCloseActiveWorkspaceAction() {
		return closeActiveWorkspaceAction;
	}

	/**
	 * Returns the closeAllWorkspaces action.
	 * 
	 * @return Action
	 */
	public Action getCloseAllWorkspacesAction() {
		return closeAllWorkspacesAction;
	}

	/**
	 * Returns the openWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getOpenWorkspaceAction() {
		return openWorkspaceAction;
	}

	/**
	 * Returns the saveActiveWorkspace action.
	 * 
	 * @return Action
	 */
	public Action getSaveActiveWorkspaceAction() {
		return saveActiveWorkspaceAction;
	}

	/**
	 * Returns the saveActiveWorkspaceAsComponent action.
	 * 
	 * @return Action
	 */
	public Action getSaveActiveWorskpaceAsComponentAction() {
		return saveActiveWorskpaceAsComponentAction;
	}

	/**
	 * Returns the saveAllWorkspaces action.
	 * 
	 * @return Action
	 */
	public Action getSaveAllWorkspacesAction() {
		return saveAllWorkspacesAction;
	}

	/**
	 * Returns the CopySelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getCopySelectedComponentsAction() {
		return copySelectedComponentsAction;
	}

	/**
	 * Returns the cutSelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getCutSelectedComponentsAction() {
		return cutSelectedComponentsAction;
	}

	/**
	 * Returns the pasteSelectedComponent action.
	 * 
	 * @return Action
	 */
	public Action getPasteSelectedComponentAction() {
		return pasteSelectedComponentAction;
	}

	/**
	 * Returns the undo action.
	 * 
	 * @return Action
	 */
	public Action getUndoAction() {
		return undoAction;
	}

	/**
	 * Returns the redo action.
	 * 
	 * @return Action
	 */
	public Action getRedoAction() {
		return redoAction;
	}

	/**
	 * Returns the exit action.
	 * 
	 * @return Action
	 */
	public Action getExitAction() {
		return exitAction;
	}

	/**
	 * Returns the addComponent action.
	 * 
	 * @return Action
	 */
	public Action getAddComponentAction() {
		return addComponentAction;
	}

	/**
	 * Returns the removeSelectedComponents action.
	 * 
	 * @return Action
	 */
	public Action getRemoveSelectedComponentsAction() {
		return removeSelectedComponentsAction;
	}

	/**
	 * Returns the selectAllComponents action.
	 * 
	 * @return Action
	 */
	public Action getSelectAllComponentsAction() {
		return selectAllComponentsAction;
	}

	/**
	 * Returns the selectComponent action.
	 * 
	 * @return Action
	 */
	public Action getSelectComponentAction() {
		return selectComponentAction;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		int selectedIndex = tabbedPane.getSelectedIndex();

		if (selectedIndex < 0) {
			return;
		} else {
			mc.setActiveWorkspace(selectedIndex);
		}

	}

}
