package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.Constants;

/**
 * Model for managing and updating circuit components.
 */
public final class Model implements IModel{
	private String name;
	private final Collection<IAbstractCircuitGate> componentList;

	/**
	 * Returns an instance of Model
	 * 
	 * @param name
	 *            the name of the model
	 */
	public Model(String name) {
		componentList = new ArrayList<IAbstractCircuitGate>();
		this.name = name;
	}

	public void addComponent(IAbstractCircuitGate component, Point position) {
		AbstractCircuitGate c = component.clone();
		c.setPosition(position);
		componentList.add(c);
	}

	public Collection<IAbstractCircuitGate> getComponents() {
		return componentList;
	}

	public IAbstractCircuitGate getComponent(Point position) {
		int size = Constants.componentSize;
		for (IAbstractCircuitGate acg : componentList) {
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

	public void removeComponents(Collection<IAbstractCircuitGate> list) {
		for (IAbstractCircuitGate i : list) {
			_removeComponent(i);
		}
	}

	public void updateComponents() {
		List<List<IAbstractCircuitGate>> groupList = new LinkedList<List<IAbstractCircuitGate>>();

		// Sort our components in groups of update tiers.
		int loopCount = 1;
		boolean endOfLoop = true;
		do {
			List<IAbstractCircuitGate> tempList = new LinkedList<IAbstractCircuitGate>();
			endOfLoop = true;
			for (IAbstractCircuitGate i : componentList) {
				if (i.getComponentTier() == loopCount) {
					tempList.add(i);
					endOfLoop = false;
				}
			}
			groupList.add(tempList);
			loopCount++;
		} while (!endOfLoop);

		// Update each tier individually
		for (List<IAbstractCircuitGate> l : groupList) {
			List<AbstractCircuitGate> cloneList = new ArrayList<AbstractCircuitGate>();
			for (IAbstractCircuitGate g : l) {
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

	@Override
	public void removeComponent(IAbstractCircuitGate g) {
		_removeComponent(g);
		updateComponents();
	}
	
	private void _removeComponent(IAbstractCircuitGate g) {
		componentList.remove(g);
	}
}