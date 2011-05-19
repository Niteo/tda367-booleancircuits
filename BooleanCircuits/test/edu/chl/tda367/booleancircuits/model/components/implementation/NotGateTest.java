package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class NotGateTest {

	@Test
	public void testNotGate() {
		new NotGate();
	}

	@Test
	public void testGetNoOfInputs() {
		assertTrue(new NotGate().getNoOfInputs() == 1);
	}

	@Test
	public void testGetNoOfOutputs() {
		assertTrue(new NotGate().getNoOfOutputs() == 1);
	}

	@Test
	public void testGetInputs() {
		NotGate not = new NotGate();
		assertTrue(not.getInputs().size() == 1);
	}

	@Test
	public void testGetOutputValue() {
		NotGate not = new NotGate();
		assertTrue(not.getOutputValue(0));
	}

	@Test
	public void testSetOutput() {
		NotGate not = new NotGate();
		not.setOutput(0, true);
		assertTrue(not.getOutputValue(0));
	}

	@Test
	public void testConnectInput() {
		NotGate not = new NotGate();
		NotGate test = new NotGate();

		not.connectInput(0, test, 0);

		assertEquals(test, not.getInputs().get(0).getInputComponent());
	}

	@Test
	public void testGetRecoupledTo() {
		NotGate not = new NotGate();
		NotGate testGate = new NotGate();
		assertTrue(not.getRecoupledTo().size() == 0);

		not.connectInput(0, testGate, 0);
		testGate.connectInput(0, not, 0);
		assertTrue(not.getRecoupledTo().size() == 1);
	}

	@Test
	public void testEmptyGateClone() {
		NotGate not = new NotGate();
		ICircuitGate testGate = not.emptyGateClone();

		assertTrue(testGate instanceof NotGate);
		assertTrue(testGate.getNoOfInputs() == not.getNoOfInputs());
	}

	@Test
	public void testToString() {
		NotGate not = new NotGate();
		assertTrue(not.toString().equals("NOT"));
	}

	@Test
	public void testOverwriteGate() {
		NotGate not = new NotGate();
		not.setOutput(0, true);

		NotGate test = new NotGate();
		test.setOutput(0, false);

		not.overwriteGate(test);

		assertTrue(not.getInputs().equals(test.getInputs())
				&& not.getOutputValue(0) == test.getOutputValue(0));
	}

	@Test
	public void testGetComponentTier() {
		NotGate not = new NotGate();
		assertTrue(not.getComponentTier() == 1);
		not.connectInput(0, new NotGate(), 0);
		assertTrue(not.getComponentTier() == 2);
	}

	@Test
	public void testConnectsTo() {
		NotGate not = new NotGate();
		NotGate testGate = new NotGate();
		not.connectInput(0, testGate, 0);
		assertTrue(not.connectsTo(testGate));
	}

	@Test
	public void testGetPosition() {
		NotGate not = new NotGate();
		assertTrue(not.getPosition().x == 0 && not.getPosition().y == 0);
	}

	@Test
	public void testSetPosition() {
		NotGate not = new NotGate();
		assertTrue(not.getPosition().x == 0 && not.getPosition().y == 0);
		Point p = new Point(8, 8);
		not.setPosition(p);
		assertTrue(not.getPosition() == p);
	}

	@Test
	public void testMove() {
		NotGate not = new NotGate();
		assertTrue(not.getPosition().x == 0 && not.getPosition().y == 0);
		not.move(7, 8);
		assertTrue(not.getPosition().x == 7 && not.getPosition().y == 8);
	}

	@Test
	public void testClone() {
		NotGate not = new NotGate();
		ICircuitGate clone = not.clone();
		assertTrue(clone instanceof NotGate);
		assertTrue(clone.getPosition().x == not.getPosition().x
				&& clone.getPosition().y == not.getPosition().y);
		assertTrue(clone.getNoOfInputs() == not.getNoOfInputs());
		assertTrue(clone.getNoOfOutputs() == not.getNoOfOutputs());
	}

	@Test
	public void testUpdate() {
		new NotGate().update();
	}

	@Test
	public void testUpdateOutput() {
		NotGate not1 = new NotGate();
		NotGate not2 = new NotGate();
		assertTrue(not1.getOutputValue(0));
		ConstantGate oneGate = new ConstantGate(true);
		ConstantGate zeroGate = new ConstantGate(false);

		// Invert one
		not1.connectInput(0, oneGate, 0);
		oneGate.update();
		assertTrue(not1.getInputs().get(0).getInputValue());

		not1.update();
		assertFalse(not1.getOutputValue(0));

		// Invert zero
		not2.connectInput(0, zeroGate, 0);
		zeroGate.update();
		assertFalse(not2.getInputs().get(0).getInputValue());

		not2.update();
		assertTrue(not2.getOutputValue(0));
	}

}
