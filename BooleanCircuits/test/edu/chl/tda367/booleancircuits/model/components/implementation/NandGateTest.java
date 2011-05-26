package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NandGateTest {

	@Test
	public void testNandGate() {
		new NandGate(2);
	}

	@Test
	public void testEmptyGateClone() {
		NandGate nand = new NandGate(2);
		ICircuitGate testGate = nand.emptyGateClone();

		assertTrue(testGate instanceof NandGate);
		assertTrue(testGate.getNoOfInputs() == nand.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertEquals("NAND", new NandGate(2).toString());
	}

	@Test
	public void testUpdateOutput() {
		NandGate nand = new NandGate(2);
		assertTrue(nand.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);
		ConstantGate oneGate2 = new ConstantGate(true);

		nand.update();
		assertTrue(nand.getOutputValue(0));

		nand.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(nand.getInputs().get(0).getInputValue());
		nand.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertTrue(nand.getInputs().get(1).getInputValue());

		nand.update();
		assertFalse(nand.getOutputValue(0));
	}

}
