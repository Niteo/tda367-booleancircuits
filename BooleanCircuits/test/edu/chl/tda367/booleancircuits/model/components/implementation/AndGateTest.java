package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AndGateTest {

	@Test
	public void testAndGate() {
		AndGate and = new AndGate(2);
		assertTrue(and != null && and.getNoOfInputs() == 2);
	}

	@Test
	public void testToString() {
		AndGate and = new AndGate(2);
		assertEquals("AND", and.toString());
	}

	@Test
	public void testGetInputs() {
		AndGate and = new AndGate(2);
		assertTrue(and.getInputs() instanceof List);
	}

	@Test
	public void testSetOutput() {
		AndGate and = new AndGate(2);
		and.setOutput(0, true);
		assertEquals(true, and.getOutputValue(0));
	}

	@Test
	public void testCreateIO() {
		AndGate and = new AndGate(2);
		and.createIO(1, 1);
		assertTrue(and.getNoOfInputs() == 1 && and.getNoOfOutputs() == 1);
	}

	@Test
	public void testOverwriteGate() {
		AndGate and = new AndGate(1);
		and.createIO(1, 1);
		and.setOutput(0, true);

		AndGate test = new AndGate(2);
		test.createIO(2, 2);
		test.setOutput(0, false);

		and.overwriteGate(test);

		assertTrue(and.getInputs().equals(test.getInputs())
				&& and.getOutputValue(0) == test.getOutputValue(0)
				&& and.getNoOfInputs() == 2 && and.getNoOfOutputs() == 2);
	}

	@Test
	public void testConnectInput() {
		AndGate and = new AndGate(2);
		AndGate test = new AndGate(1);
		
		and.connectInput(0, test, 0);
		
		assertEquals(test, and.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testGetOutputValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNoOfInputs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNoOfOutputs() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComponentTier() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateOutput() {
		fail("Not yet implemented");
	}

}
