package edu.chl.tda367.booleancircuits.io.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;

public class ComponentFolderTest {
	List<ICircuitGate> componentList;



	@Test
	public void testComponentFolderListOfAbstractCircuitGateString() {
		componentList = new ArrayList<ICircuitGate>();
		new ComponentFolder(componentList, "vippa");
		
	}

	@Test
	public void testGetName() {
		ComponentFolder folder = new ComponentFolder(componentList, "vippa");
		assertTrue(folder.getName().equals("vippa"));
	}

	@Test
	public void testGetAllComponents() {
		componentList = new ArrayList<ICircuitGate>();
		ComponentFolder folder = new ComponentFolder(componentList, "vippa");
		assertTrue(folder.getAllComponents().size()==0);
		
		//TODO:fix this
	}

}
