package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.Constants;

/**
 * Model for managing and updating circuit components.
 */
public final class Model implements IModel{
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


	public void addComponent(AbstractCircuitGate component, Point position) {
		AbstractCircuitGate c = component.clone();
		c.setPosition(position);
		componentList.add(c);
		updateComponents();
	}


	public List<AbstractCircuitGate> getComponents() {
		return componentList;
	}

	public void selectAllComponents() {
		selectedComponentList.clear();
		selectedComponentList.addAll(componentList);
	}
	

	public AbstractCircuitGate getComponent(Point position) {
		int size = Constants.componentSize;
		for (AbstractCircuitGate acg : componentList) {
			// Check X
			if (position.getX() >= acg.getPosition().getX() - size * 0.5f
					&& position.getX() <= acg.getPosition().getX() + size
							* 0.5f) {
				// Check Y
				if (position.getY() >= acg.getPosition().getY() - size * 0.5f
						&& position.getY() <= acg.getPosition().getY() + size
								* 0.5f) {
					// Component found
					return acg;
				}
			}
		}
		
		return null;
	}

	public void selectComponent(Point position) {
		AbstractCircuitGate acg = this.getComponent(position);
		
		if (selectedComponentList.contains(acg)) {
			selectedComponentList.remove(acg);
		} else {
			selectedComponentList.add(acg);
		}
	}


	public void removeSelectedComponents() {
		for (AbstractCircuitGate i : selectedComponentList) {
			componentList.remove(i);
		}
		selectedComponentList.clear();
		updateComponents();
	}


	public boolean isSelectedComponent(AbstractCircuitGate component) {
		if (selectedComponentList.contains(component)) {
			return true;
		} else {
			return false;
		}
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
			loopCount++;
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


	@Override
	public String toString() {
		return name;
	}
}