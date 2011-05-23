package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;

public class ModelTest {

	@Test
	public void testAddComponent() {
		Model model = new Model();

		Point position = new Point(5, 5);
		model.addComponent(new GateWrapper(new NotGate()), position);
		assertTrue(model.getComponent(position).getGate() instanceof NotGate);
	}

	@Test
	public void testAddComponents() {
		Model model = new Model();
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		assertTrue(model.getComponents().size() == 0);

		list.add(new GateWrapper(new AndGate(2)));
		list.add(new GateWrapper(new NandGate(2)));
		list.add(new GateWrapper(new OrGate(2)));

		model.addComponents(list);
		assertTrue(model.getComponents().size() == 3);
	}

	@Test
	public void testClock() {
		Model model = new Model();
		IGateWrapper clock = new GateWrapper(new Clock());

		model.addComponent(clock, new Point(0, 0));
		clock.update();
		assertFalse(clock.getOutputValue(0));
		model.clock();
		clock.update();
		assertTrue(clock.getOutputValue(0));
	}

	@Test
	public void testGetComponent() {
		Model model = new Model();

		assertNull(model.getComponent(new Point(0, 0)));
	}

	@Test
	public void testGetComponents() {
		Model model = new Model();

		assertTrue(model.getComponents() != null);
		assertTrue(model.getComponents().size() == 0);
	}

	@Test
	public void testGetNumberOfComponents() {
		Model m = new Model();
		assertTrue(m.getNumberOfComponents() == 0);
		m.addComponent(new GateWrapper(new ConstantGate(true)), new Point(0, 0));
		assertTrue(m.getNumberOfComponents() == 1);
	}

	@Test
	public void testModel() {
		new Model();
	}

	@Test
	public void testRemoveComponent() {
		Model model = new Model();
		IGateWrapper and = new GateWrapper(new AndGate(2));
		model.addComponent(and, new Point(0, 0));

		assertTrue(model.getComponent(new Point(0, 0)).getGate() instanceof AndGate);
		assertTrue(model.getComponents().size() == 1);

		model.removeComponent(and);
		assertTrue(model.getComponents().size() == 0);
	}

	@Test
	public void testRemoveComponents() {
		Model model = new Model();
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		assertTrue(model.getComponents().size() == 0);

		list.add(new GateWrapper(new AndGate(2)));
		list.add(new GateWrapper(new NandGate(2)));
		list.add(new GateWrapper(new OrGate(2)));

		model.addComponents(list);
		assertTrue(model.getComponents().size() == 3);

		model.removeComponents(list);
		assertTrue(model.getComponents().size() == 0);
	}

	@Test
	public void testUpdateComponents() {
		Model model = new Model();
		IGateWrapper clock = new GateWrapper(new Clock());

		model.addComponent(clock, new Point(0, 0));
		model.updateComponents();
		assertFalse(clock.getOutputValue(0));
		model.clock();
		model.updateComponents();
		assertTrue(clock.getOutputValue(0));
	}
}
