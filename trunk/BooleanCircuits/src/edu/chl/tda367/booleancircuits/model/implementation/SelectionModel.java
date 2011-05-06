package edu.chl.tda367.booleancircuits.model.implementation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * A class that manages what components are selected.
 * 
 * @author Boel
 * 
 */
public class SelectionModel implements ISelectionModel {

	private final List<IAbstractCircuitGate> selectedComponentList;

	public SelectionModel() {
		selectedComponentList = new LinkedList<IAbstractCircuitGate>();
	}

	@Override
	public void selectAllComponents(Collection<IAbstractCircuitGate> c) {
		for (IAbstractCircuitGate gate : c) {
			selectedComponentList.add(gate);
		}
	}

	@Override
	public void selectComponent(IAbstractCircuitGate g) {
		selectedComponentList.add(g);
	}

	@Override
	public boolean isSelectedComponent(IAbstractCircuitGate g) {
		if (selectedComponentList.contains(g)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeUnusedElements() {
		selectedComponentList.remove(null);
	}

	@Override
	public List<IAbstractCircuitGate> getSelectedComponents() {
		return selectedComponentList;
	}

}
