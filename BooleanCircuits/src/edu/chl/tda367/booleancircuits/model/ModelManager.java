package edu.chl.tda367.booleancircuits.model;

import java.beans.*;
import java.util.*;

/**
 * A class which manages Models as workspaces.
 * @author Kaufmann
 */
public final class ModelManager {
	
	private List<Model> modelList;
	private Model activeWorkspace;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	/**
	 * Creates an instance of ModelManager.
	 */
	public ModelManager(){
		modelList = new ArrayList<Model>();
		activeWorkspace = null;
	}
	
	/**
	 * Creates a new workspace and makes it active.
	 */
	public void newWorkspace(){
		addWorkspace(new Model());
		firePropertyChanged();
	}
	
	/**
	 * Adds a new workspace and makes it active.
	 * @param workspace the workspace to be added
	 */
	public void addWorkspace(Model workspace){
		modelList.add(workspace);
		activeWorkspace = modelList.get(modelList.size()-1);
	}
	
	/**
	 * Closes the active workspace.
	 */
	public void closeActiveWorkspace(){
		if(activeWorkspace == null){
			throw new UnsupportedOperationException();
		} else {
			modelList.remove(activeWorkspace);
		}
			
		//TODO: THROW BETTER EXCEPTION.
	}
	/**
	 * Returns the active workspace.
	 * @return the active workspace to return.
	 */
	public Model getActiveWorkspace(){
		return activeWorkspace;
	}
	
	/**
	 * Returns all workspaces.
	 * @return list of all workspaces.
	 */
	public List<Model> getWorkspaces(){
		return modelList;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	private void firePropertyChanged(){
		pcs.firePropertyChange(new PropertyChangeEvent(this, "ModelManager", 0, 0));
	}
}
