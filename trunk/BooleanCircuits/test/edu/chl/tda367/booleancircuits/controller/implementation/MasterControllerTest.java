package edu.chl.tda367.booleancircuits.controller.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public class MasterControllerTest {

	@Test
	public void testMasterController() {
		MasterController masterController = new MasterController(
				new ModelManager());
		assertTrue(masterController!=null);
	}

	@Test
	public void testCloseActiveWorkspace() {
		
	}

	@Test
	public void testCloseAllWorkspaces() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCloseWorkspace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testNewWorkspace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testOpenWorkspace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSaveActiveWorkspace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSaveAllWorkspaces() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetActiveWorkspace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUndo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRedo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddComponent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveSelectedComponents() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSelectAllComponents() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSelectComponent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCopySelectedComponents() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCutSelectedComponents() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPasteSelectedComponents() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetChosenComponent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveComponent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testConnectComponent() {
		fail("Not yet implemented"); // TODO
	}

}
