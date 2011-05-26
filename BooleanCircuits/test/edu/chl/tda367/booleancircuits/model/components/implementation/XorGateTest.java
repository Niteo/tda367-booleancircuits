package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class XorGateTest {

	@Test
	public void testXorGate() {
		new XorGate(2);
	}

	@Test
	public void testEmptyGateClone() {
		XorGate xor = new XorGate(2);
		ICircuitGate testGate = xor.emptyGateClone();

		assertTrue(testGate instanceof XorGate);
		assertTrue(testGate.getNoOfInputs() == xor.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertEquals("XOR", new XorGate(2).toString());
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

}
