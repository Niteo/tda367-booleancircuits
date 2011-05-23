package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public class MasterControllerTest {

	@Test
	public void testAddComponent() {
		new MasterController(new ModelManager()).addComponent(new Point(0, 0));
	}

	@Test
	public void testCloseActiveWorkspace() {
		new MasterController(new ModelManager()).closeActiveWorkspace();
	}

	@Test
	public void testCloseAllWorkspaces() {
		new MasterController(new ModelManager()).closeAllWorkspaces();
	}

	@Test
	public void testCloseWorkspace() {
		new MasterController(new ModelManager()).closeWorkspace(0);
	}

	@Test
	public void testConnectComponent() {
		new MasterController(new ModelManager()).connectComponent(new GateWrapper(
				new AndGate(2)), 0);
	}

	@Test
	public void testCopySelectedComponents() {
		new MasterController(new ModelManager()).copySelectedComponents();
	}

	@Test
	public void testCutSelectedComponents() {
		new MasterController(new ModelManager()).cutSelectedComponents();
	}

	@Test
	public void testMasterController() {
		new MasterController(new ModelManager());
	}

	@Test
	public void testNewWorkspace() {
		new MasterController(new ModelManager()).newWorkspace();
	}

	@Test
	public void testOpenWorkspace() {
		new MasterController(new ModelManager()).openWorkspace();
	}

	@Test
	public void testPasteSelectedComponents() {
		new MasterController(new ModelManager()).pasteSelectedComponents();
	}

	@Test
	public void testPasteSelectedComponentsPoint() {
		new MasterController(new ModelManager())
				.pasteSelectedComponents(new Point(0, 0));
	}

	@Test
	public void testRemoveComponent() {
		new MasterController(new ModelManager())
				.removeComponent(new GateWrapper(new AndGate(2)));
	}

	@Test
	public void testRemoveSelectedComponents() {
		new MasterController(new ModelManager()).removeSelectedComponents();
	}

	@Test
	public void testSaveActiveWorkspace() {
		new MasterController(new ModelManager()).saveActiveWorkspace(false);
	}

	@Test
	public void testSaveAllWorkspaces() {
		new MasterController(new ModelManager()).saveAllWorkspaces();
	}

	@Test
	public void testSelectAllComponents() {
		new MasterController(new ModelManager()).selectAllComponents();
	}

	@Test
	public void testSelectComponent() {
		new MasterController(new ModelManager()).selectComponent(
				new Point(0, 0), false);
	}

	@Test
	public void testSetActiveWorkspace() {
		new MasterController(new ModelManager()).setActiveWorkspace(0);
	}

	@Test
	public void testSetChosenComponent() {
		new MasterController(new ModelManager())
				.setChosenComponent(new GateWrapper(new AndGate(2)));
	}

}
