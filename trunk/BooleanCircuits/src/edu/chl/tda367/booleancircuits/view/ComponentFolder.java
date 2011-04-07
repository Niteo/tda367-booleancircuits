package edu.chl.tda367.booleancircuits.view;

import java.io.File;
import java.util.ArrayList;
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
	private final File folder;
	private final String name;

	/**
	 * Creates a File from pathName Creates a list of components which lies in
	 * pathName folder
	 * 
	 * @param pathName
	 */
	public ComponentFolder(String pathName, String name) {
		folder = new File(pathName);
		this.name = name;
		initComponentList();
	}

	private void initComponentList() {
		// TODO: Implement so that a list of AbstractCircuitComponent is
		// generated from List containing string files;
		componentList = new ArrayList<AbstractCircuitGate>();
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
		// TODO: implementation
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
