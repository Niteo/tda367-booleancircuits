package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.implementation.CircuitManager;

public class MasterControllerTest {


	@Test(expected=NullPointerException.class)
	public void testMasterController() {
		new MasterController(new CircuitManager());
		new MasterController(null);
	}

	@Test
	public void testAddComponent() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.setChosenComponent(new GateWrapper(new ConstantGate(true)));
		mc.addComponent(new Point(0,0));
	}

	@Test
	public void testCloseActiveWorkspace() {
		new MasterController(new CircuitManager()).closeActiveWorkspace();
	}

	@Test
	public void testCloseAllWorkspaces() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.newWorkspace();
		mc.closeAllWorkspaces();
	}

	@Test
	public void testCloseWorkspace() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.newWorkspace();
		mc.closeWorkspace(1);
		mc.closeWorkspace(0);
	}

	@Test
	public void testConnectComponent() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.connectComponent(new GateWrapper(
				new AndGate(2)), 0);
		mc.connectComponent(new GateWrapper(
				new AndGate(2)), 0);
		mc.connectComponent(null, 0);
	}

	@Test
	public void testCopySelectedComponents() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.newWorkspace();
		mc.setChosenComponent(new GateWrapper(new ConstantGate(true)));
		mc.addComponent(new Point(0,0));
		mc.copySelectedComponents();
	}

	@Test
	public void testCutSelectedComponents() {
		new MasterController(new CircuitManager()).cutSelectedComponents();
	}

	@Test
	public void testNewWorkspace() {
		new MasterController(new CircuitManager()).newWorkspace();
	}

	@Test
	public void testPasteSelectedComponents() {
		MasterController mc = new MasterController(new CircuitManager());
		mc.newWorkspace();
		mc.copySelectedComponents();
		mc.pasteSelectedComponents();
	}

	@Test
	public void testSelectComponents(){
		MasterController mc = new MasterController(new CircuitManager());
		mc.selectComponents(new Point(0,0), new Point(50,50));
	}

	@Test
	public void testPasteSelectedComponentsPoint() {
		new MasterController(new CircuitManager())
				.pasteSelectedComponents(new Point(0, 0));
	}

	@Test
	public void testRemoveComponent() {
		new MasterController(new CircuitManager())
				.removeComponent(new GateWrapper(new AndGate(2)));
	}

	@Test
	public void testRemoveSelectedComponents() {
		new MasterController(new CircuitManager()).removeSelectedComponents();
	}

	@Test
	public void testSaveActiveWorkspace() {
		new MasterController(new CircuitManager()).saveActiveWorkspace(false);
	}

	@Test
	public void testSaveAllWorkspaces() {
		new MasterController(new CircuitManager()).saveAllWorkspaces();
	}

	@Test
	public void testSelectAllComponents() {
		new MasterController(new CircuitManager()).selectAllComponents();
	}

	@Test
	public void testSelectComponent() {
		new MasterController(new CircuitManager()).selectComponent(
				new Point(0, 0), false);
	}

	@Test
	public void testSetActiveWorkspace() {
		new MasterController(new CircuitManager()).setActiveWorkspace(0);
	}

	@Test
	public void testSetChosenComponent() {
		new MasterController(new CircuitManager())
				.setChosenComponent(new GateWrapper(new AndGate(2)));
	}

	@Test
	public void testToggleClockTimer(){
		MasterController mc = new MasterController(new CircuitManager());
		mc.toggleClockTimer();
		mc.toggleClockTimer();
	}
	
	@Test
	public void testImportWorkspace(){
		MasterController mc = new MasterController(new CircuitManager());
		mc.importWorkspace();
	}

}
