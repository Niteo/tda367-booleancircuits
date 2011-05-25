package edu.chl.tda367.booleancircuits.utilities.implementation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Equal;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory.Components;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory.PredefinedComponents;

public class GateFactoryTest {

	@Test
	public void testGetNewComponentComponentsInt() {
		ICircuitGate and = GateFactory.getNewComponent(Components.AND, 3);
		assertTrue(and instanceof AndGate);
		assertTrue(and.getNoOfInputs() == 3);

		ICircuitGate nand = GateFactory.getNewComponent(Components.NAND, 4);
		assertTrue(nand instanceof NandGate);
		assertTrue(nand.getNoOfInputs() == 4);

		ICircuitGate or = GateFactory.getNewComponent(Components.OR, 5);
		assertTrue(or instanceof OrGate);
		assertTrue(or.getNoOfInputs() == 5);

		ICircuitGate nor = GateFactory.getNewComponent(Components.NOR, 6);
		assertTrue(nor instanceof NorGate);
		assertTrue(nor.getNoOfInputs() == 6);

		ICircuitGate xor = GateFactory.getNewComponent(Components.XOR, 7);
		assertTrue(xor instanceof XorGate);
		assertTrue(xor.getNoOfInputs() == 7);

		ICircuitGate xnor = GateFactory.getNewComponent(Components.XNOR, 8);
		assertTrue(xnor instanceof XnorGate);
		assertTrue(xnor.getNoOfInputs() == 8);
	}

	@Test
	public void testGetNewComponentPredefinedComponents() {
		assertTrue(GateFactory.getNewComponent(PredefinedComponents.NOT) instanceof NotGate);
		assertTrue(GateFactory
				.getNewComponent(PredefinedComponents.CONSTANTGATE_ONE) instanceof ConstantGate);
		assertTrue(GateFactory
				.getNewComponent(PredefinedComponents.CONSTANTGATE_ZERO) instanceof ConstantGate);
		assertTrue(GateFactory.getNewComponent(PredefinedComponents.CLOCK) instanceof Clock);
		assertTrue(GateFactory.getNewComponent(PredefinedComponents.EQUAL) instanceof Equal);
	}

	@Test
	public void testGetNewComponentStringInt() {
		ICircuitGate and = GateFactory.getNewComponent("AND", 2);
		assertTrue(and instanceof AndGate);
		assertTrue(and.getNoOfInputs() == 2);

		ICircuitGate nand = GateFactory.getNewComponent("NAND", 3);
		assertTrue(nand instanceof NandGate);
		assertTrue(nand.getNoOfInputs() == 3);

		ICircuitGate or = GateFactory.getNewComponent("OR", 4);
		assertTrue(or instanceof OrGate);
		assertTrue(or.getNoOfInputs() == 4);

		ICircuitGate nor = GateFactory.getNewComponent("NOR", 5);
		assertTrue(nor instanceof NorGate);
		assertTrue(nor.getNoOfInputs() == 5);

		ICircuitGate xor = GateFactory.getNewComponent("XOR", 6);
		assertTrue(xor instanceof XorGate);
		assertTrue(xor.getNoOfInputs() == 6);

		ICircuitGate xnor = GateFactory.getNewComponent("XNOR", 7);
		assertTrue(xnor instanceof XnorGate);
		assertTrue(xnor.getNoOfInputs() == 7);

		ICircuitGate not = GateFactory.getNewComponent("NOT", 1);
		assertTrue(not instanceof NotGate);
		assertTrue(not.getNoOfInputs() == 1);

		ICircuitGate one = GateFactory.getNewComponent("1", 0);
		assertTrue(one instanceof ConstantGate);
		assertTrue(one.getNoOfInputs() == 0);

		ICircuitGate zero = GateFactory.getNewComponent("0", 0);
		assertTrue(zero instanceof ConstantGate);
		assertTrue(zero.getNoOfInputs() == 0);

		ICircuitGate clock = GateFactory.getNewComponent("CLOCK", 0);
		assertTrue(clock instanceof Clock);
		assertTrue(clock.getNoOfInputs() == 0);

		ICircuitGate equal = GateFactory.getNewComponent("EQUAL", 1);
		assertTrue(equal instanceof Equal);
		assertTrue(equal.getNoOfInputs() == 1);
	}

}
