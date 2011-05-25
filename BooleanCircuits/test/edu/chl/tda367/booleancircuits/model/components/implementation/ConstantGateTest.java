package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class ConstantGateTest {
	@Test
	public void testClone() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		ICircuitGate falseClone = falseConstantGate.clone();
		assertTrue(falseClone instanceof ConstantGate);
		assertTrue(falseClone.getNoOfInputs() == falseConstantGate
				.getNoOfInputs());
		assertTrue(falseClone.getNoOfOutputs() == falseConstantGate
				.getNoOfOutputs());

		ICircuitGate trueClone = trueConstantGate.clone();
		assertTrue(trueClone instanceof ConstantGate);
		assertTrue(trueClone.getNoOfInputs() == trueConstantGate
				.getNoOfInputs());
		assertTrue(trueClone.getNoOfOutputs() == trueConstantGate
				.getNoOfOutputs());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConnectInput() throws IllegalArgumentException {
		ConstantGate falseConstantGate = new ConstantGate(false);

		falseConstantGate.connectInput(0, new ConstantGate(true), 1);

	}

	@Test
	public void testConnectsTo() {
		ConstantGate falseGate = new ConstantGate(false);
		ConstantGate trueGate = new ConstantGate(true);

		assertFalse(falseGate.connectsTo(trueGate));
	}

	@Test
	public void testConstantGate() {
		new ConstantGate(false);
		new ConstantGate(true);

	}

	@Test
	public void testEmptyGateClone() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ICircuitGate emptyGate = falseConstantGate.emptyGateClone();

		assertTrue(emptyGate instanceof ConstantGate);
		assertTrue(emptyGate.getNoOfInputs() == falseConstantGate
				.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		falseConstantGate.setOutput(0, trueConstantGate.getOutputValue(0));

		assertTrue(falseConstantGate.getComponentTier() == 1);
	}

	@Test
	public void testGetInputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		assertTrue(falseConstantGate.getInputs().size() == 0);

		ConstantGate trueConstantGate = new ConstantGate(true);
		assertTrue(trueConstantGate.getInputs().size() == 0);

	}

	@Test
	public void testGetNoOfInputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		assertTrue(falseConstantGate.getNoOfInputs() == 0);

	}

	@Test
	public void testGetNoOfOutputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		assertTrue(falseConstantGate.getNoOfOutputs() == 1);

	}

	@Test
	public void testGetOutputValue() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		falseConstantGate.setOutput(0, false);
		trueConstantGate.setOutput(0, true);

		assertTrue(falseConstantGate.getOutputValue(0) == false);
		assertTrue(trueConstantGate.getOutputValue(0) == true);
	}

	@Test
	public void testOverwriteGate() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate test = new ConstantGate(true);
		falseConstantGate.overwriteGate(test);

		assertTrue(falseConstantGate.getInputs().equals(test.getInputs()));
		assertTrue(falseConstantGate.getOutputValue(0) == test
				.getOutputValue(0));

	}

	@Test
	public void testSetOutput() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		falseConstantGate.setOutput(0, false);
		trueConstantGate.setOutput(0, true);

		assertTrue(falseConstantGate.getNoOfOutputs() == 1);
		assertTrue(falseConstantGate.getOutputValue(0) == false);

		assertTrue(trueConstantGate.getNoOfOutputs() == 1);
		assertTrue(trueConstantGate.getOutputValue(0) == true);
	}

	@Test
	public void testToString() {
		assertTrue(new ConstantGate(true).toString().equals("1"));
		assertTrue(new ConstantGate(false).toString().equals("0"));
	}

	@Test
	public void testUpdate() {
		new ConstantGate(true).update();
	}

	@Test
	public void testUpdateOutput() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		falseConstantGate.update();
		assertFalse(falseConstantGate.getOutputValue(0));
		assertFalse(trueConstantGate.getOutputValue(0));
		trueConstantGate.update();
		assertTrue(trueConstantGate.getOutputValue(0));

	}
}
