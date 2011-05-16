package edu.chl.tda367.booleancircuits.controller.implementation;

import static org.junit.Assert.*;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public class ActionControllerTest {

	@Test
	public void testActionController() {
		new ActionController(new MasterController(new ModelManager()));
	}

	@Test
	public void testGetNewWorkspaceAction() {
		assertNotNull(new ActionController(new MasterController(new ModelManager())).getNewWorkspaceAction());
	}

	@Test
	public void testGetCloseActiveWorkspaceAction() {
		assertNotNull(new ActionController(new MasterController(new ModelManager())).getCloseActiveWorkspaceAction());
	}

	@Test
	public void testGetCloseAllWorkspacesAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOpenWorkspaceAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaveActiveWorkspaceAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaveAllWorkspacesAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCopySelectedComponentsAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCutSelectedComponentsAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPasteSelectedComponentAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUndoAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRedoAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExitAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRemoveSelectedComponentsAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSelectAllComponentsAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaveAsAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartClockAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPauseClockAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetImportWorkspaceAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShowAboutBoxAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShowHelpAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testStateChanged() {
		new ActionController(new MasterController(new ModelManager()))
				.stateChanged(new ChangeEvent(new JTabbedPane()));
	}

}
