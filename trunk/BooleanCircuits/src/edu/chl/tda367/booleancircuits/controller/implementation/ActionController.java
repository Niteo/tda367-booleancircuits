package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.chl.tda367.booleancircuits.controller.IActionController;
import edu.chl.tda367.booleancircuits.view.implementation.AboutBox;

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

	private final Action saveAsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveActiveWorkspace(true);
		}

	};

	private final Action newWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.newWorkspace();
		}

	};

	private final Action closeActiveWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mc.closeActiveWorkspace();
		}
	};

	private final Action closeAllWorkspacesAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.closeAllWorkspaces();
		}
	};

	private final Action openWorkspaceAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			mc.openWorkspace();
		}
	};

	private final Action saveActiveWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveActiveWorkspace(false);
		}
	};

	private final Action saveAllWorkspacesAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.saveAllWorkspaces();
		}
	};

	private final Action copySelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.copySelectedComponents();
		}
	};

	private final Action cutSelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.cutSelectedComponents();
		}
	};

	private final Action pasteSelectedComponentAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.pasteSelectedComponents();
		}
	};

	private final Action undoAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO undo
		}
	};

	private final Action redoAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO redo
		}
	};

	private final Action exitAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	private final Action removeSelectedComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.removeSelectedComponents();
		}
	};

	private final Action selectAllComponentsAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			mc.selectAllComponents();
		}
	};

	private final Action pauseClockAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pauseClockAction.setEnabled(false);
			startClockAction.setEnabled(true);
			mc.toggleClockTimer();
		}

	};

	private final Action startClockAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			pauseClockAction.setEnabled(true);
			startClockAction.setEnabled(false);
			mc.toggleClockTimer();
		}
	};

	private final Action importWorkspaceAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mc.importWorkspace();
		}
	};

	private final Action showAboutBoxAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String infoText = "<html>Boolean Circuits<br><br>Version: 1.0 <br><br>This software is developed by Robert Kaufmann, Anton Lin, Boel Nelson and Jennifer Panditha at Chalmers university of technology.</html>";
			Icon logo = new ImageIcon("resources/icons/cross-icon.png");
			new AboutBox(infoText, logo);
		}
	};

	private final Action showHelpAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Runtime.getRuntime().exec("manual.pdf");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Couldn't find the file manual.pdf!");
			}
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

	@Override
	public Action getStartClockAction() {
		return startClockAction;
	}

	@Override
	public Action getPauseClockAction() {
		return pauseClockAction;
	}

	@Override
	public Action getImportWorkspaceAction() {
		return importWorkspaceAction;
	}

	@Override
	public Action getShowAboutBoxAction() {
		return showAboutBoxAction;
	}

	@Override
	public Action getShowHelpAction() {
		return showHelpAction;
	}

}
