package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class XnorGateTest {

	@Test
	public void testAndGate() {
		new XnorGate(2);
	}

	@Test
	public void testClone() {
		XnorGate xnor = new XnorGate(2);
		ICircuitGate clone = xnor.clone();
		assertTrue(clone instanceof XnorGate);
		assertTrue(clone.getNoOfInputs() == xnor.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == xnor.getNoOfOutputs());
	}

	@Test
	public void testConnectInput() {
		XnorGate xnor = new XnorGate(2);
		XnorGate test = new XnorGate(2);

		xnor.connectInput(0, test, 0);

		assertEquals(test, xnor.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testConnectsTo() {
		XnorGate xnor = new XnorGate(2);
		XnorGate testGate = new XnorGate(2);
		xnor.connectInput(0, testGate, 0);
		assertTrue(xnor.connectsTo(testGate));
	}

	public void testEmptyGateClone() {
		XnorGate xnor = new XnorGate(2);
		ICircuitGate testGate = xnor.emptyGateClone();

		assertTrue(testGate instanceof XnorGate);
		assertTrue(testGate.getNoOfInputs() == xnor.getNoOfInputs());
	}

	@Test
	public void testGetComponentTier() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getComponentTier() == 1);
		xnor.connectInput(0, new AndGate(2), 0);
		assertTrue(xnor.getComponentTier() == 2);
	}

	@Test
	public void testGetInputs() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getInputs().size() == 2);
	}

	@Test
	public void testGetNoOfInputs() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputValue() {
		XnorGate xnor = new XnorGate(2);
		assertTrue(xnor.getOutputValue(0));
	}

	@Test
	public void testGetRecoupledTo() {
		XnorGate xnor = new XnorGate(2);
		XnorGate testGate = new XnorGate(2);
		assertTrue(xnor.getRecoupledTo().size() == 0);

		xnor.connectInput(0, testGate, 0);
		testGate.connectInput(1, xnor, 1);
		assertTrue(xnor.getRecoupledTo().size() == 1);
	}

	@Test
	public void testOverwriteGate() {
		XnorGate xnor = new XnorGate(2);
		xnor.setOutput(0, true);

		XnorGate test = new XnorGate(2);
		test.setOutput(0, false);

		xnor.overwriteGate(test);

		assertTrue(xnor.getInputs().equals(test.getInputs())
				&& xnor.getOutputValue(0) == test.getOutputValue(0));

	}

	@Test
	public void testSetOutput() {
		XnorGate xnor = new XnorGate(2);
		xnor.setOutput(0, true);
		assertTrue(xnor.getOutputValue(0));
	}

	@Test
	public void testToString() {
		assertEquals("XNOR", new XnorGate(2).toString());
	}

	@Test
	public void testUpdate() {
		new XnorGate(2).update();
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
