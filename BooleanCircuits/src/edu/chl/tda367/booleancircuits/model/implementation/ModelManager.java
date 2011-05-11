package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
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

	public void newWorkspace() {
		addWorkspace(new ModelWrapper());
	}

	public void addWorkspace(IModelWrapper workspace) {
		modelList.add(workspace);
		selectionModelList.add(new SelectionModel());
		_setActiveWorkspace(modelList.size() - 1);
		workspaceCount++;
	}

	public void closeActiveWorkspace() {
		if (selectedIndex < 0 || selectedIndex >= modelList.size()) {
			return;
		} else {
			removeModel(selectedIndex);
			firePropertyChanged();
		}
	}

	public void closeAllWorkspaces() {
		modelList.removeAll(modelList);
		selectionModelList.removeAll(selectionModelList);
		_setActiveWorkspace(-1);
	}

	public void closeWorkspace(int i) {
		removeModel(i);
	}

	public void setActiveWorkspace(int i) {
		_setActiveWorkspace(i);
	}

	public int getActiveWorkspaceIndex() {
		return selectedIndex;
	}

	public IModelWrapper getActiveWorkspaceModel() {
		return _getActiveWorkspaceModel();
	}

	public ArrayList<IModelWrapper> getWorkspaces() {
		return modelList;
	}

	public void addComponent(IAbstractCircuitGate component, Point position) {
		_addComponent(component, position);
		firePropertyChanged();
	}

	public void removeSelectedComponents() {
		getActiveWorkspaceModel().removeComponents(
				selectionModelList.get(selectedIndex).getSelectedComponents());
		_getActiveSelectionModel().removeUnusedElements();
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

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
	public void removeComponent(IAbstractCircuitGate g) {
		getActiveWorkspaceModel().removeComponent(g);
		_getActiveSelectionModel().removeUnusedElements();
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}


	@Override
	public void connectComponents(IAbstractCircuitGate componentIn,
			IAbstractCircuitGate componentOut, int portIn, int portOut) {
		componentIn.connectInput(portIn, componentOut, portOut);
		getActiveWorkspaceModel().updateComponents();
		firePropertyChanged();
	}

	@Override
	public void manualPropertyChanged() {
		firePropertyChanged();
	}

	@Override
	public void addComponents(List<IAbstractCircuitGate> component) {
		for (int i = 0; i < component.size(); i++) {
			Point tempPos = new Point(component.get(i).getPosition());
			tempPos.x -= Constants.componentSize;
			tempPos.y -= Constants.componentSize;
			_addComponent(component.get(i), tempPos);
		}
		firePropertyChanged();
	}

	@Override
	public void addComponents(List<IAbstractCircuitGate> component,
			Point position) {
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

		int deltaX =(maxX - minX)/2 +minX;
		int deltaY =(maxY - minY)/2 +minY;

		int moveX = (position.x - deltaX);
		int moveY = (position.y - deltaY);

		for (int i = 0; i < component.size(); i++) {
			IAbstractCircuitGate gate = component.get(i);
			Point temp = new Point(gate.getPosition());
			temp.x += moveX;
			temp.y += moveY;
			_addComponent(gate, temp);
		}
		firePropertyChanged();
	}
	
	@Override
	public void clockActiveModel() {
		_getActiveWorkspaceModel().clock();
	}
	
	private void _addComponent(IAbstractCircuitGate component, Point position) {
		getActiveWorkspaceModel().addComponent(component, position);
	}
	
	private IModelWrapper _getActiveWorkspaceModel() {
		return modelList.get(selectedIndex);
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
}
