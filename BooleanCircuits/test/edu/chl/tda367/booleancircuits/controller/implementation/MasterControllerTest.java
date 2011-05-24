package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public class MasterControllerTest {
	

	@Test(expected=NullPointerException.class)
	public void testMasterController() {
		new MasterController(new ModelManager());
		new MasterController(null);
	}
	
	@Test
	public void testAddComponent() {
		MasterController mc = new MasterController(new ModelManager());
		mc.setChosenComponent(new GateWrapper(new ConstantGate(true)));
		mc.addComponent(new Point(0,0));
	}

	@Test
	public void testCloseActiveWorkspace() {
		new MasterController(new ModelManager()).closeActiveWorkspace();
	}

	@Test
	public void testCloseAllWorkspaces() {
		MasterController mc = new MasterController(new ModelManager());
		mc.newWorkspace();
		mc.closeAllWorkspaces();
	}

	@Test
	public void testCloseWorkspace() {
		MasterController mc = new MasterController(new ModelManager());
		mc.newWorkspace();
		mc.closeWorkspace(1);
		mc.closeWorkspace(0);
	}

	@Test
	public void testConnectComponent() {
		MasterController mc = new MasterController(new ModelManager());
		mc.connectComponent(new GateWrapper(
				new AndGate(2)), 0);
		mc.connectComponent(new GateWrapper(
				new AndGate(2)), 0);
		mc.connectComponent(null, 0);
	}

	@Test
	public void testCopySelectedComponents() {
		MasterController mc = new MasterController(new ModelManager());
		mc.newWorkspace();
		mc.setChosenComponent(new GateWrapper(new ConstantGate(true)));
		mc.addComponent(new Point(0,0));
		mc.copySelectedComponents();
	}

	@Test
	public void testCutSelectedComponents() {
		new MasterController(new ModelManager()).cutSelectedComponents();
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
		MasterController mc = new MasterController(new ModelManager());
		mc.newWorkspace();
		mc.copySelectedComponents();
		mc.pasteSelectedComponents();
	}
	
	@Test
	public void testSelectComponents(){
		MasterController mc = new MasterController(new ModelManager());
		mc.selectComponents(new Point(0,0), new Point(50,50));
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
	
	@Test
	public void testToggleClockTimer(){
		MasterController mc = new MasterController(new ModelManager());
		mc.toggleClockTimer();
		mc.toggleClockTimer();
	}

}
