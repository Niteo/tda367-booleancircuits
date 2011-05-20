package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.*;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;

public class ModelTest {

	@Test
	public void testAddComponent() {
		Model model = new Model();

		Point position = new Point(5, 5);
		model.addComponent(new NotGate(), position);
		assertTrue(model.getComponent(position) instanceof NotGate);
	}

	@Test
	public void testAddComponents() {
		Model model = new Model();
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();
		assertTrue(model.getComponents().size() == 0);

		list.add(new AndGate(2));
		list.add(new NandGate(2));
		list.add(new OrGate(2));

		model.addComponents(list);
		assertTrue(model.getComponents().size() == 3);
	}

	@Test
	public void testClock() {
		Model model = new Model();
		Clock clock = new Clock();

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
	public void testGetNumberOfComponents(){
		Model m = new Model();
		assertTrue(m.getNumberOfComponents() == 0);
		m.addComponent(new ConstantGate(true), new Point(0,0));
		assertTrue(m.getNumberOfComponents() == 1);
	}

	@Test
	public void testModel() {
		new Model();
	}

	@Test
	public void testRemoveComponent() {
		Model model = new Model();
		AndGate and = new AndGate(2);
		model.addComponent(and, new Point(0, 0));

		assertTrue(model.getComponent(new Point(0, 0)) instanceof AndGate);
		assertTrue(model.getComponents().size() == 1);

		model.removeComponent(and);
		assertTrue(model.getComponents().size() == 0);
	}

	@Test
	public void testRemoveComponents() {
		Model model = new Model();
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();
		assertTrue(model.getComponents().size() == 0);

		list.add(new AndGate(2));
		list.add(new NandGate(2));
		list.add(new OrGate(2));

		model.addComponents(list);
		assertTrue(model.getComponents().size() == 3);

		model.removeComponents(list);
		assertTrue(model.getComponents().size() == 0);
	}

	@Test
	public void testUpdateComponents() {
		Model model = new Model();
		Clock clock = new Clock();

		model.addComponent(clock, new Point(0, 0));
		model.updateComponents();
		assertFalse(clock.getOutputValue(0));
		model.clock();
		model.updateComponents();
		assertTrue(clock.getOutputValue(0));
	}
}
