package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.implementation.*;

public class ModelTest {

	@Test
	public void testModel() {

		Model model = new Model();
		assertTrue(model != null);
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

		model.addComponent(and, new Point(10, 10));
		model.addComponent(not, new Point(14, 14));
		model.addComponent(nand, new Point(20, 20));

		if (model.getComponents() != null) {
			model.getComponents().contains(new AndGate(2));
			model.getComponents().contains(new NotGate());
			model.getComponents().contains(new NandGate(2));
		} else {
			fail("get no components");
		}

	}

	@Test
	public void testGetComponent() {
		AndGate and = new AndGate(2);
		NotGate not = new NotGate();
		NandGate nand = new NandGate(2);
		Model model = new Model();
		model.addComponent(and, new Point(10, 10));
		model.addComponent(not, new Point(14, 14));
		model.addComponent(nand, new Point(20, 20));

		assertTrue(model.getComponent(new Point(10, 10)).equals(and)
				&& model.getComponent(new Point(10, 10)).equals(not)
				&& model.getComponent(new Point(10, 10)).equals(nand));
	
	}



}
