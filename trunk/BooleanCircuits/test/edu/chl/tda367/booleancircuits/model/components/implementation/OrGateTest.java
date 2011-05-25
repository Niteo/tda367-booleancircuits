package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class OrGateTest {

	@Test
	public void testOrGate() {
		new OrGate(2);
	}

	@Test
	public void testEmptyGateClone() {
		OrGate or = new OrGate(2);
		ICircuitGate testGate = or.emptyGateClone();

		assertTrue(testGate instanceof OrGate);
		assertTrue(testGate.getNoOfInputs() == or.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertEquals("OR", new OrGate(2).toString());
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
