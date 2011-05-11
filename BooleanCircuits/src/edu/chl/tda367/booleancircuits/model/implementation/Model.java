package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

/**
 * Model for managing and updating circuit components.
 */
public final class Model implements IModel {
	private final Collection<IAbstractCircuitGate> componentList;

	/**
	 * Returns an instance of Model
	 * 
	 * @param name
	 *            the name of the model
	 */
	public Model() {
		componentList = new ArrayList<IAbstractCircuitGate>();
	}

	public void addComponent(IAbstractCircuitGate component, Point position) {
		component.setPosition(position);
		componentList.add(component);
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
		for (IAbstractCircuitGate gate : list) {
			_removeComponent(gate);
		}
	}

	public void clock() {
		for (IAbstractCircuitGate gate : componentList) {
			if (gate instanceof Clock) {
				((Clock) gate).toggleClock();
			}
		}
		updateComponents();
	}

	public void updateComponents() {
		List<List<IAbstractCircuitGate>> groupList = new ArrayList<List<IAbstractCircuitGate>>();

		// Prepare tiers
		int maxTier = 0;
		for (IAbstractCircuitGate iGate : componentList) {
			int tier = iGate.getComponentTier();
			if (tier > maxTier) {
				// Add a list per each tier jump made
				for (int i = maxTier; i < tier; i++) {
					groupList.add(new LinkedList<IAbstractCircuitGate>());
				}
				maxTier = tier;
			}
		}

		// Sort components into tiers
		for (IAbstractCircuitGate iGate : componentList) {
			Collection<IAbstractCircuitGate> recouples = iGate.getRecoupledTo();
			if (recouples.size() > 0) {
				int recouplesMaxTier = iGate.getComponentTier();
				for (IAbstractCircuitGate reGate : recouples) {
					int tier = reGate.getComponentTier();
					if(tier > recouplesMaxTier){
						recouplesMaxTier = tier;
					}
				}
				if(!groupList.get(recouplesMaxTier - 1).contains(iGate)){
					groupList.get(recouplesMaxTier - 1).add(iGate);
				}
				for(IAbstractCircuitGate addGate : recouples){
					if(!groupList.get(recouplesMaxTier - 1).contains(addGate)){
						groupList.get(recouplesMaxTier - 1).add(addGate);
					}
				}
			} else {
				groupList.get(iGate.getComponentTier() - 1).add(iGate);
			}
		}

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
	public void removeComponent(IAbstractCircuitGate g) {
		_removeComponent(g);
		updateComponents();
	}

	private void _removeComponent(IAbstractCircuitGate g) {
		componentList.remove(g);
	}
}