package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.IObservable;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public final class ModelManager implements IObservable, IModelManager {

	private ArrayList<IModel> modelList;
	private int selectedIndex;
	private static int workspaceCount = 1;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private ArrayList<ISelectionModel> selectionModelList;

	public ModelManager() {
		modelList = new ArrayList<IModel>();
		selectionModelList = new ArrayList<ISelectionModel>();
		selectedIndex = -1;
	}

	public void newWorkspace() {
		addWorkspace(new Model("Untitled " + workspaceCount));
	}

	public void addWorkspace(IModel workspace) {
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

	public IModel getActiveWorkspaceModel() {
		return _getActiveWorkspaceModel();
	}

	public ArrayList<IModel> getWorkspaces() {
		return modelList;
	}

	public void addComponent(IAbstractCircuitGate component, Point position) {
		getActiveWorkspaceModel().addComponent(component, position);
		firePropertyChanged();
	}

	public void removeSelectedComponents() {
		getActiveWorkspaceModel().removeComponents(
				selectionModelList.get(selectedIndex).getSelectedComponents());
		_getActiveSelectionModel().removeUnusedElements();
		firePropertyChanged();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
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
	public void selectAllComponents() {
		_getActiveSelectionModel().selectAllComponents(
				modelList.get(selectedIndex).getComponents());
		firePropertyChanged();
	}

	@Override
	public void selectComponent(Point position) {
		_getActiveSelectionModel().selectComponent(
				modelList.get(selectedIndex).getComponent(position));
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
		firePropertyChanged();
	}
	
	private IModel _getActiveWorkspaceModel(){
		return modelList.get(selectedIndex);
	}
	
	private ISelectionModel _getActiveSelectionModel(){
		return selectionModelList.get(selectedIndex);
	}
}
