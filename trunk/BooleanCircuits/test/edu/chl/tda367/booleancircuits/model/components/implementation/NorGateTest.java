package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NorGateTest {

	@Test
	public void testClone() {
		NorGate nor = new NorGate(2);
		ICircuitGate clone = nor.clone();
		assertTrue(clone instanceof NorGate);
		assertTrue(clone.getNoOfInputs() == nor.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == nor.getNoOfOutputs());
	}

	@Test
	public void testConnectInput() {
		NorGate nor = new NorGate(2);
		NorGate test = new NorGate(1);

		nor.connectInput(0, test, 0);

		assertEquals(test, nor.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testConnectsTo() {
		NorGate nor = new NorGate(2);
		NorGate testGate = new NorGate(2);
		nor.connectInput(0, testGate, 0);
		assertTrue(nor.connectsTo(testGate));
	}

	public void testEmptyGateClone() {
		NorGate nor = new NorGate(2);
		ICircuitGate testGate = nor.emptyGateClone();

		assertTrue(testGate instanceof NorGate);
		assertTrue(testGate.getNoOfInputs() == nor.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getComponentTier() == 1);
		nor.connectInput(0, new NorGate(2), 0);
		assertTrue(nor.getComponentTier() == 2);
	}

	@Test
	public void testGetInputs() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getInputs().size() == 2);
	}

	@Test
	public void testGetNoOfInputs() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		NorGate nor = new NorGate(2);
		assertTrue(nor.getOutputValue(0));
	}

	@Test
	public void testGetRecoupledTo() {
		NorGate nor = new NorGate(2);
		NorGate testGate = new NorGate(2);
		assertTrue(nor.getRecoupledTo().size() == 0);

		nor.connectInput(0, testGate, 0);
		testGate.connectInput(1, nor, 1);
		assertTrue(nor.getRecoupledTo().size() == 1);
	}

	@Test
	public void testNorGate() {
		new NorGate(2);
	}

	@Test
	public void testOverwriteGate() {
		NorGate nor = new NorGate(2);
		nor.setOutput(0, true);

		NorGate test = new NorGate(2);
		test.setOutput(0, false);

		nor.overwriteGate(test);

		assertTrue(nor.getInputs().equals(test.getInputs())
				&& nor.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testSetOutput() {
		NorGate nor = new NorGate(2);
		nor.setOutput(0, true);
		assertTrue(nor.getOutputValue(0));
	}

	@Test
	public void testToString() {
		assertEquals("NOR", new NorGate(2).toString());
	}

	@Test
	public void testUpdate() {
		new NorGate(2).update();
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
