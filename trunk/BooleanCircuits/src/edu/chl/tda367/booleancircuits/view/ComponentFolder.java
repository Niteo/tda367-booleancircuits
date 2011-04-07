package edu.chl.tda367.booleancircuits.view;

import java.io.File;

/**
 * This class creates a file in which there are a number of logical components
 * 
 * @author antonlin
 * 
 */

public class ComponentFolder {

	private final File[] componentList;
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
		componentList = folder.listFiles();
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
	public File getComponent(int index) {
		return componentList[index];
	}

}
