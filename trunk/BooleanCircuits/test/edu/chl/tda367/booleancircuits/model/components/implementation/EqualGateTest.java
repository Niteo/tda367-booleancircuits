package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class EqualGateTest {

	@Test
	public void testEqualGate() {
		new Equal();
	}

	@Test
	public void testClone() {
		Equal gate = new Equal();
		ICircuitGate clone = gate.clone();
		assertTrue(clone instanceof Equal);
		assertTrue(clone.getNoOfInputs() == gate.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == gate.getNoOfOutputs());
	}


	@Test
	public void testConnectInput() {
		Equal gate = new Equal();
		Equal test = new Equal();

		gate.connectInput(0, test, 0);

		assertEquals(test, gate.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testEmptyGateClone() {
		Equal gate = new Equal();
		ICircuitGate emptyGate = gate.emptyGateClone();

		assertTrue(emptyGate instanceof Equal);
		assertTrue(emptyGate.getNoOfInputs() == gate
				.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		Equal gate = new Equal();
		assertTrue(gate.getComponentTier() == 1);
	}

	@Test
	public void testGetInputs() {
		Equal gate = new Equal();
		assertTrue(gate.getInputs().size() == 1);
	}

	@Test
	public void testGetNoOfInputs() {
		Equal gate = new Equal();
		assertTrue(gate.getNoOfInputs() == 1);

	}

	@Test
	public void testGetNoOfOutputs() {
		Equal gate = new Equal();
		assertTrue(gate.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		Equal gate = new Equal();

		gate.setOutput(0, false);

		assertTrue(gate.getOutputValue(0) == false);
	}

	@Test
	public void testOverwriteGate() {
		Equal gate = new Equal();
		gate.setOutput(0, false);
		Equal test = new Equal();
		test.setOutput(0, true);
		gate.overwriteGate(test);

		assertTrue(gate.getInputs().equals(test.getInputs()));
		assertTrue(test.getOutputValue(0) == test
				.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		Equal gate = new Equal();

		gate.setOutput(0, true);

		assertTrue(gate.getOutputValue(0) == true);
	}

	@Test
	public void testToString() {
		assertTrue(new Equal().toString().equals("EQUAL"));
	}

	@Test
	public void testUpdate() {
		new Equal().update();
	}

	@Test
	public void testUpdateOutput() {
		Equal gate = new Equal();
		ConstantGate falseConstantGate = new ConstantGate(true);

		gate.connectInput(0, falseConstantGate, 0);
		falseConstantGate.update();
		gate.update();
		assertTrue(gate.getOutputValue(0));

	}

}
