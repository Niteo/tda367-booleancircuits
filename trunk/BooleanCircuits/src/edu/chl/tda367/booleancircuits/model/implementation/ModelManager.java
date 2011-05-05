package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.IObservable;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public final class ModelManager implements IObservable, IModelManager {

	private List<Model> modelList;
	private int selectedIndex;
	private static int workspaceCount = 1;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public ModelManager() {
		modelList = new ArrayList<Model>();
		selectedIndex = -1;
	}

	public void newWorkspace() {
		addWorkspace(new Model("Untitled " + workspaceCount));
	}

	public void addWorkspace(Model workspace) {
		modelList.add(workspace);
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

	public Model getActiveWorkspaceModel() {
		return modelList.get(selectedIndex);
	}

	public List<Model> getWorkspaces() {
		return modelList;
	}
	
	public void addComponent(AbstractCircuitGate component, Point position){
		getActiveWorkspaceModel().addComponent(component, position);
		firePropertyChanged();
	}

	public void removeSelectedComponents(){
		getActiveWorkspaceModel().removeSelectedComponents();
		firePropertyChanged();
	}
	
	public void selectAllComponents(){
		getActiveWorkspaceModel().selectAllComponents();
		firePropertyChanged();
	}
	
	public void selectComponent(Point position){
		getActiveWorkspaceModel().selectComponent(position);
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
			if(modelList.size()==0){
				selectedIndex=-1;
			}
			else if(selectedIndex>=modelList.size()){
				selectedIndex=modelList.size()-1;
			}
			firePropertyChanged();
		}
	}
}
