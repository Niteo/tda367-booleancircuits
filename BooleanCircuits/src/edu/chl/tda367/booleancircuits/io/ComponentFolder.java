package edu.chl.tda367.booleancircuits.io;

import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.AndGate;
import edu.chl.tda367.booleancircuits.model.components.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.NandGate;
import edu.chl.tda367.booleancircuits.model.components.NorGate;
import edu.chl.tda367.booleancircuits.model.components.NotGate;
import edu.chl.tda367.booleancircuits.model.components.OrGate;
import edu.chl.tda367.booleancircuits.model.components.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.XorGate;

/**
 * This class creates a file in which there are a number of logical components
 * 
 * @author antonlin
 * 
 */

public class ComponentFolder {

	private List<AbstractCircuitGate> componentList;
	private String name;

	/**
	 * Creates a File from pathName Creates a list of components which lies in
	 * pathName folder
	 * 
	 * @param pathName
	 */
	public ComponentFolder() {
		componentList = new ArrayList<AbstractCircuitGate>();
		name = "Standard Gates";
		initStandardGates();
	}

	public ComponentFolder(List<AbstractCircuitGate> componentList, String name) {
		this.name = name;
		this.componentList = componentList;
		initStandardGates();
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

	private void initStandardGates() {
		componentList.add(new AndGate(2));
		componentList.add(new ConstantGate(true));
		componentList.add(new ConstantGate(false));
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
	public List<AbstractCircuitGate> getAllComponents() {
		return componentList;
	}

}
