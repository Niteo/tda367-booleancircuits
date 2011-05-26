package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.io.File;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.*;

public final class CircuitWrapper implements ICircuitWrapper {

	private static int nWrappers = 0;
	private File file;
	private boolean isChanged;
	private ICircuit model;
	private int wrapperId;

	public CircuitWrapper() {
		model = new Circuit();
		wrapperId = ++nWrappers;
	}

	public CircuitWrapper(final File file) {
		this();
		this.file = file;
	}

	public CircuitWrapper(final File file, final Circuit model) {
		this(file);
		this.model = model;
		wrapperId = ++nWrappers;
	}

	@Override
	public boolean addComponent(final IGateWrapper component, final Point position) {
		if(model.addComponent(component, position)){
			return isChanged = true;
		}
		return false;
	}

	@Override
	public boolean addComponents(final Collection<IGateWrapper> components) {
		if(model.addComponents(components)){
			return isChanged = true;
		}
		return false;
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
	public IGateWrapper getGateWrapper(final ICircuitGate gate) {
		return model.getGateWrapper(gate);
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
	public boolean removeComponent(final IGateWrapper g) {
		if(model.removeComponent(g)){
			isChanged = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeComponents(final Collection<IGateWrapper> list) {
		if(model.removeComponents(list)){
			isChanged = true;
			return true;
		}
		return false;
	}

	@Override
	public void setChanged(final boolean isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public void setFile(final File file) {
		this.file = file;
	}

	/**
	 * Returns the wrapper's corresponding filename. If no file is set, it will
	 * return untitled X, where X is a unique identifier for the circuit.
	 * 
	 * @return a string representation of this circuit's name.
	 */
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
}
