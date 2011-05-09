package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.io.File;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;

public final class ModelWrapper implements IModelWrapper{
	
	private IModel model;
	private File file;
	private boolean hasChanged;
	
	public ModelWrapper(){
		model = new Model();
	}
	
	public ModelWrapper(File file){
		model = new Model();
		this.file = file;
	}
	
	public ModelWrapper(File file, Model model){
		this.model = model;
		this.file = file;
	}
	
	@Override
	public void addComponent(IAbstractCircuitGate component, Point position) {
		model.addComponent(component, position);
		hasChanged = true;
	}

	@Override
	public Collection<IAbstractCircuitGate> getComponents() {
		return model.getComponents();
	}

	@Override
	public IAbstractCircuitGate getComponent(Point position) {
		return model.getComponent(position);
	}

	@Override
	public void removeComponents(Collection<IAbstractCircuitGate> list) {
		model.removeComponents(list);
		hasChanged = true;
	}

	@Override
	public void removeComponent(IAbstractCircuitGate g) {
		model.removeComponent(g);	
		hasChanged = true;
	}

	@Override
	public void updateComponents() {
		model.updateComponents();
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public boolean hasChanged() {
		return hasChanged;
	}
	
	
	
	@Override
	public String toString(){
		String retString = "";
		if(hasChanged){
			retString += "*";
		}
		if(file != null){
			retString += file.getName();
		} else {
			retString += "<Untitled>";
		}
		
		return retString;
	}

	@Override
	public boolean hasFile() {
		return file != null;
	}

	@Override
	public void setChangedFalse() {
		hasChanged = false;
	}
	
	@Override
	public void setFile(File file){
		this.file = file;
	}
}