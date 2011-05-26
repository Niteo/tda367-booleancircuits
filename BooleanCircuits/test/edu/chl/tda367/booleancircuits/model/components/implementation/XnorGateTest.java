package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class XnorGateTest {

	@Test
	public void testXnorGate() {
		new XnorGate(2);
	}

	@Test
	public void testEmptyGateClone() {
		XnorGate xnor = new XnorGate(2);
		ICircuitGate testGate = xnor.emptyGateClone();

		assertTrue(testGate instanceof XnorGate);
		assertTrue(testGate.getNoOfInputs() == xnor.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertEquals("XNOR", new XnorGate(2).toString());
	}

	@Test
	public void testUpdateOutput() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);
		ConstantGate oneGate2 = new ConstantGate(true);

		xnor.update();
		assertTrue(xnor.getOutputValue(0));

		xnor.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(xnor.getInputs().get(0).getInputValue());

		xnor.update();
		assertFalse(xnor.getOutputValue(0));

		xnor.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertTrue(xnor.getInputs().get(1).getInputValue());

		xnor.update();
		assertTrue(xnor.getOutputValue(0));
	}

}
