package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.IObservable;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public final class ModelManager implements IObservable, IModelManager {

	private ArrayList<IModelWrapper> modelList;
	private int selectedIndex;
	private static int workspaceCount = 1;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private ArrayList<ISelectionModel> selectionModelList;

	public ModelManager() {
		modelList = new ArrayList<IModelWrapper>();
		selectionModelList = new ArrayList<ISelectionModel>();
		selectedIndex = -1;
	}

	@Override
	public void newWorkspace() {
		addWorkspace(new ModelWrapper());
	}

	@Override
	public void addWorkspace(IModelWrapper workspace) {
		modelList.add(workspace);
		selectionModelList.add(new SelectionModel());
		_setActiveWorkspace(modelList.size() - 1);
		workspaceCount++;
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
		modelList.removeAll(modelList);
		selectionModelList.removeAll(selectionModelList);
		_setActiveWorkspace(-1);
	}

	@Override
	public void closeWorkspace(int i) {
		removeModel(i);
	}

	@Override
	public void setActiveWorkspace(int i) {
		_setActiveWorkspace(i);
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
	public ArrayList<IModelWrapper> getWorkspaces() {
		return modelList;
	}

	@Override
	public void addComponent(ICircuitGate component, Point position) {
		_addComponent(component, position);
		firePropertyChanged();
	}

	@Override
	public void removeSelectedComponents() {
		getActiveWorkspaceModel().removeComponents(
				selectionModelList.get(selectedIndex).getSelectedComponents());
		_getActiveSelectionModel().removeUnusedElements();
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void selectAllComponents() {
		_getActiveSelectionModel().selectComponents(
				modelList.get(selectedIndex).getComponents());
		firePropertyChanged();
	}

	@Override
	public void selectComponent(Point position, boolean multiSelect) {
		_getActiveSelectionModel().selectComponent(
				modelList.get(selectedIndex).getComponent(position),
				multiSelect);
		firePropertyChanged();
	}

	@Override
	public boolean isSelectedComponent(AbstractCircuitGate g) {
		return _getActiveSelectionModel().isSelectedComponent(g);
	}

	@Override
	public ISelectionModel getActiveSelectionModel() {
		return _getActiveSelectionModel();
	}

	@Override
	public void removeComponent(ICircuitGate g) {
		getActiveWorkspaceModel().removeComponent(g);
		_getActiveSelectionModel().removeUnusedElements();
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}

	@Override
	public void connectComponents(ICircuitGate componentIn,
			ICircuitGate componentOut, int portIn, int portOut) {
		componentIn.connectInput(portIn, componentOut, portOut);
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}

	@Override
	public void manualPropertyChanged() {
		firePropertyChanged();
	}

	@Override
	public void addComponents(List<ICircuitGate> component) {
		for (int i = 0; i < component.size(); i++) {
			Point tempPos = new Point(component.get(i).getPosition());
			tempPos.x -= Constants.componentSize;
			tempPos.y -= Constants.componentSize;
			_addComponent(component.get(i), tempPos);
		}
		firePropertyChanged();
	}

	@Override
	public void addComponents(List<ICircuitGate> component, Point position) {
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
			_addComponent(gate, temp);
		}
		firePropertyChanged();
	}

	@Override
	public void clockActiveModel() {
		if (modelList.size() > 0) {
			_getActiveWorkspaceModel().clock();
		}
		firePropertyChanged();
	}

	private void _addComponent(ICircuitGate component, Point position) {
		getActiveWorkspaceModel().addComponent(component, position);
		getActiveWorkspaceModel().updateComponents();
	}

	private IModelWrapper _getActiveWorkspaceModel() {
		if (modelList.size() > 0) {
			return modelList.get(selectedIndex);
		} else {
			return null;
		}
	}

	private ISelectionModel _getActiveSelectionModel() {
		return selectionModelList.get(selectedIndex);
	}

	private void firePropertyChanged() {
		pcs.firePropertyChange(new PropertyChangeEvent(this, "ModelManager", 0,
				1));
	}

	private void _setActiveWorkspace(int i) {
		selectedIndex = i;
		firePropertyChanged();
	}

	private void removeModel(int i) {
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

	@Override
	public void selectComponents(Point pos1, Point pos2) {
		List<ICircuitGate> selectedComponents = new ArrayList<ICircuitGate>();
		Point start = new Point(Math.min(pos1.x, pos2.x), Math.min(pos1.y,
				pos2.y));
		Point end = new Point(Math.max(pos1.x, pos2.x),
				Math.max(pos1.y, pos2.y));

		for (ICircuitGate gate : this.getActiveWorkspaceModel().getComponents()) {
			Point gatePosition = gate.getPosition();
			if (gatePosition.x >= start.x && gatePosition.x <= end.x
					&& gatePosition.y >= start.y && gatePosition.y <= end.y) {
				selectedComponents.add(gate);
			}
		}
		this.getActiveSelectionModel().selectComponents(selectedComponents);
	}
}