package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NotGateTest {

	@Test
	public void testEmptyGateClone() {
		NotGate not = new NotGate();
		ICircuitGate testGate = not.emptyGateClone();

		assertTrue(testGate instanceof NotGate);
		assertTrue(testGate.getNoOfInputs() == not.getNoOfInputs());
	}

	@Test
	public void testNotGate() {
		new NotGate();
	}

	@Test
	public void testToString() {
		NotGate not = new NotGate();
		assertTrue(not.toString().equals("NOT"));
	}

	@Test
	public void testUpdateOutput() {
		NotGate not1 = new NotGate();
		NotGate not2 = new NotGate();
		assertTrue(not1.getOutputValue(0));
		ConstantGate oneGate = new ConstantGate(true);
		ConstantGate zeroGate = new ConstantGate(false);

		// Invert one
		not1.connectInput(0, oneGate, 0);
		oneGate.update();
		assertTrue(not1.getInputs().get(0).getInputValue());

		not1.update();
		assertFalse(not1.getOutputValue(0));

		// Invert zero
		not2.connectInput(0, zeroGate, 0);
		zeroGate.update();
		assertFalse(not2.getInputs().get(0).getInputValue());

		not2.update();
		assertTrue(not2.getOutputValue(0));
	}

}
