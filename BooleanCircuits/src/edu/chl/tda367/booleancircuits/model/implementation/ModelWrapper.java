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
	private boolean isChanged;
	private static int nWrappers = 0;
	private int wrapperId;
	
	public ModelWrapper(){
		model = new Model();
		wrapperId = ++nWrappers;
	}
	
	public ModelWrapper(File file){
		this();
		this.file = file;
	}
	
	public ModelWrapper(File file, Model model){
		this(file);
		this.model = model;
		wrapperId = ++nWrappers;
	}
	
	@Override
	public void addComponent(ICircuitGate component, Point position) {
		model.addComponent(component, position);
		isChanged = true;
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
		isChanged = true;
	}

	@Override
	public void removeComponent(ICircuitGate g) {
		model.removeComponent(g);	
		isChanged = true;
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
		return isChanged;
	}
	
	
	
	@Override
	public String toString(){
		String retString = "";
		if(isChanged){
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
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	@Override
	public void setFile(File file){
		this.file = file;
	}

	@Override
	public void clock() {
		model.clock();
	}

	@Override
	public void addComponents(Collection<ICircuitGate> components) {
		model.addComponents(components);
	}

	@Override
	public int getNumberOfComponents() {
		return model.getNumberOfComponents();
	}
}
