package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;

public class SelectionModelTest {

	@Test
	public void testSelectionModel() {
		new SelectionModel();
	}

	@Test
	public void testGetSelectedComponents() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getSelectedComponents().size() == 0);
	}

	@Test
	public void testSelectComponent() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getSelectedComponents().size() == 0);

		sm.selectComponent(new AndGate(2), false);
		assertTrue(sm.getSelectedComponents().size() == 1);
		assertTrue(sm.getSelectedComponents().get(0) instanceof AndGate);
	}

	@Test
	public void testSelectComponents() {
		SelectionModel sm = new SelectionModel();
		assertTrue(sm.getSelectedComponents().size() == 0);
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();

		list.add(new AndGate(2));
		list.add(new NandGate(2));

		sm.selectComponents(list);
		assertTrue(sm.getSelectedComponents().size() == 2);
		assertTrue(sm.getSelectedComponents().get(0) instanceof AndGate);
		assertTrue(sm.getSelectedComponents().get(1) instanceof NandGate);

	}

	@Test
	public void testIsSelectedComponent() {
		SelectionModel sm = new SelectionModel();
		AndGate and = new AndGate(2);

		assertFalse(sm.isSelectedComponent(and));
		sm.selectComponent(and, false);
		assertTrue(sm.isSelectedComponent(and));
	}

	@Test
	public void testRemoveUnusedElements() {
		fail("Implement me!");
	}
}
