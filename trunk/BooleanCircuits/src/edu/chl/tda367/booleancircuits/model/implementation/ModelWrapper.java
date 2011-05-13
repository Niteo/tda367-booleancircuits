package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.io.File;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

public final class ModelWrapper implements IModelWrapper{
	
	private IModel model;
	private File file;
	private boolean hasChanged;
	private static int nWrappers = 0;
	private int wrapperId;
	
	public ModelWrapper(){
		model = new Model();
		wrapperId = ++nWrappers;
	}
	
	public ModelWrapper(File file){
		model = new Model();
		this.file = file;
		wrapperId = ++nWrappers;
	}
	
	public ModelWrapper(File file, Model model){
		this.model = model;
		this.file = file;
		wrapperId = ++nWrappers;
		// TODO: nWrappers, duplicerad kod?
	}
	
	@Override
	public void addComponent(ICircuitGate component, Point position) {
		model.addComponent(component, position);
		hasChanged = true;
	}

	@Override
	public Collection<ICircuitGate> getComponents() {
		return model.getComponents();
	}

	@Override
	public ICircuitGate getComponent(Point position) {
		return model.getComponent(position);
	}

	@Override
	public void removeComponents(Collection<ICircuitGate> list) {
		model.removeComponents(list);
		hasChanged = true;
	}

	@Override
	public void removeComponent(ICircuitGate g) {
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
			retString += "Untitled " + wrapperId;
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

	@Override
	public void clock() {
		model.clock();
	}
}
