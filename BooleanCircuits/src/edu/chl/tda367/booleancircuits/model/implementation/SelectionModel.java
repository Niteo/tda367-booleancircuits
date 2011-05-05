package edu.chl.tda367.booleancircuits.model.implementation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * A class that manages what components are selected.
 * 
 * @author Boel
 * 
 */
public class SelectionModel implements ISelectionModel {

	private final List<AbstractCircuitGate> selectedComponentList;

	public SelectionModel() {
		selectedComponentList = new LinkedList<AbstractCircuitGate>();
	}

	@Override
	public void selectAllComponents(Collection<AbstractCircuitGate> c) {
		for (AbstractCircuitGate gate : c) {
			selectedComponentList.add(gate);
		}
	}

	@Override
	public void selectComponent(AbstractCircuitGate g) {
		selectedComponentList.add(g);
	}

	@Override
	public boolean isSelectedComponent(AbstractCircuitGate g) {
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
	public List<AbstractCircuitGate> getSelectedComponents() {
		return selectedComponentList;
	}

}
