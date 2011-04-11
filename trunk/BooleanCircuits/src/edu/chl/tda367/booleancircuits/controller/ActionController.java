package edu.chl.tda367.booleancircuits.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * A listener class to control action events.
 * @author Boel
 *
 */
public class ActionController {

	private MasterController mc;

	/**
	 * Returns an instance of ActionController.
	 * @param MasterController
	 */
	public ActionController(MasterController masterController) {
		mc = masterController;
	}

	private Action newWorkspaceAction = new AbstractAction(){

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
	
	private Action openWorkspaceAction = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO add correct path
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
			//TODO add correct path
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
			//TODO add correct coordinates
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
	
	private Action closeAction = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	private Action addComponentAction = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO get coords and component
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

	public Action getNewWorkspaceAction() {
		return newWorkspaceAction;
	}
	
	public Action getCloseActiveWorkspaceAction() {
		return closeActiveWorkspaceAction;
	}

	public Action getOpenWorkspaceAction() {
		return openWorkspaceAction;
	}

	public Action getSaveActiveWorkspaceAction() {
		return saveActiveWorkspaceAction;
	}

	public Action getSaveActiveWorskpaceAsComponentAction() {
		return saveActiveWorskpaceAsComponentAction;
	}

	public Action getSaveAllWorkspacesAction() {
		return saveAllWorkspacesAction;
	}

	public Action getCopySelectedComponentsAction() {
		return copySelectedComponentsAction;
	}

	public Action getCutSelectedComponentsAction() {
		return cutSelectedComponentsAction;
	}

	public Action getPasteSelectedComponentAction() {
		return pasteSelectedComponentAction;
	}

	public Action getUndoAction() {
		return undoAction;
	}

	public Action getRedoAction() {
		return redoAction;
	}
	
	public Action getCloseAction() {
		return closeAction;
	}

	public Action getAddComponentAction() {
		return addComponentAction;
	}

	public Action getRemoveSelectedComponentsAction() {
		return removeSelectedComponentsAction;
	}

	public Action getSelectAllComponentsAction() {
		return selectAllComponentsAction;
	}

	public Action getSelectComponentAction() {
		return selectComponentAction;
	}
	
	
}
