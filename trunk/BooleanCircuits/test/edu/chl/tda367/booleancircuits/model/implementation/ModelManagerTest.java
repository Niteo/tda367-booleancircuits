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
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;

public class ModelManagerTest {

	@Test
	public void testCircuitManager() {
		new CircuitManager();
	}

	@Test
	public void testGetActiveSelectionModel() {
		CircuitManager mm = new CircuitManager();
		assertNull(mm.getActiveSelectionModel());
	}

	@Test
	public void testGetActiveCircuitIndex() {
		CircuitManager mm = new CircuitManager();
		assertTrue(mm.getActiveCircuitIndex() == -1);
	}

	@Test
	public void testGetActiveCircuit() {
		CircuitManager mm = new CircuitManager();
		assertNull(mm.getActiveCircuit());
	}

	@Test
	public void testGetCircuits() {
		CircuitManager mm = new CircuitManager();
		assertTrue(mm.getCircuits().size() == 0);
	}

	@Test
	public void testAddCircuit() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw = new CircuitWrapper();

		mm.addCircuit(mw);
		assertTrue(mm.getCircuits().size() == 1);
	}

	@Test
	public void testNewCircuit() {
		CircuitManager mm = new CircuitManager();

		assertTrue(mm.getCircuits().size() == 0);
		mm.newCircuit();
		assertTrue(mm.getCircuits().size() == 1);
	}

	@Test
	public void testAddComponent() {
		CircuitManager mm = new CircuitManager();

		mm.newCircuit();
		mm.addComponent(new GateWrapper(new ConstantGate(true)),
				new Point(0, 0));

		assertTrue(mm.getActiveCircuit().getComponent(new Point(0, 0))
				.getGate() instanceof ConstantGate);
	}

	@Test
	public void testClock() {
		CircuitManager cm = new CircuitManager();
		cm.newCircuit();
		GateWrapper clock = new GateWrapper(new Clock());
		cm.addComponent(clock, new Point(0, 0));
		cm.clockActiveCircuit();
		assertTrue(clock.getOutputValue(0) == true);
	}

	@Test
	public void testSelectComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newCircuit();
		cm.addComponent(new GateWrapper(new ConstantGate(true)),
				new Point(0, 0));
		cm.selectComponents(new Point(-1, -1), new Point(1, 1));
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
		cm.newCircuit();
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
	public void testCloseActiveCircuit() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addCircuit(mw1);
		mm.addCircuit(mw2);

		assertTrue(mm.getActiveCircuitIndex() == 1);
		mm.closeActiveCircuit();
		assertTrue(mm.getActiveCircuitIndex() == 0);
		assertTrue(mm.getActiveCircuit() == mw1);
	}

	@Test
	public void testCloseAllCircuits() {
		CircuitManager mm = new CircuitManager();

		mm.newCircuit();
		mm.newCircuit();
		mm.closeAllCircuits();
		assertTrue(mm.getCircuits().size() == 0);
	}

	@Test
	public void testCloseCircuit() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addCircuit(mw1);
		mm.addCircuit(mw2);

		mm.closeCircuit(1);
		assertTrue(mm.getActiveCircuit() == mw1);
		mm.closeCircuit(-1);
	}

	@Test
	public void testConnectComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newCircuit();
		cm.connectComponents(new GateWrapper(new AndGate(2)), new GateWrapper(
				new NandGate(2)), 0, 0);
	}

	@Test
	public void testSelectComponent() {
		new CircuitManager().selectComponent(new Point(0, 0), false);
	}

	@Test
	public void testIsSelectedComponent() {
		CircuitManager cm = new CircuitManager();
		cm.isSelectedComponent(null);
		cm.newCircuit();
		GateWrapper gw = new GateWrapper(new NandGate(220));
		cm.selectComponent(new Point(0, 0), false);
		cm.addComponent(gw, new Point(0, 0));
		cm.isSelectedComponent(gw);
	}

	@Test
	public void testManualPropertyChanged() {
		new CircuitManager().manualPropertyChanged();
	}

	@Test
	public void testRemoveComponent() {
		CircuitManager cm = new CircuitManager();
		GateWrapper gw = new GateWrapper(new ConstantGate(true));
		cm.newCircuit();
		cm.addComponent(gw, new Point(1, 1));
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
		cm.newCircuit();
		cm.removeSelectedComponents();
	}

	@Test
	public void testSelectAllComponents() {
		CircuitManager cm = new CircuitManager();
		cm.newCircuit();
		cm.selectAllComponents();
	}

	@Test
	public void testSetActiveCircuit() {
		CircuitManager mm = new CircuitManager();
		CircuitWrapper mw1 = new CircuitWrapper();
		CircuitWrapper mw2 = new CircuitWrapper();

		mm.addCircuit(mw1);
		mm.addCircuit(mw2);
		mm.setActiveCircuit(0);

		assertTrue(mm.getActiveCircuit() == mw1);
	}

	@Test
	public void testGetGateWrapper() {
		CircuitManager cm = new CircuitManager();
		cm.getGateWrapper(null);
		cm.newCircuit();
		cm.getGateWrapper(null);
	}
}