package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;

public class GateInputTest {

	@Test
	public void testGateInput() {
		new GateInput();
	}

	@Test
	public void testSetInputComponent() {
		GateInput input = new GateInput();
		input.setInputComponent(new ConstantGate(false), 0);

	}

	@Test
	public void testGetInputComponent() {
		GateInput input = new GateInput();
		assertNull(input.getInputComponent());

		input.setInputComponent(new ConstantGate(false), 0);
		assertTrue(input.getInputComponent() instanceof ConstantGate);

	}

	@Test
	public void testGetInputPort() {
		GateInput input1 = new GateInput();
		GateInput input2 = new GateInput();
		assertTrue(input2.getInputPort() == 0);

		input1.setInputComponent(new ConstantGate(false), 0);
		input2.setInputComponent(new AndGate(2), 2);

		assertTrue(input1.getInputPort() == 0);
		assertTrue(input2.getInputPort() == 0);

	}

	@Test
	public void testGetInputValue() {
		GateInput input1 = new GateInput();
		GateInput input2 = new GateInput();
		ConstantGate gate = new ConstantGate(true);
		input2.setInputComponent(gate, 0);
		gate.update();
		assertTrue(input1.getInputValue() == false);
		assertTrue(input2.getInputValue() == true);
	}

	@Test
	public void testReset() {
		IGateInput input = new GateInput();
		input.setInputComponent(new AndGate(2), 0);
		input.reset();
		assertNull(input.getInputComponent());
		assertTrue(input.getInputPort() == 0);
	}

}
