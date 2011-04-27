package edu.chl.tda367.booleancircuits.model;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * Model for managing and updating circuit components.
 */
public final class Model {

	private String name;
	private final List<AbstractCircuitGate> componentList;
	private final List<AbstractCircuitGate> selectedComponentList;

	/**
	 * Returns an instance of Model
	 * 
	 * @param name
	 *            the name of the model
	 */
	public Model(String name) {
		componentList = new ArrayList<AbstractCircuitGate>();
		selectedComponentList = new ArrayList<AbstractCircuitGate>();
		this.name = name;
	}

	/**
	 * Adds a component to the model.
	 * 
	 * @param the
	 *            new component to add
	 */
	public void addComponent(AbstractCircuitGate component) {
		componentList.add(component);
		// TODO: Add a copy instead of the reference(!!)
	}

	/**
	 * Returns a list of selected components
	 * 
	 * @return a list of selected components
	 */
	public List<AbstractCircuitGate> getSelectedComponents() {
		return selectedComponentList;
		/*
		 * for(int i = 0; i<=selectedComponentList.size();i++){
		 * List<AbstractCircuitGate> selectedList = new
		 * ArrayList<AbstractCircuitGate>(); selectedList =
		 * ((List<AbstractCircuitGate>) selectedComponentList).
		 * 
		 * } return null;
		 */
		// TODO: clone selectedComponentList
	}

	/**
	 * Returns a reference to the list of components
	 * 
	 * @return a reference to the list of components
	 */
	public List<AbstractCircuitGate> getComponents() {
		return componentList;
		// TODO: clone componentList
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
	 * 
	 * @param coordinate
	 *            the coordinate for the component
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

	private void updateComponents() {
		List<List<AbstractCircuitGate>> groupList = new LinkedList<List<AbstractCircuitGate>>();

		// Sort our components in groups of update tiers.
		int loopCount = 1;
		boolean endOfLoop = true;
		do {
			List<AbstractCircuitGate> tempList = new LinkedList<AbstractCircuitGate>();
			endOfLoop = true;
			for (AbstractCircuitGate i : componentList) {
				if (i.getComponentTier() == loopCount) {
					tempList.add(i);
					endOfLoop = false;
				}
			}
		} while (!endOfLoop);

		// Update each tier individually
		for (List<AbstractCircuitGate> l : groupList) {
			List<AbstractCircuitGate> cloneList = new ArrayList<AbstractCircuitGate>();
			for (AbstractCircuitGate g : l) {
				AbstractCircuitGate temp = g.clone();
				temp.update();
				cloneList.add(temp);
			}

			for (int i = 0; i < cloneList.size(); i++) {
				l.get(i).overwriteGate(cloneList.get(i));
			}
		}
	}

	/**
	 * Returns the name of the workspace
	 */
	@Override
	public String toString() {
		return name;
	}
}