package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class OrGateTest {

	@Test
	public void testClone() {
		OrGate or = new OrGate(2);
		ICircuitGate clone = or.clone();
		assertTrue(clone instanceof OrGate);
		assertTrue(clone.getNoOfInputs() == or.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == or.getNoOfOutputs());
	}

	@Test
	public void testConnectInput() {
		OrGate or = new OrGate(2);
		OrGate test = new OrGate(2);

		or.connectInput(0, test, 0);

		assertEquals(test, or.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testConnectsTo() {
		OrGate or = new OrGate(2);
		OrGate testGate = new OrGate(2);
		or.connectInput(0, testGate, 0);
		assertTrue(or.connectsTo(testGate));
	}

	@Test
	public void testEmptyGateClone() {
		OrGate or = new OrGate(2);
		ICircuitGate testGate = or.emptyGateClone();

		assertTrue(testGate instanceof OrGate);
		assertTrue(testGate.getNoOfInputs() == or.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		OrGate or = new OrGate(2);
		assertTrue(or.getComponentTier() == 1);
		or.connectInput(0, new XorGate(2), 0);
		assertTrue(or.getComponentTier() == 2);
	}

	@Test
	public void testGetInputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getInputs().size() == 2);
	}

	@Test
	public void testGetNoOfInputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		OrGate or = new OrGate(2);
		assertTrue(or.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		OrGate or = new OrGate(2);
		assertFalse(or.getOutputValue(0));
	}

	@Test
	public void testGetRecoupledTo() {
		OrGate or = new OrGate(2);
		OrGate testGate = new OrGate(2);
		assertTrue(or.getRecoupledTo().size() == 0);

		or.connectInput(0, testGate, 0);
		testGate.connectInput(1, or, 1);
		assertTrue(or.getRecoupledTo().size() == 1);
	}

	@Test
	public void testOrGate() {
		new OrGate(2);
	}

	@Test
	public void testOverwriteGate() {
		OrGate or = new OrGate(2);
		or.setOutput(0, true);

		OrGate test = new OrGate(2);
		test.setOutput(0, false);

		or.overwriteGate(test);

		assertTrue(or.getInputs().equals(test.getInputs()));
		assertTrue(or.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testSetOutput() {
		OrGate or = new OrGate(2);
		or.setOutput(0, true);
		assertTrue(or.getOutputValue(0));
	}

	@Test
	public void testToString() {
		assertEquals("OR", new OrGate(2).toString());
	}

	@Test
	public void testUpdate() {
		new OrGate(2).update();
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
