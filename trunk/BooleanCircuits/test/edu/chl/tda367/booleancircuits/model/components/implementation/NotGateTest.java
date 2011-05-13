package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class NotGateTest {

	@Test
	public void testNotGate() {
		new NotGate();
	}
	
	@Test
	public void testGetNoOfInputs() {
		assertTrue(new NotGate().getNoOfInputs()==1);
	}
	
	@Test
	public void testGetNoOfOutputs() {
		assertTrue(new NotGate().getNoOfOutputs()==1);
	}

	@Test
	public void testGetInputs() {
		assertTrue(new NotGate().getInputs() instanceof List);
	}

	@Test
	public void testSetOutput() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOutputValue() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetRecoupledTo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmptyGateClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateIO() {
		fail("Not yet implemented");
	}

	@Test
	public void testOverwriteGate() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectInput() {
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
	public void testConnectsTo() {
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
