package edu.chl.tda367.booleancircuits.controller.implementation;

import static org.junit.Assert.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public class ActionControllerTest {

	@Test
	public void testActionController() {
		new ActionController(new MasterController(new ModelManager()));
	}

	@Test
	public void testGetCloseActiveWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getCloseActiveWorkspaceAction());
		assertTrue(ac.getCloseActiveWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetCloseAllWorkspacesAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getCloseAllWorkspacesAction());
		assertTrue(ac.getCloseAllWorkspacesAction() instanceof AbstractAction);

	}

	@Test
	public void testGetCopySelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getCopySelectedComponentsAction());
		assertTrue(ac.getCopySelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetCutSelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getCutSelectedComponentsAction());
		assertTrue(ac.getCutSelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetImportWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getImportWorkspaceAction());
		assertTrue(ac.getImportWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetNewWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getNewWorkspaceAction());
		assertTrue(ac.getNewWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetOpenWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getOpenWorkspaceAction());
		assertTrue(ac.getOpenWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetPasteSelectedComponentAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getPasteSelectedComponentAction());
		assertTrue(ac.getPasteSelectedComponentAction() instanceof AbstractAction);
	}

	@Test
	public void testGetPauseClockAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getPauseClockAction());
		assertTrue(ac.getPauseClockAction() instanceof AbstractAction);

	}

	@Test
	public void testGetRedoAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getRedoAction());
		assertTrue(ac.getRedoAction() instanceof AbstractAction);
	}

	@Test
	public void testGetRemoveSelectedComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getRemoveSelectedComponentsAction());
		assertTrue(ac.getRemoveSelectedComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveActiveWorkspaceAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getSaveActiveWorkspaceAction());
		assertTrue(ac.getSaveActiveWorkspaceAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveAllWorkspacesAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getSaveAllWorkspacesAction());
		assertTrue(ac.getSaveAllWorkspacesAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSaveAsAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getSaveAsAction());
		assertTrue(ac.getSaveAsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetSelectAllComponentsAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getSelectAllComponentsAction());
		assertTrue(ac.getSelectAllComponentsAction() instanceof AbstractAction);
	}

	@Test
	public void testGetShowHelpAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getShowHelpAction());
		assertTrue(ac.getShowHelpAction() instanceof AbstractAction);
	}

	@Test
	public void testGetStartClockAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getStartClockAction());
		assertTrue(ac.getStartClockAction() instanceof AbstractAction);
	}

	@Test
	public void testGetUndoAction() {
		ActionController ac = new ActionController(new MasterController(
				new ModelManager()));
		assertNotNull(ac.getUndoAction());
		assertTrue(ac.getUndoAction() instanceof AbstractAction);
	}

	@Test
	public void testStateChanged() {
		new ActionController(new MasterController(new ModelManager()))
				.stateChanged(new ChangeEvent(new JTabbedPane()));
	}

}
