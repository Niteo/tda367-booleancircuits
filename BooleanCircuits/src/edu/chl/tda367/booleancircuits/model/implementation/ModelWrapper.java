package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.io.File;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

public final class ModelWrapper implements IModelWrapper {

	private static int nWrappers = 0;
	private File file;
	private boolean isChanged;
	private IModel model;
	private int wrapperId;

	public ModelWrapper() {
		model = new Model();
		wrapperId = ++nWrappers;
	}

	public ModelWrapper(final File file) {
		this();
		this.file = file;
	}

	public ModelWrapper(final File file, final Model model) {
		this(file);
		this.model = model;
		wrapperId = ++nWrappers;
	}

	@Override
	public void addComponent(final IGateWrapper component, final Point position) {
		model.addComponent(component, position);
		isChanged = true;
	}

	@Override
	public void addComponents(final Collection<IGateWrapper> components) {
		model.addComponents(components);
		isChanged = true;
	}

	@Override
	public void clock() {
		model.clock();
	}

	@Override
	public IGateWrapper getComponent(final Point position) {
		return model.getComponent(position);
	}

	@Override
	public Collection<IGateWrapper> getComponents() {
		return model.getComponents();
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public int getNumberOfComponents() {
		return model.getNumberOfComponents();
	}

	@Override
	public boolean hasChanged() {
		return isChanged;
	}

	@Override
	public boolean hasFile() {
		return file != null;
	}

	@Override
	public boolean hasInfiniteRecursion() {
		return model.hasInfiniteRecursion();
	}

	@Override
	public void removeComponent(final IGateWrapper g) {
		model.removeComponent(g);
		isChanged = true;
	}

	@Override
	public void removeComponents(final Collection<IGateWrapper> list) {
		model.removeComponents(list);
		isChanged = true;
	}

	@Override
	public void setChanged(final boolean isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public void setFile(final File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		String retString = "";
		if (isChanged) {
			retString += "*";
		}
		if (file != null) {
			retString += file.getName();
		} else {
			retString += "Untitled " + wrapperId;
		}

		return retString;
	}

	@Override
	public void updateComponents() {
		model.updateComponents();
	}

	@Override
	public IGateWrapper getGateWrapper(ICircuitGate gate) {
		return model.getGateWrapper(gate);
	}
}
