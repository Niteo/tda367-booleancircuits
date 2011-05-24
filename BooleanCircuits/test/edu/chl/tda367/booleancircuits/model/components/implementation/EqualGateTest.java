package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class EqualGateTest {

	@Test
	public void testEqualGate() {
		new EqualGate();
	}

	@Test
	public void testClone() {
		EqualGate gate = new EqualGate();
		ICircuitGate clone = gate.clone();
		assertTrue(clone instanceof EqualGate);
		assertTrue(clone.getNoOfInputs() == gate.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == gate.getNoOfOutputs());
	}


	@Test
	public void testConnectInput() {
		EqualGate gate = new EqualGate();
		EqualGate test = new EqualGate();

		gate.connectInput(0, test, 0);

		assertEquals(test, gate.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testEmptyGateClone() {
		EqualGate gate = new EqualGate();
		ICircuitGate emptyGate = gate.emptyGateClone();

		assertTrue(emptyGate instanceof EqualGate);
		assertTrue(emptyGate.getNoOfInputs() == gate
				.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		EqualGate gate = new EqualGate();
		assertTrue(gate.getComponentTier() == 1);
	}

	@Test
	public void testGetInputs() {
		EqualGate gate = new EqualGate();
		assertTrue(gate.getInputs().size() == 1);
	}

	@Test
	public void testGetNoOfInputs() {
		EqualGate gate = new EqualGate();
		assertTrue(gate.getNoOfInputs() == 1);

	}

	@Test
	public void testGetNoOfOutputs() {
		EqualGate gate = new EqualGate();
		assertTrue(gate.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		EqualGate gate = new EqualGate();

		gate.setOutput(0, false);

		assertTrue(gate.getOutputValue(0) == false);
	}

	@Test
	public void testOverwriteGate() {
		EqualGate gate = new EqualGate();
		gate.setOutput(0, false);
		EqualGate test = new EqualGate();
		test.setOutput(0, true);
		gate.overwriteGate(test);

		assertTrue(gate.getInputs().equals(test.getInputs()));
		assertTrue(test.getOutputValue(0) == test
				.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		EqualGate gate = new EqualGate();

		gate.setOutput(0, true);

		assertTrue(gate.getOutputValue(0) == true);
	}

	@Test
	public void testToString() {
		assertTrue(new EqualGate().toString().equals("EQUAL"));
	}

	@Test
	public void testUpdate() {
		new EqualGate().update();
	}

	@Test
	public void testUpdateOutput() {
		EqualGate gate = new EqualGate();
		ConstantGate falseConstantGate = new ConstantGate(true);

		gate.connectInput(0, falseConstantGate, 0);
		falseConstantGate.update();
		gate.update();
		assertTrue(gate.getOutputValue(0));

	}

}
