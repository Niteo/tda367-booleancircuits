package edu.chl.tda367.booleancircuits.io.implementation;

import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.io.IComponentFolder;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;

/**
 * This class creates a folder in which there are a number of logical components
 * 
 * @author antonlin
 * 
 */

public class ComponentFolder implements IComponentFolder {

	private List<ICircuitGate> componentList;
	private String name;

	public ComponentFolder() {
		componentList = new ArrayList<ICircuitGate>();
		name = "Logic Gates";
		initStandardGates();
	}

	public ComponentFolder(List<ICircuitGate> componentList, String name) {
		this.name = name;
		this.componentList = componentList;
	}

	/**
	 * returns name of componentfolder
	 * 
	 * @return
	 */
	@Override
	public String getName() {
		return name;
	}

	private void initStandardGates() {
		componentList.add(new AndGate(2));
		componentList.add(new NandGate(2));
		componentList.add(new NorGate(2));
		componentList.add(new NotGate());
		componentList.add(new OrGate(2));
		componentList.add(new XnorGate(2));
		componentList.add(new XorGate(2));
	}

	/**
	 * returns a list of AbstractCircuitGate, during tests(in development) it
	 * will just return a list of strings
	 * 
	 * @return
	 */
	@Override
	public List<ICircuitGate> getAllComponents() {
		return componentList;
	}

}