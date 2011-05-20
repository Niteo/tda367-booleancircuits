package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.*;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public final class ModelManager implements IObservable, IModelManager {

	private static int workspaceCount = 1;
	private ArrayList<IModelWrapper> modelList;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private int selectedIndex;
	private ArrayList<ISelectionModel> selectionModelList;

	public ModelManager() {
		modelList = new ArrayList<IModelWrapper>();
		selectionModelList = new ArrayList<ISelectionModel>();
		selectedIndex = -1;
	}

	@Override
	public void addComponent(final ICircuitGate component, final Point position) {
		_addComponent(component, position);
		firePropertyChanged();
	}

	@Override
	public void addComponents(final List<ICircuitGate> components) {
		if (getActiveWorkspaceModel() != null) {
			getActiveWorkspaceModel().addComponents(components);
			firePropertyChanged();
		}
	}

	@Override
	public void addComponents(final List<ICircuitGate> component,
			final Point position) {
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (int i = 0; i < component.size(); i++) {
			Point p = component.get(i).getPosition();
			if (p.x > maxX) {
				maxX = p.x;
			}
			if (p.x < minX) {
				minX = p.x;
			}
			if (p.y > maxY) {
				maxY = p.y;
			}
			if (p.y < minY) {
				minY = p.y;
			}
		}

		int deltaX = (maxX - minX) / 2 + minX;
		int deltaY = (maxY - minY) / 2 + minY;

		int moveX = (position.x - deltaX);
		int moveY = (position.y - deltaY);

		for (int i = 0; i < component.size(); i++) {
			ICircuitGate gate = component.get(i);
			Point temp = new Point(gate.getPosition());
			temp.x += moveX;
			temp.y += moveY;
			gate.setPosition(temp);
		}
		this._getActiveWorkspaceModel().addComponents(component);
		firePropertyChanged();
	}

	@Override
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void addWorkspace(final IModelWrapper workspace) {
		modelList.add(workspace);
		selectionModelList.add(new SelectionModel());
		_setActiveWorkspace(modelList.size() - 1);
		workspaceCount++;
	}

	@Override
	public void clockActiveModel() {
		if (modelList.size() > 0) {
			_getActiveWorkspaceModel().clock();
		}
		firePropertyChanged();
	}

	@Override
	public void closeActiveWorkspace() {
		if (selectedIndex < 0 || selectedIndex >= modelList.size()) {
			return;
		} else {
			removeModel(selectedIndex);
			firePropertyChanged();
		}
	}

	@Override
	public void closeAllWorkspaces() {
		modelList.clear();
		selectionModelList.clear();
		_setActiveWorkspace(-1);
	}

	@Override
	public void closeWorkspace(final int i) {
		removeModel(i);
	}

	@Override
	public void connectComponents(final ICircuitGate componentIn,
			final ICircuitGate componentOut, final int portIn, final int portOut) {
		if (_getActiveWorkspaceModel() != null) {
			componentIn.connectInput(portIn, componentOut, portOut);
			_getActiveWorkspaceModel().updateComponents();
			firePropertyChanged();
		}
	}

	@Override
	public ISelectionModel getActiveSelectionModel() {
		return _getActiveSelectionModel();
	}

	@Override
	public int getActiveWorkspaceIndex() {
		return selectedIndex;
	}

	@Override
	public IModelWrapper getActiveWorkspaceModel() {
		return _getActiveWorkspaceModel();
	}

	@Override
	public IModelWrapper getWorkspace(final int i) {
		return modelList.get(i);
	}

	@Override
	public ArrayList<IModelWrapper> getWorkspaces() {
		return modelList;
	}

	@Override
	public boolean isSelectedComponent(final ICircuitGate g) {
		if (_getActiveSelectionModel() != null) {
			return _getActiveSelectionModel().isSelectedComponent(g);
		}
		return false;
	}

	@Override
	public void manualPropertyChanged() {
		firePropertyChanged();
	}

	@Override
	public void newWorkspace() {
		addWorkspace(new ModelWrapper());
	}

	@Override
	public void removeComponent(final ICircuitGate g) {
		if (_getActiveWorkspaceModel() != null) {
			_getActiveWorkspaceModel().removeComponent(g);
			_getActiveSelectionModel().removeComponent(g);
			_getActiveWorkspaceModel().updateComponents();
			firePropertyChanged();
		}
	}

	@Override
	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void removeSelectedComponents() {
		if (selectedIndex >= 0 && selectedIndex < selectionModelList.size()) {
			_getActiveWorkspaceModel().removeComponents(
					selectionModelList.get(selectedIndex)
							.getSelectedComponents());
			_getActiveSelectionModel().removeComponents(
					selectionModelList.get(selectedIndex)
							.getSelectedComponents());
			_getActiveWorkspaceModel().updateComponents();
			firePropertyChanged();
		}
	}

	@Override
	public void selectAllComponents() {
		if (selectedIndex >= 0 && selectedIndex < modelList.size()) {
			_getActiveSelectionModel().selectComponents(
					modelList.get(selectedIndex).getComponents());
			firePropertyChanged();
		}
	}

	@Override
	public void selectComponent(final Point position, final boolean multiSelect) {
		if (selectedIndex >= 0 && selectedIndex < modelList.size()) {
			_getActiveSelectionModel().selectComponent(
					modelList.get(selectedIndex).getComponent(position),
					multiSelect);
			firePropertyChanged();
		}
	}

	@Override
	public void selectComponents(final Point pos1, final Point pos2) {
		List<ICircuitGate> selectedComponents = new ArrayList<ICircuitGate>();

		for (ICircuitGate gate : _getActiveWorkspaceModel().getComponents()) {
			Point gatePosition = gate.getPosition();
			if (gatePosition.x >= Math.min(pos1.x, pos2.x)
					&& gatePosition.x <= Math.max(pos1.x, pos2.x)
					&& gatePosition.y >= Math.min(pos1.y, pos2.y)
					&& gatePosition.y <= Math.max(pos1.y, pos2.y)) {
				selectedComponents.add(gate);
			}
		}
		this.getActiveSelectionModel().selectComponents(selectedComponents);
	}

	@Override
	public void setActiveWorkspace(final int i) {
		_setActiveWorkspace(i);
	}

	private void _addComponent(final ICircuitGate component,
			final Point position) {
		if (_getActiveWorkspaceModel() != null) {
			_getActiveWorkspaceModel().addComponent(component, position);
			_getActiveWorkspaceModel().updateComponents();
		}
	}

	private ISelectionModel _getActiveSelectionModel() {
		if (selectedIndex >= 0 && selectedIndex < selectionModelList.size()) {
			return selectionModelList.get(selectedIndex);
		} else {
			return null;
		}
	}

	private IModelWrapper _getActiveWorkspaceModel() {
		if (selectedIndex >= 0 && selectedIndex < modelList.size()) {
			return modelList.get(selectedIndex);
		} else {
			return null;
		}
	}

	private void _setActiveWorkspace(final int i) {
		selectedIndex = i;
		firePropertyChanged();
	}

	@SuppressWarnings("boxing")
	private void firePropertyChanged() {
		pcs.firePropertyChange(new PropertyChangeEvent(this, "ModelManager", 0,
				1));
	}

	private void removeModel(final int i) {
		if (i < 0 || i >= modelList.size()) {
			return;
		} else {
			modelList.remove(i);
			selectionModelList.remove(i);
			if (modelList.size() == 0) {
				selectedIndex = -1;
			} else if (selectedIndex >= modelList.size()) {
				selectedIndex = modelList.size() - 1;
			}
			firePropertyChanged();
		}
	}
}
