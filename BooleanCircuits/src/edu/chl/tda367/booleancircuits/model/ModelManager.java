package edu.chl.tda367.booleancircuits.model;

import java.beans.*;
import java.util.*;

/**
 * A class which manages Models as workspaces.
 * 
 * @author Kaufmann
 */
public final class ModelManager implements IObservable {

	private List<Model> modelList;
	private int selectedIndex;
	private static int workspaceCount = 1;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Creates an instance of ModelManager.
	 */
	public ModelManager() {
		modelList = new ArrayList<Model>();
		selectedIndex = -1;
	}

	/**
	 * Creates a new workspace and makes it active.
	 */
	public void newWorkspace() {
		addWorkspace(new Model("Untitled " + workspaceCount));
	}

	/**
	 * Adds a new workspace and makes it active.
	 * 
	 * @param workspace
	 *            the workspace to be added
	 */
	public void addWorkspace(Model workspace) {
		modelList.add(workspace);
		_setActiveWorkspace(modelList.size() - 1);
		workspaceCount++;
	}

	/**
	 * Closes the active workspace.
	 */
	public void closeActiveWorkspace() {
		if (selectedIndex == -1) {
			throw new IllegalArgumentException();
		} else {
			removeModel(selectedIndex);
		}
	}

	/**
	 * Closes all workspaces.
	 */
	public void closeAllWorkspaces() {
		modelList.removeAll(modelList);
		firePropertyChanged();
	}

	/**
	 * Closes a specific workspace.
	 * 
	 * @param i
	 *            int index of the workspace
	 */
	public void closeWorkspace(int i) {
		modelList.remove(i);

		firePropertyChanged();
	}

	/**
	 * Sets the currently active workspace.
	 * 
	 * @param i
	 *            int index of the workspace
	 */
	public void setActiveWorkspace(int i) {
		_setActiveWorkspace(i);
	}

	/**
	 * Returns the index of the active workspace.
	 * 
	 * @return int index of the workspace
	 */
	public int getActiveWorkspaceIndex() {
		return selectedIndex;
	}

	/**
	 * Returns the active workspace model.
	 * 
	 * @return Model the active workspace to return.
	 */
	public Model getActiveWorkspaceModel() {
		return modelList.get(selectedIndex);
	}

	/**
	 * Returns all workspaces.
	 * 
	 * @return list of all workspaces.
	 */
	public List<Model> getWorkspaces() {
		return modelList;
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
			throw new IllegalArgumentException(
					"Index can't be less than zero or bigger than modelList");
		} else {
			modelList.remove(i);
			if(modelList.size()==0){
				selectedIndex=-1;
			}
			else if(selectedIndex>=modelList.size()){
				selectedIndex=modelList.size()-1;
			}
		}
	}
}
