package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.chl.tda367.booleancircuits.controller.IActionController;

/**
 * A class to control action events.
 * 
 * @author Boel
 * 
 */
public class ActionController implements ChangeListener, IActionController {

	private MasterController mc;

	/**
	 * Returns an instance of ActionController.
	 * 
	 * @param MasterController
	 */
	public ActionController(MasterController masterController) {
		mc = masterController;
	}

	private Action saveAsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveActiveWorkspace(true);
		}

	};
	
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
			mc.openWorkspace();
		}
	};

	private Action saveActiveWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveActiveWorkspace(false);
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
			mc.pasteSelectedComponents();
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

	private Action insertComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO implement me!

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

	@Override
	public Action getNewWorkspaceAction() {
		return newWorkspaceAction;
	}

	@Override
	public Action getCloseActiveWorkspaceAction() {
		return closeActiveWorkspaceAction;
	}

	@Override
	public Action getCloseAllWorkspacesAction() {
		return closeAllWorkspacesAction;
	}

	@Override
	public Action getOpenWorkspaceAction() {
		return openWorkspaceAction;
	}

	@Override
	public Action getSaveActiveWorkspaceAction() {
		return saveActiveWorkspaceAction;
	}

	@Override
	public Action getSaveAllWorkspacesAction() {
		return saveAllWorkspacesAction;
	}

	@Override
	public Action getCopySelectedComponentsAction() {
		return copySelectedComponentsAction;
	}

	@Override
	public Action getCutSelectedComponentsAction() {
		return cutSelectedComponentsAction;
	}

	@Override
	public Action getPasteSelectedComponentAction() {
		return pasteSelectedComponentAction;
	}

	@Override
	public Action getUndoAction() {
		return undoAction;
	}

	@Override
	public Action getRedoAction() {
		return redoAction;
	}

	@Override
	public Action getExitAction() {
		return exitAction;
	}

	@Override
	public Action getInsertComponentAction() {
		return insertComponentAction;
	}

	@Override
	public Action getRemoveSelectedComponentsAction() {
		return removeSelectedComponentsAction;
	}

	@Override
	public Action getSelectAllComponentsAction() {
		return selectAllComponentsAction;
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

	@Override
	public Action getSaveAsAction() {
		return saveAsAction;
	}

}
