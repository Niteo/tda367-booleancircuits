package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public final class MasterController implements IMasterController {

	private ModelManager mm = null;
	private AbstractCircuitGate connectComponent = null;
	private FileManager fileManager;

	/**
	 * Returns an instance of a MasterController
	 * 
	 * @param mm
	 *            the ModelManager to control
	 * @throws NullPOinterException
	 *             if mm is null
	 */
	public MasterController(ModelManager mm) {
		if (mm == null) {
			throw new NullPointerException("mm must not be null!");
		} else {
			this.mm = mm;
			fileManager = new FileManager();
		}
	}

	@Override
	public void closeActiveWorkspace() {
		mm.closeActiveWorkspace();
	}

	@Override
	public void closeAllWorkspaces() {
		mm.closeAllWorkspaces();
	}

	@Override
	public void closeWorkspace(int i) {
		mm.closeWorkspace(i);
	}

	@Override
	public void newWorkspace() {
		mm.newWorkspace();
	}

	@Override
	public void openWorkspace(String path) {
		fileManager.openFile(path);
	}

	@Override
	public void saveActiveWorkspace() {
		fileManager.saveFile(mm.getActiveWorkspaceModel().getComponents(),
				"Penis hello!");
	}

	@Override
	public void saveActiveWorskpaceAsComponent(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveAllWorkspaces() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setActiveWorkspace(int i) {
		mm.setActiveWorkspace(i);
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addComponent(AbstractCircuitGate component, Point position) {
		mm.addComponent(component, position);
	}

	@Override
	public void removeSelectedComponents() {
		mm.removeSelectedComponents();
	}

	@Override
	public void selectAllComponents() {
		mm.selectAllComponents();
	}

	@Override
	public void selectComponent(Point position) {
		mm.selectComponent(position);
	}

	@Override
	public void copySelectedComponents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void cutSelectedComponents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pasteSelectedComponent(Point position) {
		throw new UnsupportedOperationException();
	}
}