package edu.chl.tda367.booleancircuits.io.implementation;

import java.util.List;

import edu.chl.tda367.booleancircuits.io.IComponentFolder;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

/**
 * This class creates a folder in which there are a number of logical components
 * 
 * @author antonlin
 * 
 */

public class ComponentFolder implements IComponentFolder {

	private List<ICircuitGate> componentList;
	private String name;

	public ComponentFolder(List<ICircuitGate> componentList, String name) {
		this.name = name;
		this.componentList = componentList;
	}

	/**
	 * Returns name of componentfolder
	 * 
	 * @return
	 */
	@Override
	public String getName() {
		return name;
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
