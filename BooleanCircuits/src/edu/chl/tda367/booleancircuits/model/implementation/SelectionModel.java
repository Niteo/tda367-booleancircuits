package edu.chl.tda367.booleancircuits.model.implementation;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

/**
 * A class that manages what components are selected.
 * 
 * @author Boel
 * 
 */
public final class SelectionModel implements ISelectionModel {

	private final List<ICircuitGate> selectedComponentList;

	public SelectionModel() {
		selectedComponentList = new LinkedList<ICircuitGate>();
	}

	@Override
	public int getNumberOfComponents() {
		return selectedComponentList.size();
	}

	@Override
	public List<ICircuitGate> getSelectedComponents() {
		return selectedComponentList;
	}

	@Override
	public boolean isSelectedComponent(final ICircuitGate g) {
		return _isSelected(g);
	}

	@Override
	public void removeComponent(final ICircuitGate g) {
		selectedComponentList.remove(g);
	}

	@Override
	public void removeComponents(final Collection<ICircuitGate> c) {
		selectedComponentList.removeAll(c);
	}

	@Override
	public void selectComponent(final ICircuitGate g, final boolean multiSelect) {
		if (multiSelect) {
			if (_isSelected(g)) {
				selectedComponentList.remove(g);
			} else {
				selectedComponentList.add(g);
			}
		} else {
			selectedComponentList.clear();
			selectedComponentList.add(g);
		}
	}

	@Override
	public void selectComponents(final Collection<ICircuitGate> c) {
		selectedComponentList.clear();
		for (ICircuitGate gate : c) {
			selectedComponentList.add(gate);
		}
	}

	private boolean _isSelected(final ICircuitGate g) {
		if (selectedComponentList.contains(g)) {
			return true;
		} else {
			return false;
		}
	}
}
