package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.components.*;
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
	public void addComponent(final ICircuitGate component, final Point position) {
		component.setPosition(position);
		componentList.add(component);
	}

	@Override
	public void addComponents(final Collection<ICircuitGate> components) {
		componentList.addAll(components);
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
	public ICircuitGate getComponent(final Point position) {
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
	public Collection<ICircuitGate> getComponents() {
		return Collections.unmodifiableCollection(componentList);
	}

	@Override
	public int getNumberOfComponents() {
		return componentList.size();
	}

	@Override
	public boolean hasInfiniteRecursion() {
		return infiniteRecursion;
	}

	@Override
	public void removeComponent(final ICircuitGate g) {
		_removeComponent(g);
		updateComponents();
	}

	@Override
	public void removeComponents(final Collection<ICircuitGate> list) {
		for (ICircuitGate gate : list) {
			_removeComponent(gate);
		}
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
				List<ICircuitGate> cloneList = new ArrayList<ICircuitGate>();
				for (ICircuitGate g : l) {
					ICircuitGate temp = g.clone();
					if (temp.update()) {
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

	private void _removeComponent(final ICircuitGate g) {
		componentList.remove(g);
	}
}