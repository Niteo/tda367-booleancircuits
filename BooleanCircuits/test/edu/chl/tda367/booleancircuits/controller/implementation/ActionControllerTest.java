package edu.chl.tda367.booleancircuits.controller.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.AbstractAction;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.implementation.CircuitManager;

public class ActionControllerTest {

	@Test
	public void testActionController() {
		new ActionController(new MasterController(new CircuitManager()));
	}

	@Test
	public void testGetCloseActiveWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getCloseActiveWorkspaceAction());
		assertTrue(ac.getCloseActiveWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetCloseAllWorkspacesAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getCloseAllWorkspacesAction());
		assertTrue(ac.getCloseAllWorkspacesAction() instanceof AbstractAction);

	}

	@Test
	public void testGetCopySelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getCopySelectedComponentsAction());
		assertTrue(ac.getCopySelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetCutSelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getCutSelectedComponentsAction());
		assertTrue(ac.getCutSelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetImportWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getImportWorkspaceAction());
		assertTrue(ac.getImportWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetNewWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getNewWorkspaceAction());
		assertTrue(ac.getNewWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetOpenWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getOpenWorkspaceAction());
		assertTrue(ac.getOpenWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetPasteSelectedComponentAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getPasteSelectedComponentAction());
		assertTrue(ac.getPasteSelectedComponentAction() instanceof AbstractAction);
	}

	@Test
	public void testGetPauseClockAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getPauseClockAction());
		assertTrue(ac.getPauseClockAction() instanceof AbstractAction);

	}

	@Test
	public void testGetRedoAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getRedoAction());
		assertTrue(ac.getRedoAction() instanceof AbstractAction);
	}

	@Test
	public void testGetRemoveSelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getRemoveSelectedComponentsAction());
		assertTrue(ac.getRemoveSelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveActiveWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getSaveActiveWorkspaceAction());
		assertTrue(ac.getSaveActiveWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveAllWorkspacesAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getSaveAllWorkspacesAction());
		assertTrue(ac.getSaveAllWorkspacesAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveAsAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getSaveAsAction());
		assertTrue(ac.getSaveAsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSelectAllComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getSelectAllComponentsAction());
		assertTrue(ac.getSelectAllComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetShowHelpAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getShowHelpAction());
		assertTrue(ac.getShowHelpAction() instanceof AbstractAction);
	}

	@Test
	public void testGetStartClockAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getStartClockAction());
		assertTrue(ac.getStartClockAction() instanceof AbstractAction);
	}

	@Test
	public void testGetUndoAction() {
		ActionController ac = new ActionController(new MasterController(
				new CircuitManager()));
		assertNotNull(ac.getUndoAction());
		assertTrue(ac.getUndoAction() instanceof AbstractAction);
	}

	@Test
	public void testStateChanged() {
		new ActionController(new MasterController(new CircuitManager()))
				.stateChanged(new ChangeEvent(new JTabbedPane()));

	}

	@Test
	public void testSetEnabledAction() {
		MasterController mc = new MasterController(new CircuitManager());
		ActionController ac = new ActionController(mc);

		ac.setActionsEnabled(true);

	}

}
