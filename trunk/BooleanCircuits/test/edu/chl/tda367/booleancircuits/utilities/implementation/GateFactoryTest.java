package edu.chl.tda367.booleancircuits.utilities.implementation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory.Components;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory.PredefinedComponents;

public class GateFactoryTest {

	@Test
	public void testGetNewComponentComponentsInt() {
		ICircuitGate and = GateFactory.getNewComponent(Components.AND, 3);
		assertTrue(and instanceof AndGate);
		assertTrue(and.getNoOfInputs() == 3);
	}

	@Test
	public void testGetNewComponentPredefinedComponents() {
		assertTrue(GateFactory.getNewComponent(PredefinedComponents.NOT) instanceof NotGate);
	}

	@Test
	public void testGetNewComponentStringInt() {
		ICircuitGate and = GateFactory.getNewComponent("AND", 4);
		assertTrue(and instanceof AndGate);
		assertTrue(and.getNoOfInputs() == 4);
	}

}
