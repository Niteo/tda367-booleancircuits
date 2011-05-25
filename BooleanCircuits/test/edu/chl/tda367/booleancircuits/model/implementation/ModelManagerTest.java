package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;

public class ModelManagerTest {

	@Test
	public void testClock(){
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		GateWrapper clock = new GateWrapper(new Clock());
		cm.addComponent(clock, new Point(0,0));
		cm.clockActiveModel();
		assertTrue(clock.getOutputValue(0) == true);
	}
	
	@Test
	public void testSelectComponents(){
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		cm.addComponent(new GateWrapper(new ConstantGate(true)), new Point(0,0));
		cm.selectComponents(new Point(-1,-1), new Point(1,1));
	}
	
	@Test
	public void testAddComponent() {
		CircuitManager mm = new CircuitManager();

		mm.newWorkspace();
		mm.addComponent(new GateWrapper(new ConstantGate(true)),
				new Point(0, 0));

		assertTrue(mm.getActiveWorkspaceModel().
				getComponent(new Point(0, 0)).
				getGate() instanceof ConstantGate);
	}

	@Test
	public void testAddComponentsList() {
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		list.add(new GateWrapper(new AndGate(2)));
		new CircuitManager().addComponents(list);
	}

	@Test
	public void testAddComponentsListPoint() {
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		list.add(new GateWrapper(new AndGate(2)));
		cm.addComponents(list, new Point(0, 0));
	}

	@Test
	public void testAddPropertyChangeListener() {
		new CircuitManager()
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						return;
					}
				});
	}

	@Test
	public void testAddWorkspace() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw = new CircuitWrapper();

		mm.addWorkspace(mw);
		assertTrue(mm.getWorkspaces().size() == 1);
	}

	@Test
	public void testCloseActiveWorkspace() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);

		assertTrue(mm.getActiveWorkspaceIndex() == 1);
		mm.closeActiveWorkspace();
		assertTrue(mm.getActiveWorkspaceIndex() == 0);
		assertTrue(mm.getActiveWorkspaceModel() == mw1);
	}

	@Test
	public void testCloseAllWorkspaces() {
		CircuitManager mm = new CircuitManager();

		mm.newWorkspace();
		mm.newWorkspace();
		mm.closeAllWorkspaces();
		assertTrue(mm.getWorkspaces().size() == 0);
	}

	@Test
	public void testCloseWorkspace() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);

		mm.closeWorkspace(1);
		assertTrue(mm.getActiveWorkspaceModel() == mw1);
		mm.closeWorkspace(-1);
	}

	@Test
	public void testConnectComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		cm.connectComponents(new GateWrapper(new AndGate(2)),
				new GateWrapper(new NandGate(2)), 0, 0);
	}

	@Test
	public void testGetActiveSelectionModel() {
		CircuitManager mm = new CircuitManager();
		assertNull(mm.getActiveSelectionModel());
	}

	@Test
	public void testGetActiveWorkspaceIndex() {
		CircuitManager mm = new CircuitManager();
		assertTrue(mm.getActiveWorkspaceIndex() == -1);
	}

	@Test
	public void testGetActiveWorkspaceModel() {
		CircuitManager mm = new CircuitManager();
		assertNull(mm.getActiveWorkspaceModel());
	}

	@Test
	public void testGetWorkspaces() {
		CircuitManager mm = new CircuitManager();
		assertTrue(mm.getWorkspaces().size() == 0);
	}

	@Test
	public void testIsSelectedComponent() {
		CircuitManager cm = new CircuitManager();
		cm.isSelectedComponent(null);
		cm.newWorkspace();
		GateWrapper gw = new GateWrapper(new NandGate(220));
		cm.selectComponent(new Point(0,0), false);
		cm.addComponent(gw, new Point(0,0));
		cm.isSelectedComponent(gw);
	}

	@Test
	public void testManualPropertyChanged() {
		new CircuitManager().manualPropertyChanged();
	}

	@Test
	public void testModelManager() {
		new CircuitManager();
	}

	@Test
	public void testNewWorkspace() {
		CircuitManager mm = new CircuitManager();

		assertTrue(mm.getWorkspaces().size() == 0);
		mm.newWorkspace();
		assertTrue(mm.getWorkspaces().size() == 1);
	}

	@Test
	public void testRemoveComponent() {
		CircuitManager cm = new CircuitManager();
		GateWrapper gw = new GateWrapper(new ConstantGate(true));
		cm.newWorkspace();
		cm.addComponent(gw, new Point(1,1));
		cm.removeComponent(gw);
	}

	@Test
	public void testRemovePropertyChangeListener() {
		PropertyChangeListener listener = new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				return;
			}
		};
		new CircuitManager().addPropertyChangeListener(listener);
		new CircuitManager().removePropertyChangeListener(listener);
	}

	@Test
	public void testRemoveSelectedComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		cm.removeSelectedComponents();
	}

	@Test
	public void testSelectAllComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newWorkspace();
		cm.selectAllComponents();
	}

	@Test
	public void testSelectComponent() {
		new CircuitManager().selectComponent(new Point(0, 0), false);
	}

	@Test
	public void testSetActiveWorkspace() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);
		mm.setActiveWorkspace(0);

		assertTrue(mm.getActiveWorkspaceModel() == mw1);
	}
	
	@Test
	public void testGetGateWrapper(){
		CircuitManager cm = new CircuitManager();
		cm.getGateWrapper(null);
		cm.newWorkspace();
		cm.getGateWrapper(null);
	}
}