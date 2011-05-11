package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.ClipboardManager;

public final class MasterController implements IMasterController {

	private IModelManager mm = null;
	private IAbstractCircuitGate connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private IAbstractCircuitGate chosenGate;
	private IClipboardManager clipboardManager = new ClipboardManager();

	/**
	 * Returns an instance of a MasterController
	 * 
	 * @param mm
	 *            the ModelManager to control
	 * @throws NullPointerException
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
	public void openWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			IModelWrapper workspace = fileManager.openFile(fc.getSelectedFile());
			workspace.setChangedFalse();
			mm.addWorkspace(workspace);
		}
	}

	@Override
	public void saveActiveWorkspace(boolean saveAs) {
		if(saveAs){
			_saveWorkspaceAs(mm.getActiveWorkspaceModel());
		} else {
			_saveWorkspace(mm.getActiveWorkspaceModel());
		}
	}

	@Override
	public void saveAllWorkspaces() {
		for(IModelWrapper imw : mm.getWorkspaces()){
			_saveWorkspace(imw);
		}
	}
	
	private void _saveWorkspace(IModelWrapper imw){
		if(imw.getFile() != null){
			fileManager.saveFile(imw.getComponents(),
					imw.getFile());
			imw.setChangedFalse();
			mm.manualPropertyChanged();
		} else {
			_saveWorkspaceAs(imw);
		}
	}
	
	private void _saveWorkspaceAs(IModelWrapper imw){
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			fileManager.saveFile(imw.getComponents(),
					fc.getSelectedFile());
			imw.setFile(fc.getSelectedFile());
			imw.setChangedFalse();
			mm.manualPropertyChanged();
		}
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
		if (chosenGate != null) {
			mm.addComponent(chosenGate.clone(), position);
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
		_copySelectedComponents();
	}

	@Override
	public void cutSelectedComponents() {
		_copySelectedComponents();
		mm.removeSelectedComponents();
	}

	@Override
	public void pasteSelectedComponents() {
		mm.addComponents(clipboardManager.paste());
	}
	

	@Override
	public void pasteSelectedComponents(Point position) {
		mm.addComponents(clipboardManager.paste(), position);
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
		if (g == null) {
			connectComponent = null;
			return;
		} else if (connectComponent == null) {
			connectComponent = g;
			connectPort = port;
		} else {
			mm.connectComponents(connectComponent, g, connectPort, port);
			connectComponent = null;
		}
	}
	
	private void _copySelectedComponents() {
		clipboardManager.copy(mm.getActiveSelectionModel().getSelectedComponents());
	}
}