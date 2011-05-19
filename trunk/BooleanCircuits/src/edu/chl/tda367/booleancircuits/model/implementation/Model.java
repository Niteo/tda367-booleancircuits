package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

/**
 * Model for managing and updating circuit components.
 */
public final class Model implements IModel {
	private final Collection<ICircuitGate> componentList;
	private boolean infiniteRecursion = false;

	/**
	 * Returns an instance of Model
	 *
	 * @param name
	 *            the name of the model
	 */
	public Model() {
		componentList = new ArrayList<ICircuitGate>();
	}

	@Override
	public void addComponent(ICircuitGate component, Point position) {
		component.setPosition(position);
		componentList.add(component);
	}

	@Override
	public Collection<ICircuitGate> getComponents() {
		return Collections.unmodifiableCollection(componentList);
	}

	@Override
	public ICircuitGate getComponent(Point position) {
		int size = Constants.componentSize;
		for (ICircuitGate acg : componentList) {
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

	@Override
	public void removeComponents(Collection<ICircuitGate> list) {
		for (ICircuitGate gate : list) {
			_removeComponent(gate);
		}
	}

	@Override
	public void clock() {
		for (ICircuitGate gate : componentList) {
			if (gate instanceof Clock) {
				((Clock) gate).toggleClock();
			}
		}
		updateComponents();
	}

	@Override
	public void updateComponents() {
		List<List<ICircuitGate>> groupList = new ArrayList<List<ICircuitGate>>();
		infiniteRecursion = false;

		for (ICircuitGate iGate : componentList) {
			for (IGateInput input : iGate.getInputs()) {
				if (!componentList.contains(input.getInputComponent())) {
					input.reset();
				}
			}
		}

		// Prepare tiers
		int maxTier = 0;
		for (ICircuitGate iGate : componentList) {
			int tier = iGate.getComponentTier();
			if (tier > maxTier) {
				// Add a list per each tier jump made
				for (int i = maxTier; i < tier; i++) {
					groupList.add(new LinkedList<ICircuitGate>());
				}
				maxTier = tier;
			}
		}

		// Sort components into tiers
		for (ICircuitGate iGate : componentList) {
			Collection<ICircuitGate> recouples = iGate.getRecoupledTo();
			System.out.println(recouples);
			if (recouples.size() > 0) {
				int recouplesMinTier = iGate.getComponentTier();
				for (ICircuitGate reGate : recouples) {
					int tier = reGate.getComponentTier();
					if (tier < recouplesMinTier) {
						recouplesMinTier = tier;
					}
				}
				if (!groupList.get(recouplesMinTier - 1).contains(iGate)) {
					groupList.get(recouplesMinTier - 1).add(iGate);
				}
				for (ICircuitGate addGate : recouples) {
					if (!groupList.get(recouplesMinTier - 1).contains(addGate)) {
						groupList.get(recouplesMinTier - 1).add(addGate);
					}
				}
			} else {
				groupList.get(iGate.getComponentTier() - 1).add(iGate);
			}
		}
		// Update each tier individually
		boolean hasChanged;
		int loop = 0;
		do {
			hasChanged = false;
			for (List<ICircuitGate> l : groupList) {
				List<AbstractCircuitGate> cloneList = new ArrayList<AbstractCircuitGate>();
				for (ICircuitGate g : l) {
					AbstractCircuitGate temp = g.clone();
					if (temp.update()) {
						System.out.println(temp);
						hasChanged = true;
					}
					cloneList.add(temp);
				}
				for (int i = 0; i < cloneList.size(); i++) {
					l.get(i).overwriteGate(cloneList.get(i));
				}
			}
			if (++loop == 100) {
				hasChanged = false;
				infiniteRecursion = true;
			}
		} while (hasChanged);
	}

	@Override
	public void removeComponent(ICircuitGate g) {
		_removeComponent(g);
		updateComponents();
	}

	@Override
	public void addComponents(Collection<ICircuitGate> components) {
		componentList.addAll(components);
	}

	@Override
	public int getNumberOfComponents() {
		return componentList.size();
	}

	@Override
	public boolean hasInfiniteRecursion() {
		return infiniteRecursion;
	}

	private void _removeComponent(ICircuitGate g) {
		componentList.remove(g);
	}
}