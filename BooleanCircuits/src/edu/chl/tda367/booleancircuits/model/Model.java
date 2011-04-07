package edu.chl.tda367.booleancircuits.model;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;


/**
 * Model for managing and updating circuit components.
 */
public final class Model {

	private final List<AbstractCircuitGate> componentList;
	private final List<AbstractCircuitGate> selectedComponentList;

	/**
	 * Returns an instance of Model
	 */
	public Model() {
		componentList = new ArrayList<AbstractCircuitGate>();
		selectedComponentList = new ArrayList<AbstractCircuitGate>();
	}

	/**
	 * Adds a component to the model.
	 * @param the new component to add
	 */
	public void addComponent(AbstractCircuitGate component) {
		componentList.add(component);
		// TODO: Add a copy instead of the reference(!!)
	}

	/**
	 * Returns a list of selected components
	 * @return a list of selected components 
	 */
	public List<AbstractCircuitGate> getSelectedComponents() {
		return selectedComponentList;
	}

	/**
	 * Selects all components in the model.
	 */
	public void selectAllComponents() {
		selectedComponentList.clear();
		selectedComponentList.addAll(componentList);
	}
	
	/**
	 * Selects the first found component at the specified coordinate
	 * @param coordinate the coordinate for the component
	 */
	public void selectComponent(Coordinate coordinate) {
		// TODO: Add logic here
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Removes the components that are selected
	 */
	public void removeSelectedComponent() {
		for (AbstractCircuitGate i : selectedComponentList) {
			componentList.remove(i);
		}
		selectedComponentList.clear();
	}

	
	private void updateComponents(){
		throw new UnsupportedOperationException();
		// TODO: Add code here!
	}
}