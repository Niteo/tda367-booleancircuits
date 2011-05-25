package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;

public class SelectionModelTest {

	@Test
	public void testGetNumberOfComponents() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getNumberOfComponents() == 0);

		sm.selectComponent(new GateWrapper(new ConstantGate(true)), false);
		assertTrue(sm.getNumberOfComponents() == 1);
	}

	@Test
	public void testGetSelectedComponents() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getSelectedComponents().size() == 0);
	}

	@Test
	public void testIsSelectedComponent() {
		SelectionModel sm = new SelectionModel();
		IGateWrapper and = new GateWrapper(new AndGate(2));

		assertFalse(sm.isSelectedComponent(and));
		sm.selectComponent(and, false);
		assertTrue(sm.isSelectedComponent(and));
	}

	@Test
	public void testRemoveComponent() {
		SelectionModel sm = new SelectionModel();
		IGateWrapper and = new GateWrapper(new AndGate(2));

		sm.selectComponent(and, false);
		assertTrue(sm.isSelectedComponent(and));
		sm.removeComponent(and);
		assertFalse(sm.isSelectedComponent(and));
	}

	@Test
	public void testRemoveComponents() {
		SelectionModel sm = new SelectionModel();
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper nand = new GateWrapper(new NandGate(2));
		Collection<IGateWrapper> collection = new ArrayList<IGateWrapper>();

		collection.add(and);
		collection.add(nand);

		sm.selectComponents(collection);
		assertTrue(sm.isSelectedComponent(and));
		assertTrue(sm.isSelectedComponent(nand));
		sm.removeComponents(collection);
		assertFalse(sm.isSelectedComponent(and));
		assertFalse(sm.isSelectedComponent(nand));
	}

	@Test
	public void testSelectComponent() {
		SelectionModel sm = new SelectionModel();
		IGateWrapper or = new GateWrapper(new OrGate(2));
		assertTrue(sm.getSelectedComponents().size() == 0);

		sm.selectComponent(new GateWrapper(new AndGate(2)), false);
		assertTrue(sm.getSelectedComponents().size() == 1);
		assertTrue(sm.getSelectedComponents().get(0).getGate() instanceof AndGate);

		sm.selectComponent(or, true);
		assertTrue(sm.getSelectedComponents().size() == 2);
		sm.selectComponent(or, true);
		assertTrue(sm.getSelectedComponents().size() == 1);
	}

	@Test
	public void testSelectComponents() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getSelectedComponents().size() == 0);
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();

		list.add(new GateWrapper(new AndGate(2)));
		list.add(new GateWrapper(new NandGate(2)));

		sm.selectComponents(list);
		assertTrue(sm.getSelectedComponents().size() == 2);
		assertTrue(sm.getSelectedComponents().get(0).getGate() instanceof AndGate);
		assertTrue(sm.getSelectedComponents().get(1).getGate() instanceof NandGate);

	}

	@Test
	public void testSelectionModel() {
		new SelectionModel();
	}

}
