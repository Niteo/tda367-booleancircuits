package edu.chl.tda367.booleancircuits.model.implementation;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.common.IObservable;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * A class which manages Models as workspaces.
 *
 * @author Kaufmann
 */
public final class CircuitManager implements IObservable, ICircuitManager {

	private static int workspaceCount = 1;
	private ArrayList<ICircuitWrapper> modelList;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private int selectedIndex;
	private ArrayList<ISelectionModel> selectionModelList;

	public CircuitManager() {
		modelList = new ArrayList<ICircuitWrapper>();
		selectionModelList = new ArrayList<ISelectionModel>();
		selectedIndex = -1;
	}

	@Override
	public void addComponent(final IGateWrapper component, final Point position) {
		_addComponent(component, position);
		firePropertyChanged();
	}

	@Override
	public void addComponents(final List<IGateWrapper> components) {
		ICircuitWrapper awm = _getActiveWorkspaceModel();
		if (awm != null) {
			awm.addComponents(components);
			firePropertyChanged();
		}
	}

	@Override
	public void addComponents(final List<IGateWrapper> component,
			final Point position) {
		ICircuitWrapper awm = _getActiveWorkspaceModel();
		if (awm != null) {
			int minX = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE;
			int minY = Integer.MAX_VALUE;
			int maxY = Integer.MIN_VALUE;

			for (int i = 0; i < component.size(); i++) {
				Point p = component.get(i).getPosition();
				if (p.x > maxX) {
					maxX = p.x;
				}
				if (p.x < minX) {
					minX = p.x;
				}
				if (p.y > maxY) {
					maxY = p.y;
				}
				if (p.y < minY) {
					minY = p.y;
				}
			}

			int deltaX = (maxX - minX) / 2 + minX;
			int deltaY = (maxY - minY) / 2 + minY;

			int moveX = (position.x - deltaX);
			int moveY = (position.y - deltaY);

			for (int i = 0; i < component.size(); i++) {
				IGateWrapper gate = component.get(i);
				Point temp = new Point(gate.getPosition());
				temp.x += moveX;
				temp.y += moveY;
				gate.setPosition(temp);
			}
			awm.addComponents(component);
			firePropertyChanged();
		}
	}

	@Override
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void addWorkspace(final ICircuitWrapper workspace) {
		modelList.add(workspace);
		selectionModelList.add(new SelectionModel());
		_setActiveWorkspace(modelList.size() - 1);
		workspaceCount++;
	}

	@Override
	public void clockActiveModel() {
		ICircuitWrapper w = _getActiveWorkspaceModel();
		if (w != null) {
			w.clock();
		}
		firePropertyChanged();
	}

	@Override
	public void closeActiveWorkspace() {
		removeModel(selectedIndex);
	}

	@Override
	public void closeAllWorkspaces() {
		modelList.clear();
		selectionModelList.clear();
		_setActiveWorkspace(-1);
	}

	@Override
	public void closeWorkspace(final int i) {
		removeModel(i);
	}

	@Override
	public void connectComponents(final IGateWrapper componentIn,
			final IGateWrapper componentOut, final int portIn, final int portOut) {
		if (_getActiveWorkspaceModel() != null) {
			componentIn.connectInput(portIn, componentOut.getGate(), portOut);
			_getActiveWorkspaceModel().updateComponents();
			firePropertyChanged();
		}
	}

	@Override
	public ISelectionModel getActiveSelectionModel() {
		return _getActiveSelectionModel();
	}

	@Override
	public int getActiveWorkspaceIndex() {
		return selectedIndex;
	}

	@Override
	public ICircuitWrapper getActiveWorkspaceModel() {
		return _getActiveWorkspaceModel();
	}

	@Override
	public ICircuitWrapper getWorkspace(final int i) {
		if(i >= 0 && i < modelList.size()){
			return modelList.get(i);
		}
		return null;
	}

	@Override
	public ArrayList<ICircuitWrapper> getWorkspaces() {
		return modelList;
	}

	@Override
	public boolean isSelectedComponent(final IGateWrapper g) {
		ISelectionModel asm = _getActiveSelectionModel();
		if (asm != null) {
			return asm.isSelectedComponent(g);
		}
		return false;
	}

	@Override
	public void manualPropertyChanged() {
		firePropertyChanged();
	}

	@Override
	public void newWorkspace() {
		addWorkspace(new CircuitWrapper());
	}

	@Override
	public void removeComponent(final IGateWrapper g) {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		ISelectionModel s = _getActiveSelectionModel();
		if (m != null && s != null) {
			m.removeComponent(g);
			m.updateComponents();
			s.removeComponent(g);
			firePropertyChanged();
		}
	}

	@Override
	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void removeSelectedComponents() {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		ISelectionModel s = _getActiveSelectionModel();
		if (m != null && s != null) {
			m.removeComponents(s.getSelectedComponents());
			m.updateComponents();
			s.removeComponents(s.getSelectedComponents());
			firePropertyChanged();
		}
	}

	@Override
	public void selectAllComponents() {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		ISelectionModel s = _getActiveSelectionModel();
		if (m != null && s != null) {
			s.selectComponents(m.getComponents());
			firePropertyChanged();
		}
	}

	@Override
	public void selectComponent(final Point position, final boolean multiSelect) {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		ISelectionModel s = _getActiveSelectionModel();
		if (m != null && s != null) {
			s.selectComponent(m.getComponent(position), multiSelect);
			firePropertyChanged();
		}
	}

	@Override
	public void selectComponents(final Point pos1, final Point pos2) {
		List<IGateWrapper> selectedComponents = new ArrayList<IGateWrapper>();

		ICircuitWrapper m = _getActiveWorkspaceModel();
		if(m != null){
			for (IGateWrapper gate : m.getComponents()) {
				Point gatePosition = gate.getPosition();
				if (gatePosition.x >= Math.min(pos1.x, pos2.x)
						&& gatePosition.x <= Math.max(pos1.x, pos2.x)
						&& gatePosition.y >= Math.min(pos1.y, pos2.y)
						&& gatePosition.y <= Math.max(pos1.y, pos2.y)) {
					selectedComponents.add(gate);
				}
			}
		}
		
		ISelectionModel s = _getActiveSelectionModel();
		if (s != null) {
			s.selectComponents(selectedComponents);
			firePropertyChanged();
		}
	}

	@Override
	public void selectComponents(List<IGateWrapper> list){
		ISelectionModel s = _getActiveSelectionModel();
		if (s != null) {
			s.selectComponents(list);
			firePropertyChanged();
		}
	}

	@Override
	public void setActiveWorkspace(final int i) {
		_setActiveWorkspace(i);
	}

	private void _addComponent(final IGateWrapper component,
			final Point position) {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		if (m != null) {
			m.addComponent(component, position);
			m.updateComponents();
		}
	}

	private ISelectionModel _getActiveSelectionModel() {
		if (selectedIndex >= 0 && selectedIndex < selectionModelList.size()) {
			return selectionModelList.get(selectedIndex);
		}
		return null;
	}

	private ICircuitWrapper _getActiveWorkspaceModel() {
		if (selectedIndex >= 0 && selectedIndex < modelList.size()) {
			return modelList.get(selectedIndex);
		}
		return null;
	}

	private void _setActiveWorkspace(final int i) {
		selectedIndex = i;
		firePropertyChanged();
	}

	@SuppressWarnings("boxing")
	private void firePropertyChanged() {
		pcs.firePropertyChange(new PropertyChangeEvent(this, "ModelManager", 0,
				1));
	}

	private void removeModel(final int i) {
		if (i < 0 || i >= modelList.size()) {
			return;
		} else {
			modelList.remove(i);
			selectionModelList.remove(i);
			if (modelList.size() == 0) {
				selectedIndex = -1;
			} else if (selectedIndex >= modelList.size()) {
				selectedIndex = modelList.size() - 1;
			}
			firePropertyChanged();
		}
	}

	@Override
	public IGateWrapper getGateWrapper(ICircuitGate gate) {
		ICircuitWrapper m = _getActiveWorkspaceModel();
		if(m != null){
			return m.getGateWrapper(gate);	
		}
		return null;
	}
}
