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
		
		Point position = new Point(5,5);
		model.addComponent(new NotGate(), position );
		assertTrue(model.getComponent(position) instanceof NotGate );
		

	}

	@Test
	public void testGetComponents() {
		AndGate and = new AndGate(2);
		NotGate not = new NotGate();
		NandGate nand = new NandGate(2);
		
		Model model = new Model();
		
		model.addComponent(and, new Point(10,10));
		model.addComponent(not, new Point(14,14));
		model.addComponent(nand, new Point(20,20));
		
		assertTrue(model.getComponents().size()==3);
		//TODO: is this sufficient?
		
		
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
