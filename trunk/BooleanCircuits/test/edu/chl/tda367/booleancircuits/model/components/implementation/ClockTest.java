package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class ClockTest {

	@Test
	public void testClock() {
		new Clock();
	}

	@Test
	public void testToString() {
		assertEquals("CLOCK", new Clock().toString());
	}

	@Test
	public void testEmptyGateClone() {
		Clock clock = new Clock();
		ICircuitGate testGate = clock.emptyGateClone();

		assertTrue(testGate instanceof Clock);
		assertTrue(testGate.getNoOfInputs() == clock.getNoOfInputs());
	}

	@Test
	public void testToggleClock() {
		Clock clock = new Clock();
		assertFalse(clock.getOutputValue(0));
		clock.toggleClock();
		clock.update();
		assertTrue(clock.getOutputValue(0));
		clock.toggleClock();
		clock.update();
		assertFalse(clock.getOutputValue(0));

	}

	@Test
	public void testUpdateOutput() {
		Clock clock = new Clock();
		AndGate and = new AndGate(2);
		assertFalse(clock.getOutputValue(0));
		and.connectInput(0,clock,0);
		and.connectInput(0, new ConstantGate(true), 1);

		clock.toggleClock();
		clock.update();
		assertTrue(clock.getOutputValue(0)==true);



	}

}
