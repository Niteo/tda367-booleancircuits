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
public final class SelectionModel implements ISelectionModel {

	private final List<IAbstractCircuitGate> selectedComponentList;

	public SelectionModel() {
		selectedComponentList = new LinkedList<IAbstractCircuitGate>();
	}

	@Override
	public void selectComponents(Collection<IAbstractCircuitGate> c) {
		for (IAbstractCircuitGate gate : c) {
			selectedComponentList.add(gate);
		}
		_removeUnusedElements();
	}

	@Override
	public void selectComponent(IAbstractCircuitGate g, boolean multiSelect) {
		if(multiSelect){
			if(_isSelected(g)){
				selectedComponentList.remove(g);
			} else {
				selectedComponentList.add(g);
			}
		} else {
			selectedComponentList.clear();
			selectedComponentList.add(g);
		}
		_removeUnusedElements();
	}

	@Override
	public boolean isSelectedComponent(IAbstractCircuitGate g) {
		return _isSelected(g);
	}
	
	private boolean _isSelected(IAbstractCircuitGate g){
		if (selectedComponentList.contains(g)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeUnusedElements() {
		_removeUnusedElements();
	}

	@Override
	public List<IAbstractCircuitGate> getSelectedComponents() {
		return selectedComponentList;
	}
	
	private void _removeUnusedElements(){
		selectedComponentList.remove(null);
	}

}
