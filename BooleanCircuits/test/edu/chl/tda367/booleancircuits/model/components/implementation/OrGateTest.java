package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class OrGateTest {

	@Test
	public void testOrGate() {
		new OrGate(2);
	}

	@Test
	public void testGetNoOfInputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getNoOfOutputs() == 1);
	}

	@Test
	public void testToString() {
		assertEquals("OR", new OrGate(2).toString());
	}

	@Test
	public void testGetInputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getInputs().size()==2);
	}

	@Test
	public void testGetOutputValue() {
		OrGate or = new OrGate(2);
		assertFalse(or.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		OrGate or = new OrGate(2);
		or.setOutput(0, true);
		assertEquals(true, or.getOutputValue(0));
	}

	@Test
	public void testConnectInput() {
		OrGate or = new OrGate(2);
		OrGate test = new OrGate(2);

		or.connectInput(0, test, 0);

		assertEquals(test, or.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testGetRecoupledTo() {
		OrGate or = new OrGate(2);
		OrGate testGate = new OrGate(2);
		assertTrue(or.getRecoupledTo().size() == 0);

		or.connectInput(0, testGate, 0);
		testGate.connectInput(1, or, 1);
		assertTrue(or.getRecoupledTo().size() == 1);
	}

	public void testEmptyGateClone() {
		OrGate or = new OrGate(2);
		ICircuitGate testGate = or.emptyGateClone();

		assertTrue(testGate instanceof OrGate);
		assertTrue(testGate.getNoOfInputs() == or.getNoOfInputs());
	}

	@Test
	public void testOverwriteGate() {
		OrGate or = new OrGate(2);
		or.setOutput(0, true);

		OrGate test = new OrGate(2);
		test.setOutput(0, false);

		or.overwriteGate(test);

		assertTrue(or.getInputs().equals(test.getInputs())
				&& or.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testConnectsTo() {
		OrGate or = new OrGate(2);
		OrGate testGate = new OrGate(2);
		or.connectInput(0, testGate, 0);
		assertTrue(or.connectsTo(testGate));
	}

	@Test
	public void testGetComponentTier() {
		OrGate or = new OrGate(2);
		assertTrue(or.getComponentTier() == 1);
		or.connectInput(0, new XorGate(2), 0);
		assertTrue(or.getComponentTier() == 2);
	}

	@Test
	public void testGetPosition() {
		OrGate or = new OrGate(2);
		assertTrue(or.getPosition().x == 0 && or.getPosition().y == 0);
	}

	@Test
	public void testSetPosition() {
		OrGate or = new OrGate(2);
		assertTrue(or.getPosition().x == 0 && or.getPosition().y == 0);
		Point p = new Point(8, 8);
		or.setPosition(p);
		assertTrue(or.getPosition() == p);
	}

	@Test
	public void testMove() {
		OrGate or = new OrGate(2);
		assertTrue(or.getPosition().x == 0 && or.getPosition().y == 0);
		or.move(7, 8);
		assertTrue(or.getPosition().x == 7 && or.getPosition().y == 8);
	}

	@Test
	public void testClone() {
		OrGate or = new OrGate(2);
		ICircuitGate clone = or.clone();
		assertTrue(clone instanceof OrGate);
		assertTrue(clone.getPosition().x == or.getPosition().x
				&& clone.getPosition().y == or.getPosition().y);
		assertTrue(clone.getNoOfInputs() == or.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == or.getNoOfOutputs());
	}

	@Test
	public void testUpdate() {
		new OrGate(2).update();
	}

	@Test
	public void testUpdateOutput() {
		OrGate or = new OrGate(2);
		or.update();
		assertFalse(or.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);

		or.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(or.getInputs().get(0).getInputValue());

		or.update();
		assertTrue(or.getOutputValue(0));
	}
}
