package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class AndGateTest {

	@Test
	public void testAndGate() {
		new AndGate(2);
	}

	@Test
	public void testGetNoOfInputs() {
		AndGate and = new AndGate(2);
		assertTrue(and.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		AndGate and = new AndGate(2);
		assertTrue(and.getNoOfOutputs() == 1);
	}

	@Test
	public void testToString() {
		assertEquals("AND", new AndGate(2).toString());
	}

	@Test
	public void testGetInputs() {
		AndGate and = new AndGate(2);
		assertTrue(and.getInputs().size()==2);
	}

	@Test
	public void testGetOutputValue() {
		AndGate and = new AndGate(2);
		assertFalse(and.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		AndGate and = new AndGate(2);
		and.setOutput(0, true);
		assertEquals(true, and.getOutputValue(0));
	}

	@Test
	public void testConnectInput() {
		AndGate and = new AndGate(2);
		AndGate test = new AndGate(1);

		and.connectInput(0, test, 0);

		assertEquals(test, and.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testGetRecoupledTo() {
		AndGate and = new AndGate(2);
		AndGate testGate = new AndGate(2);
		assertTrue(and.getRecoupledTo().size() == 0);

		and.connectInput(0, testGate, 0);
		testGate.connectInput(1, and, 1);
		assertTrue(and.getRecoupledTo().size() == 1);
	}

	public void testEmptyGateClone() {
		AndGate and = new AndGate(2);
		ICircuitGate testGate = and.emptyGateClone();

		assertTrue(testGate instanceof AndGate);
		assertTrue(testGate.getNoOfInputs() == and.getNoOfInputs());
	}

	@Test
	public void testOverwriteGate() {
		AndGate and = new AndGate(1);
		and.setOutput(0, true);

		AndGate test = new AndGate(2);
		test.setOutput(0, false);

		and.overwriteGate(test);

		assertTrue(and.getInputs().equals(test.getInputs())
				&& and.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testConnectsTo() {
		AndGate and = new AndGate(2);
		AndGate testGate = new AndGate(2);
		and.connectInput(0, testGate, 0);
		assertTrue(and.connectsTo(testGate));
	}

	@Test
	public void testGetComponentTier() {
		AndGate and = new AndGate(2);
		assertTrue(and.getComponentTier() == 1);
		and.connectInput(0, new AndGate(2), 0);
		assertTrue(and.getComponentTier() == 2);
	}

	@Test
	public void testGetPosition() {
		AndGate and = new AndGate(2);
		assertTrue(and.getPosition().x == 0 && and.getPosition().y == 0);
	}

	@Test
	public void testSetPosition() {
		AndGate and = new AndGate(2);
		assertTrue(and.getPosition().x == 0 && and.getPosition().y == 0);
		Point p = new Point(8, 8);
		and.setPosition(p);
		assertTrue(and.getPosition() == p);
	}

	@Test
	public void testMove() {
		AndGate and = new AndGate(2);
		assertTrue(and.getPosition().x == 0 && and.getPosition().y == 0);
		and.move(7, 8);
		assertTrue(and.getPosition().x == 7 && and.getPosition().y == 8);
	}

	@Test
	public void testClone() {
		AndGate and = new AndGate(2);
		ICircuitGate clone = and.clone();
		assertTrue(clone instanceof AndGate);
		assertTrue(clone.getPosition().x == and.getPosition().x
				&& clone.getPosition().y == and.getPosition().y);
		assertTrue(clone.getNoOfInputs() == and.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == and.getNoOfOutputs());
	}

	@Test
	public void testUpdate() {
		new AndGate(2).update();
	}

	@Test
	public void testUpdateOutput() {
		AndGate and = new AndGate(2);
		assertFalse(and.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);
		ConstantGate oneGate2 = new ConstantGate(true);

		and.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(and.getInputs().get(0).getInputValue());
		
		and.update();
		assertFalse(and.getOutputValue(0));
		
		and.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertTrue(and.getInputs().get(1).getInputValue());

		and.update();
		assertTrue(and.getOutputValue(0));
	}

}
