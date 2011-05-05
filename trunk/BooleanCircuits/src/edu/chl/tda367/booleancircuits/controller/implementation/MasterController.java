package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;


public final class MasterController implements IMasterController {
	
	private ModelManager mm = null;
	
	/**
	 * Returns an instance of a MasterController
	 * @param mm the ModelManager to control
	 * @throws NullPOinterException if mm is null
	 */
	public MasterController(ModelManager mm){
		if(mm == null){
			throw new NullPointerException("mm must not be null!");
		} else {
			this.mm = mm;
		}
	}
	
	public void closeActiveWorkspace(){
		mm.closeActiveWorkspace();
	}
	
	public void closeAllWorkspaces(){
		mm.closeAllWorkspaces();
	}
	
	public void closeWorkspace(int i){
		mm.closeWorkspace(i);
	}
	
	public void newWorkspace(){
		mm.newWorkspace();
	}
	
	public void openWorkspace(String path){
		throw new UnsupportedOperationException();
	}
	
	public void saveActiveWorkspace(){
		throw new UnsupportedOperationException();
	}
	
	public void saveActiveWorskpaceAsComponent(String path){
		throw new UnsupportedOperationException();
	}
	
	public void saveAllWorkspaces(){
		throw new UnsupportedOperationException();
	}
	
	public void setActiveWorkspace(int i){
		mm.setActiveWorkspace(i);
	}
	
	public void undo(){
		throw new UnsupportedOperationException();
	}
	
	public void redo(){
		throw new UnsupportedOperationException();
	}
	
	public void addComponent(AbstractCircuitGate component, Point position){
		mm.addComponent(component, position);
	}

	public void removeSelectedComponents(){
		mm.removeSelectedComponents();
	}
	
	public void selectAllComponents(){
		mm.selectAllComponents();
	}
	
	public void selectComponent(Point position){
		mm.selectComponent(position);
	}
	
	public void copySelectedComponents(){
		throw new UnsupportedOperationException();
	}
	
	public void cutSelectedComponents(){
		throw new UnsupportedOperationException();
	}
	
	public void pasteSelectedComponent(Point position){
		mm.paste(position);
	}
}