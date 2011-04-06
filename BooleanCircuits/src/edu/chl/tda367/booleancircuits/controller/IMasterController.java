package edu.chl.tda367.booleancircuits.controller;

/**
 * 
 * @author Kaufmann
 * Interface for the main controller in BooleanCircuits
 */
public interface IMasterController {
	// Workspaces
	public abstract void closeActiveWorkspace();
	public abstract void newWorkspace();
	public abstract void openWorkspace(String path);
	public abstract void saveActiveWorkspace(String path);
	public abstract void saveActiveWorskpaceAsComponent(String path);
	public abstract void saveAllWorkspaces();
	
	// Component
	public abstract void addComponent(int x, int y);
	public abstract void removeSelectedComponents();
	public abstract void selectAllComponents();
	public abstract void selectComponent(int x, int y);
	
	// CutCopyPaste
	public abstract void copySelectedComponents();
	public abstract void cutSelectedComponents();
	public abstract void pasteSelectedComponent();
}