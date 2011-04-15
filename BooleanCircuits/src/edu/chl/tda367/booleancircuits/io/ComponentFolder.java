package edu.chl.tda367.booleancircuits.io;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * This class creates a file in which there are a number of logical components
 * 
 * @author antonlin
 * 
 */

public class ComponentFolder {

	private List<AbstractCircuitGate> componentList;
	private final String name;

	/**
	 * Creates a File from pathName Creates a list of components which lies in
	 * pathName folder
	 * 
	 * @param pathName
	 */
	public ComponentFolder(List<AbstractCircuitGate> componentList, String name) {
		this.name = name;
		this.componentList = componentList;
	}

	public String getName() {
		return name;
	}

	/**
	 * returns a File object representing a component
	 * 
	 * @param index
	 * @return component at specified index in list
	 */
	public AbstractCircuitGate getComponent(int index) {
		// TODO: implementation, not sure if it's going to singel out a
		// component by it's index. But for now, let it be.
		return componentList.get(index);
	}

	/**
	 * returns a list of AbstractCircuitGate, during tests(in development) it
	 * will just return a list of strings
	 * 
	 * @return
	 */
	public List<AbstractCircuitGate> getAllComponents() {
		return componentList;
	}

}
