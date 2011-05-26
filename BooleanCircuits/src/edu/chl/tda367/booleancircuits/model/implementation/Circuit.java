package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.ICircuit;
import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

/**
 * Model for managing and updating circuit components.
 */
public final class Circuit implements ICircuit {
	private final Collection<IGateWrapper> componentList;
	private boolean infiniteRecursion = false;

	/**
	 * Returns an instance of Circuit
	 * 
	 */
	public Circuit() {
		componentList = new ArrayList<IGateWrapper>();
	}

	@Override
	public boolean addComponent(final IGateWrapper component, final Point position) {
		component.setPosition(position);
		return componentList.add(component);
	}

	@Override
	public boolean addComponents(final Collection<IGateWrapper> components) {
		return componentList.addAll(components);
	}

	@Override
	public void clock() {
		for (IGateWrapper gate : componentList) {
			if (gate.getGate() instanceof Clock) {
				((Clock) gate.getGate()).toggleClock();
			}
		}
		updateComponents();
	}

	@Override
	public IGateWrapper getComponent(final Point position) {
		int size = Constants.componentSize;
		for (IGateWrapper acg : componentList) {
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
	public Collection<IGateWrapper> getComponents() {
		return Collections.unmodifiableCollection(componentList);
	}

	@Override
	public IGateWrapper getGateWrapper(final ICircuitGate gate) {
		for (IGateWrapper g : componentList) {
			if (g.getGate() == gate) {
				return g;
			}
		}
		return null;
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
	public boolean removeComponent(final IGateWrapper g) {
		boolean ret = _removeComponent(g);
		updateComponents();
		return ret;
	}

	@Override
	public boolean removeComponents(final Collection<IGateWrapper> list) {
		boolean ret = false;
		for (IGateWrapper gate : list) {
			if(_removeComponent(gate)){
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public void updateComponents() {
		List<List<IGateWrapper>> groupList = new ArrayList<List<IGateWrapper>>();
		infiniteRecursion = false;

		// Remove unconnected gate connections
		for (IGateWrapper iGate : componentList) {
			for (IGateInput input : iGate.getInputs()) {
				if (!componentList.contains(getGateWrapper(input
						.getInputComponent()))) {
					input.reset();
				}
			}
		}

		// Prepare tiers
		int maxTier = 0;
		for (IGateWrapper iGate : componentList) {
			int tier = iGate.getComponentTier();
			if (tier > maxTier) {
				// Add a list per each tier jump made
				for (int i = maxTier; i < tier; i++) {
					groupList.add(new LinkedList<IGateWrapper>());
				}
				maxTier = tier;
			}
		}

		// Sort components into tiers
		for (IGateWrapper iGate : componentList) {
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
					if (!groupList.get(recouplesMinTier - 1).contains(
							getGateWrapper(addGate))) {
						groupList.get(recouplesMinTier - 1).add(
								getGateWrapper(addGate));
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
			for (List<IGateWrapper> l : groupList) {
				List<ICircuitGate> cloneList = new ArrayList<ICircuitGate>();
				for (IGateWrapper g : l) {
					ICircuitGate temp = g.getGateClone();
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

	private boolean _removeComponent(final ICircuitGate g) {
		return componentList.remove(g);
	}
}