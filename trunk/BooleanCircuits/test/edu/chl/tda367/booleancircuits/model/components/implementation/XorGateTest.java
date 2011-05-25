package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class XorGateTest {

	@Test
	public void testClone() {
		XorGate xor = new XorGate(2);
		ICircuitGate clone = xor.clone();
		assertTrue(clone instanceof XorGate);
		assertTrue(clone.getNoOfInputs() == xor.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == xor.getNoOfOutputs());
	}

	@Test
	public void testConnectInput() {
		XorGate xor = new XorGate(2);
		XorGate test = new XorGate(1);

		xor.connectInput(0, test, 0);

		assertEquals(test, xor.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testConnectsTo() {
		XorGate xor = new XorGate(2);
		XorGate testGate = new XorGate(2);
		xor.connectInput(0, testGate, 0);
		assertTrue(xor.connectsTo(testGate));
	}

	@Test
	public void testEmptyGateClone() {
		XorGate xor = new XorGate(2);
		ICircuitGate testGate = xor.emptyGateClone();

		assertTrue(testGate instanceof XorGate);
		assertTrue(testGate.getNoOfInputs() == xor.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		XorGate xor = new XorGate(2);
		assertTrue(xor.getComponentTier() == 1);
		xor.connectInput(0, new XorGate(2), 0);
		assertTrue(xor.getComponentTier() == 2);
	}

	@Test
	public void testGetInputs() {
		XorGate xor = new XorGate(2);
		assertTrue(xor.getInputs().size() == 2);
	}

	@Test
	public void testGetNoOfInputs() {
		XorGate xor = new XorGate(2);
		assertTrue(xor.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		XorGate xor = new XorGate(2);
		assertTrue(xor.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		XorGate xor = new XorGate(2);
		assertFalse(xor.getOutputValue(0));
	}

	@Test
	public void testGetRecoupledTo() {
		XorGate xor = new XorGate(2);
		XorGate testGate = new XorGate(2);

		assertTrue(xor.getRecoupledTo().size() == 0);

		xor.connectInput(0, testGate, 0);
		testGate.connectInput(1, xor, 1);
		assertTrue(xor.getRecoupledTo().size() == 1);
	}

	@Test
	public void testOverwriteGate() {
		XorGate xor = new XorGate(2);
		xor.setOutput(0, true);

		XorGate test = new XorGate(2);
		test.setOutput(0, false);

		xor.overwriteGate(test);

		assertTrue(xor.getInputs().equals(test.getInputs()));
		assertTrue(xor.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testSetOutput() {
		XorGate xor = new XorGate(2);
		xor.setOutput(0, true);
		assertTrue(xor.getOutputValue(0));
	}

	@Test
	public void testToString() {
		assertEquals("XOR", new XorGate(2).toString());
	}

	@Test
	public void testUpdate() {
		new XorGate(2).update();
	}

	@Test
	public void testUpdateOutput() {
		XorGate xor = new XorGate(2);
		assertFalse(xor.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);
		ConstantGate oneGate2 = new ConstantGate(true);

		xor.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(xor.getInputs().get(0).getInputValue());

		xor.update();
		assertTrue(xor.getOutputValue(0));

		xor.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertTrue(xor.getInputs().get(1).getInputValue());

		xor.update();
		assertFalse(xor.getOutputValue(0));
	}

	@Test
	public void testXorGate() {
		new XorGate(2);
	}

}
