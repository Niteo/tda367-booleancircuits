package edu.chl.tda367.booleancircuits.model;

import java.util.*;


/**
 * The model can add components, set and get selected components, selectAll, removes selected component
 */
public final class Model {

	private List<CircuitComponent> componentList;
	private List<CircuitComponent> selectedComponentList;

	public Model() {

		List componentList = new ArrayList();
		List selectedComponentList = new ArrayList();
	}

	/**
	 * @param the new component is selected  
	 * adds the component that the user has chosen in the palette
	 */
	public void addComponent(CircuitComponent component) {
		componentList.add(component);
	}

	/**
	 * gets a list of selected components
	 * @return a list of selected components 
	 */
	public List<CircuitComponent> getSelectedComponents() {
		return selectedComponentList;

	}

	/**
	 * removes the components that are selected
	 */
	private void removeSelectedComponent() {
		for (CircuitComponent i : selectedComponentList) {
			componentList.remove(i);

		}
		selectedComponentList.clear();
	}

	private void setSelectedComponent(Coordinate coordinate) {

	}

	private void selectAll() {
		
	}
	// TODO: private void setNewConnection(){}, setSelectedComponent(Coordinate coordinate) and selectAll()
}
