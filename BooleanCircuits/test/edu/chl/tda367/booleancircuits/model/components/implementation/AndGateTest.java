package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

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
	public void testClone() {
		AndGate and = new AndGate(2);
		ICircuitGate clone = and.clone();
		assertTrue(clone instanceof AndGate);
		assertTrue(clone.getNoOfInputs() == and.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == and.getNoOfOutputs());
	}

	@Test
	public void testGetInputs() {
		AndGate and = new AndGate(2);
		assertTrue(and.getInputs().size() == 2);
	}

	@Test
	public void testConnectInput() {
		AndGate and = new AndGate(2);
		AndGate test = new AndGate(1);

		and.connectInput(0, test, 0);

		assertEquals(test, and.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testConnectsTo() {
		AndGate and = new AndGate(2);
		AndGate testGate = new AndGate(2);
		AndGate notConnected = new AndGate(2);

		assertFalse(and.connectsTo(testGate));
		and.connectInput(0, testGate, 0);
		assertTrue(and.connectsTo(testGate));
		testGate.connectInput(0, and, 0);

		assertFalse(and.connectsTo(notConnected));
	}

	@Test
	public void testEmptyGateClone() {
		AndGate and = new AndGate(2);
		ICircuitGate testGate = and.emptyGateClone();

		assertTrue(testGate instanceof AndGate);
		assertTrue(testGate.getNoOfInputs() == and.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		AndGate and = new AndGate(2);
		AndGate testGate = new AndGate(2);

		assertTrue(and.getComponentTier() == 1);
		and.connectInput(0, testGate, 0);
		assertTrue(and.getComponentTier() == 2);
		testGate.connectInput(0, and, 0);

		testGate.getComponentTier();
	}

	@Test
	public void testGetOutputValue() {
		AndGate and = new AndGate(2);
		assertFalse(and.getOutputValue(0));
	}

	@Test
	public void testGetRecoupledTo() {
		AndGate a = new AndGate(2);
		AndGate b = new AndGate(2);
		AndGate c = new AndGate(2);
		AndGate d = new AndGate(2);

		b.connectInput(1, a, 0);
		b.connectInput(0, c, 0);
		c.connectInput(1, b, 0);
		c.connectInput(0, d, 0);
		d.connectInput(0, c, 0);

		Collection<ICircuitGate> coll = b.getRecoupledTo();
		assertTrue(coll.contains(c));
		assertTrue(coll.contains(d));
	}

	@Test
	public void testSetOutput() {
		AndGate and = new AndGate(2);
		and.setOutput(0, true);
		assertTrue(and.getOutputValue(0));
	}

	@Test
	public void testOverwriteGate() {
		AndGate and = new AndGate(1);
		and.setOutput(0, true);

		AndGate test = new AndGate(2);
		test.setOutput(0, false);

		and.overwriteGate(test);

		assertTrue(and.getInputs().equals(test.getInputs()));
		assertTrue(and.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testToString() {
		assertEquals("AND", new AndGate(2).toString());
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
