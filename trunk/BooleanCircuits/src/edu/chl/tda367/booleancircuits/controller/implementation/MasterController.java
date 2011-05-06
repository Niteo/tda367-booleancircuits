package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;

public final class MasterController implements IMasterController {

	private IModelManager mm = null;
	private IAbstractCircuitGate connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private IAbstractCircuitGate chosenGate;

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
	public void addComponent(Point position) {
		if(chosenGate != null){
			mm.addComponent(chosenGate, position);
		}
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
	public void selectComponent(Point position, boolean multiSelect) {
		mm.selectComponent(position, multiSelect);
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
	public void pasteSelectedComponents(Point position) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setChosenComponent(IAbstractCircuitGate g) {
		chosenGate = g.clone();
	}

	@Override
	public void removeComponent(IAbstractCircuitGate g) {
		mm.removeComponent(g);
	}

	@Override
	public void connectComponent(IAbstractCircuitGate g, int port) {
		if(g == null){
			connectComponent = null;
			return;
		}
		else if(connectComponent == null){
			connectComponent = g;
			connectPort = port;
		} else {
			mm.connectComponents(connectComponent,
					g, connectPort, port);
			connectComponent = null;
		}
		
	}
}