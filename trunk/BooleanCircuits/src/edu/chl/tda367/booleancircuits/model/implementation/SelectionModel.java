package edu.chl.tda367.booleancircuits.model.implementation;

import java.util.*;

import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * A class that manages what components are selected.
 * 
 * @author Boel
 * 
 */
public final class SelectionModel implements ISelectionModel {

	private final List<IGateWrapper> selectedComponentList;

	public SelectionModel() {
		selectedComponentList = new LinkedList<IGateWrapper>();
	}

	@Override
	public int getNumberOfComponents() {
		return selectedComponentList.size();
	}

	@Override
	public List<IGateWrapper> getSelectedComponents() {
		return selectedComponentList;
	}

	@Override
	public boolean isSelectedComponent(final IGateWrapper g) {
		return _isSelected(g);
	}

	@Override
	public void removeComponent(final IGateWrapper g) {
		selectedComponentList.remove(g);
	}

	@Override
	public void removeComponents(final Collection<IGateWrapper> c) {
		selectedComponentList.removeAll(c);
	}

	@Override
	public void selectComponent(final IGateWrapper g, final boolean multiSelect) {
		if (_isSelected(g)) {
			selectedComponentList.remove(g);
		} else {
			_addComponent(g, multiSelect);
		}
	}

	@Override
	public void selectComponents(final Collection<IGateWrapper> c) {
		selectedComponentList.clear();
		for (IGateWrapper gate : c) {
			selectedComponentList.add(gate);
		}
	}

	private void _addComponent(final IGateWrapper g, final boolean multiSelect) {
		if (!multiSelect) {
			selectedComponentList.clear();
		}
		if (g != null) {
			selectedComponentList.add(g);
		}
	}

	private boolean _isSelected(final IGateWrapper g) {
		return selectedComponentList.contains(g);
	}
}
