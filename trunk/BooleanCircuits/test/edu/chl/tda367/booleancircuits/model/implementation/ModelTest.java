package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;

public class ModelTest {

	@Test
	public void testModel() {

		Model model = new Model("TestModel");
		assertTrue(model!=null);
	}

	@Test
	public void testAddComponent() {
		Model model = new Model("TestModel");
		model.addComponent(new NotGate(), new Point(5,5));
	}

	@Test
	public void testGetComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectAllComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveSelectedComponents() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsSelectedComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

}
