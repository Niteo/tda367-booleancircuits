package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

import edu.chl.tda367.booleancircuits.controller.IActionController;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

/**
 * A class to control action events.
 *
 * @author Boel
 *
 */
public class ActionController implements ChangeListener, IActionController {

	private final Action closeActiveWorkspaceAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			mc.closeActiveWorkspace();
		}
	};

	private final Action closeAllWorkspacesAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.closeAllWorkspaces();
		}
	};

	private final Action copySelectedComponentsAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.copySelectedComponents();
		}
	};

	private final Action cutSelectedComponentsAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.cutSelectedComponents();
		}
	};

	private final Action importWorkspaceAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			mc.importWorkspace();
		}
	};

	private MasterController mc;

	private final Action newWorkspaceAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.newWorkspace();
		}

	};

	private final Action openWorkspaceAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.openWorkspace();
		}
	};

	private final Action pasteSelectedComponentAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.pasteSelectedComponents();
		}
	};

	private final Action pauseClockAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			pauseClockAction.setEnabled(false);
			startClockAction.setEnabled(true);
			mc.toggleClockTimer();
		}

	};

	private final Action redoAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(final ActionEvent arg0) {
			// TODO redo
		}
	};

	private final Action removeSelectedComponentsAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.removeSelectedComponents();
		}
	};

	private final Action saveActiveWorkspaceAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.saveActiveWorkspace(false);
		}
	};

	private final Action saveAllWorkspacesAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.saveAllWorkspaces();
		}
	};

	private final Action saveAsAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.saveActiveWorkspace(true);
		}

	};

	private final Action selectAllComponentsAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
			mc.selectAllComponents();
		}
	};

	private final Action showHelpAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(final ActionEvent e) {
			File file = new File(Constants.manualPath);
			if (file.exists()) {
				Desktop desk = Desktop.getDesktop();
				try {
					desk.open(file);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Couldn't open manual.pdf!");
				}
			} else {
				JOptionPane
						.showMessageDialog(null, "Couldn't find manual.pdf!");
			}
		}
	};

	private final Action startClockAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			pauseClockAction.setEnabled(true);
			startClockAction.setEnabled(false);
			mc.toggleClockTimer();
		}
	};

	private final Action undoAction = new AbstractAction() {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(final ActionEvent arg0) {
			// TODO undo
		}
	};

	/**
	 * Returns an instance of ActionController.
	 *
	 * @param MasterController
	 */
	public ActionController(final MasterController masterController) {
		mc = masterController;
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
	public Action getCopySelectedComponentsAction() {
		return copySelectedComponentsAction;
	}

	@Override
	public Action getCutSelectedComponentsAction() {
		return cutSelectedComponentsAction;
	}

	@Override
	public Action getImportWorkspaceAction() {
		return importWorkspaceAction;
	}

	@Override
	public Action getNewWorkspaceAction() {
		return newWorkspaceAction;
	}

	@Override
	public Action getOpenWorkspaceAction() {
		return openWorkspaceAction;
	}

	@Override
	public Action getPasteSelectedComponentAction() {
		return pasteSelectedComponentAction;
	}

	@Override
	public Action getPauseClockAction() {
		return pauseClockAction;
	}

	@Override
	public Action getRedoAction() {
		return redoAction;
	}

	@Override
	public Action getRemoveSelectedComponentsAction() {
		return removeSelectedComponentsAction;
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
	public Action getSaveAsAction() {
		return saveAsAction;
	}

	@Override
	public Action getSelectAllComponentsAction() {
		return selectAllComponentsAction;
	}

	@Override
	public Action getShowHelpAction() {
		return showHelpAction;
	}

	@Override
	public Action getStartClockAction() {
		return startClockAction;
	}

	@Override
	public Action getUndoAction() {
		return undoAction;
	}

	@Override
	public void stateChanged(final ChangeEvent e) {
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		int selectedIndex = tabbedPane.getSelectedIndex();

		if (selectedIndex < 0) {
			return;
		} else {
			mc.setActiveWorkspace(selectedIndex);
		}

	}

}
