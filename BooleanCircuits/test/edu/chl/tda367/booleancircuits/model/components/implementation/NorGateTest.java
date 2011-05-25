package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NorGateTest {

	@Test
	public void testEmptyGateClone() {
		NorGate nor = new NorGate(2);
		ICircuitGate testGate = nor.emptyGateClone();

		assertTrue(testGate instanceof NorGate);
		assertTrue(testGate.getNoOfInputs() == nor.getNoOfInputs());
	}

	@Test
	public void testNorGate() {
		new NorGate(2);
	}

	@Test
	public void testToString() {
		assertEquals("NOR", new NorGate(2).toString());
	}

	@Test
	public void testUpdateOutput() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(true);
		ConstantGate oneGate2 = new ConstantGate(true);

		nor.update();
		assertTrue(nor.getOutputValue(0));

		nor.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertTrue(nor.getInputs().get(0).getInputValue());
		nor.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertTrue(nor.getInputs().get(1).getInputValue());

		nor.update();
		assertFalse(nor.getOutputValue(0));
	}

}
