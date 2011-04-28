package edu.chl.tda367.booleancircuits.model;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

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
	 * @param component the new component to add
	 * @param 
	 */
	public void addComponent(AbstractCircuitGate component, Point position) {
		//AbstractCircuitGate c = GateFactory.getNewComponent(component);
		AbstractCircuitGate c = component.clone();
		c.setPosition(position);
		componentList.add(c);
		//TODO: FIX updatecomponents
		//updateComponents();
	}

	/**
	 * Returns a list of selected components
	 * 
	 * @return a list of selected components
	 */
	public List<AbstractCircuitGate> getSelectedComponents() {
		List<AbstractCircuitGate> retList =
			new ArrayList<AbstractCircuitGate>();
		for(AbstractCircuitGate cg : selectedComponentList){
			retList.add(cg.clone());
		}
		
		return retList;
	}

	/**
	 * Returns a list of components
	 * 
	 * @return a list of components
	 */
	public List<AbstractCircuitGate> getComponents() {
		List<AbstractCircuitGate> retList =
			new ArrayList<AbstractCircuitGate>();
		for(AbstractCircuitGate cg : componentList){
			retList.add(cg.clone());
		}
		
		return retList;
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
	public void selectComponent(Point position) {
		// TODO: Change component size!!!
		// TODO: THE ABOVE. VEVWY IMPORTANT!!
		// TODO: I PROMIZ!!!!
		// TODO: :(
		int sizeX = 20, sizeY = 20;
		// TODO: THE VARIABLES ABOVE... FIIIIIIIX!
		for(AbstractCircuitGate acg : componentList){
			// Check X
			if(position.getX() >= acg.getPosition().getX() - sizeX * 0.5f &&
					position.getX() <= acg.getPosition().getX() + sizeX * 0.5f){
				// Check Y
				if(position.getY() >= acg.getPosition().getY() - sizeY * 0.5f &&
						position.getY() <= acg.getPosition().getY() + sizeY * 0.5f){
					// Component was selected
					if(selectedComponentList.contains(acg)){
						selectedComponentList.remove(acg);
					} else {
						selectedComponentList.add(acg);
					}					
				}	
			}
		}
	}

	/**
	 * Removes the components that are selected
	 */
	public void removeSelectedComponents() {
		for (AbstractCircuitGate i : selectedComponentList) {
			componentList.remove(i);
		}
		selectedComponentList.clear();
		updateComponents();
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