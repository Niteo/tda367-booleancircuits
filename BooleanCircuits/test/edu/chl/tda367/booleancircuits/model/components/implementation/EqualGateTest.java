package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class EqualGateTest {

	@Test
	public void testEqualGate() {
		new Equal();
	}

	@Test
	public void testEmptyGateClone() {
		Equal gate = new Equal();
		ICircuitGate emptyGate = gate.emptyGateClone();

		assertTrue(emptyGate instanceof Equal);
		assertTrue(emptyGate.getNoOfInputs() == gate.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertTrue(new Equal().toString().equals("EQUAL"));
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
