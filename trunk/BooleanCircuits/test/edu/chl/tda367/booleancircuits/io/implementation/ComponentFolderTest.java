package edu.chl.tda367.booleancircuits.io.implementation;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public class ComponentFolderTest {
	List<ICircuitGate> componentList = new ArrayList<ICircuitGate>();

	@Test
	public void testComponentFolderListOfAbstractCircuitGateString() {
		componentList = new ArrayList<ICircuitGate>();
		new ComponentFolder(componentList, "vippa");

	}

	@Test
	public void testGetAllComponents() {
		ComponentFolder folder = new ComponentFolder(componentList, "vippa");
		assertTrue(folder.getAllComponents().size() == 0);
	}

	@Test
	public void testGetName() {
		ComponentFolder folder = new ComponentFolder(componentList, "vippa");
		assertTrue(folder.getName().equals("vippa"));
	}

}
