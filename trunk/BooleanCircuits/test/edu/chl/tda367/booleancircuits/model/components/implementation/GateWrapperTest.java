package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

public class GateWrapperTest {

	@Test
	public void testGateWrapper() {
		new GateWrapper(new AndGate(2));
	}

	@Test
	public void testGetPosition() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		assertTrue(and.getPosition().equals(new Point(0, 0)));
	}

	@Test
	public void testSetPosition() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		and.setPosition(new Point(8, 9));
		assertTrue(and.getPosition().equals(new Point(8, 9)));
	}

	@Test
	public void testMove() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		and.setPosition(new Point(8, 8));
		and.move(4, 4);
		assertTrue(and.getPosition().equals(new Point(12, 12)));
	}

	@Test
	public void testConnectsTo() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper nand = new GateWrapper(new NandGate(2));
		assertFalse(and.connectsTo(nand));
		and.connectInput(0, nand, 0);
		assertTrue(and.connectsTo(nand));
	}

	@Test
	public void testGetNoOfInputs() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		assertTrue(and.getNoOfInputs() == 2);
	}

	@Test
	public void testGetNoOfOutputs() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		assertTrue(and.getNoOfOutputs() == 1);
	}

	@Test
	public void testGetOutputs() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper test1 = new GateWrapper(new ConstantGate(true));
		IGateWrapper test2 = new GateWrapper(new ConstantGate(true));

		and.connectInput(0, test1, 0);
		and.connectInput(1, test2, 0);

		test1.update();
		test2.update();
		and.update();

		assertTrue(and.getOutputs()[0]);
	}

	@Test
	public void testToString() {
		assertTrue(new GateWrapper(new AndGate(2)).toString().equals("AND"));
	}

	@Test
	public void testIsFullyConnected() {
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper test1 = new GateWrapper(new ConstantGate(true));
		IGateWrapper test2 = new GateWrapper(new ConstantGate(true));

		assertFalse(and.isFullyConnected());

		and.connectInput(0, test1, 0);
		and.connectInput(1, test2, 0);

		assertTrue(and.isFullyConnected());
	}

}
