package edu.chl.tda367.booleancircuits.model.implementation;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.implementation.Model;

public class ModelTest {

	@Test
	public void testModel() {

		new Model();

	}

	@Test
	public void testAddComponent() {

		Model model = new Model();

		Point position = new Point(5, 5);
		model.addComponent(new NotGate(), position);
		assertTrue(model.getComponent(position) instanceof NotGate);

	}

	@Test
	public void testGetComponents() {
		AndGate and = new AndGate(2);
		NotGate not = new NotGate();
		NandGate nand = new NandGate(2);

		Model model = new Model();

		assertTrue(model.getComponents() != null);
		assertTrue(model.getComponents().size() == 0);

		model.addComponent(and, new Point(10, 10));
		assertTrue(model.getComponents().size() == 1);
		assertTrue(model.getComponents().contains(and));

		model.addComponent(not, new Point(14, 14));
		model.addComponent(nand, new Point(20, 20));

		assertTrue(model.getComponents().size() == 3);

		assertTrue(model.getComponents().contains(not));
		assertTrue(model.getComponents().contains(nand));
	}

	@Test
	public void testGetComponent() {
		AndGate and = new AndGate(2);
		NotGate not = new NotGate();
		NandGate nand = new NandGate(2);
		Model model = new Model();
		model.addComponent(and, new Point(10, 10));
		model.addComponent(not, new Point(80, 80));
		model.addComponent(nand, new Point(200, 200));

		assertTrue(model.getComponent(new Point(10, 10)) instanceof AndGate);
		assertTrue(model.getComponent(new Point(80, 80)) instanceof NotGate);
		assertTrue(model.getComponent(new Point(200, 200)) instanceof NandGate);

	}

	@Test
	public void testRemoveComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testClock() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddComponents() {
		fail("Not yet implemented");
	}

}
