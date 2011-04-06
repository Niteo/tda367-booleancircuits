package edu.chl.tda367.booleancircuits.model;

import java.util.*;

/**
 * A class which manages Models as workspaces.
 * @author Kaufmann
 */
public final class ModelManager {
	
	private List<Model> modelList;
	private Model activeWorkspace;
	
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
	 * Adds a new workspace and makes it active.
	 * @param workspace the workspace to be added
	 */
	public void addWorkspace(Model workspace){
		modelList.add(workspace);
		activeWorkspace = modelList.get(modelList.size());
	}
}
