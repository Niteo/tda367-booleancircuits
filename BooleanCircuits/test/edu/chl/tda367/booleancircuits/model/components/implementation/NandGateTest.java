package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NandGateTest {

	@Test
	public void testNandGate() {
		new NandGate(2);
	}
	
	@Test
	public void testGetNoOfInputs() {
		NandGate nand = new NandGate(2);
		assertTrue(nand.getNoOfInputs()==2);
	}

	@Test
	public void testGetNoOfOutputs() {
		NandGate nand = new NandGate(2);
		assertTrue(nand.getNoOfOutputs()==1);
	}

	@Test
	public void testGetInputs() {
		NandGate nand = new NandGate(2);
		assertTrue(nand.getInputs() instanceof List);
	}

	@Test
	public void testGetOutputValue() {
		NandGate nand = new NandGate(2);
		assertFalse(nand.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		NandGate nand = new NandGate(2);
		nand.setOutput(0, true);
		assertEquals(true, nand.getOutputValue(0));
	}

	@Test
	public void testEmptyGateClone() {
		NandGate nand = new NandGate(2);
		ICircuitGate testGate = nand.emptyGateClone();
		
		assertTrue(testGate instanceof NandGate);
		assertTrue(testGate.getNoOfInputs()==nand.getNoOfInputs());
	}

	@Test
	public void testToString() {
		assertEquals("NAND", new NandGate(2).toString());
	}

	@Test
	public void testCreateIO() {
		NandGate nand = new NandGate(3);
		nand.createIO(1, 1);
		assertTrue(nand.getNoOfInputs() == 1 && nand.getNoOfOutputs() == 1);
	}

	@Test
	public void testOverwriteGate() {
		NandGate nand = new NandGate(1);
		nand.createIO(1, 1);
		nand.setOutput(0, true);

		NandGate test = new NandGate(2);
		test.createIO(2, 2);
		test.setOutput(0, false);

		nand.overwriteGate(test);

		assertTrue(nand.getInputs().equals(test.getInputs())
				&& nand.getOutputValue(0) == test.getOutputValue(0)
				&& nand.getNoOfInputs() == 2 && nand.getNoOfOutputs() == 2);
	}

	@Test
	public void testConnectInput() {
		NandGate nand = new NandGate(2);
		NandGate test = new NandGate(1);

		nand.connectInput(0, test, 0);

		assertEquals(test, nand.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testUpdate() {
		new NandGate(2).update();
	}

	@Test
	public void testGetComponentTier() {
		NandGate nand = new NandGate(2);
		assertTrue(nand.getComponentTier() == 1);
		nand.connectInput(0, new NandGate(2), 0);
		assertTrue(nand.getComponentTier() == 2);
	}

	@Test
	public void testGetRecoupledTo() {
		NandGate nand = new NandGate(2);
		NandGate testGate = new NandGate(2);
		assertTrue(nand.getRecoupledTo().size()==0);
		
		nand.connectInput(0, testGate, 0);
		testGate.connectInput(1, nand, 1);
		assertTrue(nand.getRecoupledTo().size()==1);
	}

	@Test
	public void testConnectsTo() {
		NandGate nand = new NandGate(2);
		NandGate testGate = new NandGate(2);
		nand.connectInput(0, testGate, 0);
		assertTrue(nand.connectsTo(testGate));
	}

	@Test
	public void testClone() {
		NandGate nand = new NandGate(2);
		ICircuitGate clone = nand.clone();
		assertTrue(clone instanceof NandGate);
		assertTrue(clone.getPosition().x == nand.getPosition().x
				&& clone.getPosition().y == nand.getPosition().y);
		assertTrue(clone.getNoOfInputs()== nand.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs()== nand.getNoOfOutputs());
	}
	
	@Test
	public void testUpdateOutput() {
		NandGate nand = new NandGate(2);
		assertFalse(nand.getOutputValue(0));
		ConstantGate oneGate1 = new ConstantGate(false);
		ConstantGate oneGate2 = new ConstantGate(false);

		nand.connectInput(0, oneGate1, 0);
		oneGate1.update();
		assertFalse(nand.getInputs().get(0).getInputValue());
		nand.connectInput(1, oneGate2, 0);
		oneGate2.update();
		assertFalse(nand.getInputs().get(1).getInputValue());

		nand.update();
		assertTrue(nand.getOutputValue(0));
	}

}
