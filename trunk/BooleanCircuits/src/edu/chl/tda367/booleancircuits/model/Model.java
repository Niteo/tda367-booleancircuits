package edu.chl.tda367.booleancircuits.model;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitComponent;


/**
 * The model can add components, set and get selected components, selectAll, removes selected component
 */
public final class Model {

	private List<AbstractCircuitComponent> componentList;
	private List<AbstractCircuitComponent> selectedComponentList;

	public Model() {

		List componentList = new ArrayList();
		List selectedComponentList = new ArrayList();
	}

	/**
	 * @param the new component is selected  
	 * adds the component that the user has chosen in the palette
	 */
	public void addComponent(AbstractCircuitComponent component) {
		componentList.add(component);
	}

	/**
	 * gets a list of selected components
	 * @return a list of selected components 
	 */
	public List<AbstractCircuitComponent> getSelectedComponents() {
		return selectedComponentList;

	}

	/**
	 * removes the components that are selected
	 */
	private void removeSelectedComponent() {
		for (AbstractCircuitComponent i : selectedComponentList) {
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
