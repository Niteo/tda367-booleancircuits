package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;

public class ModelManagerTest {

	@Test
	public void testAddComponent() {
		ModelManager mm = new ModelManager();

		mm.newWorkspace();
		mm.addComponent(new GateWrapper(new ConstantGate(true)),
				new Point(0, 0));

		assertTrue(mm.getActiveWorkspaceModel().
				getComponent(new Point(0, 0)).
				getGate() instanceof ConstantGate);
	}

	@Test
	public void testAddComponentsList() {
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		list.add(new GateWrapper(new AndGate(2)));
		new ModelManager().addComponents(list);
	}

	@Test
	public void testAddComponentsListPoint() {
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		list.add(new GateWrapper(new AndGate(2)));
		new ModelManager().addComponents(list, new Point(0, 0));
	}

	@Test
	public void testAddPropertyChangeListener() {
		new ModelManager()
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						return;
					}
				});
	}

	@Test
	public void testAddWorkspace() {
		ModelManager mm = new ModelManager();
		ModelWrapper mw = new ModelWrapper();

		mm.addWorkspace(mw);
		assertTrue(mm.getWorkspaces().size() == 1);
	}

	@Test
	public void testCloseActiveWorkspace() {
		ModelManager mm = new ModelManager();
		ModelWrapper mw1 = new ModelWrapper();
		ModelWrapper mw2 = new ModelWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);

		assertTrue(mm.getActiveWorkspaceIndex() == 1);
		mm.closeActiveWorkspace();
		assertTrue(mm.getActiveWorkspaceIndex() == 0);
		assertTrue(mm.getActiveWorkspaceModel() == mw1);
	}

	@Test
	public void testCloseAllWorkspaces() {
		ModelManager mm = new ModelManager();

		mm.newWorkspace();
		mm.newWorkspace();
		mm.closeAllWorkspaces();
		assertTrue(mm.getWorkspaces().size() == 0);
	}

	@Test
	public void testCloseWorkspace() {
		ModelManager mm = new ModelManager();
		ModelWrapper mw1 = new ModelWrapper();
		ModelWrapper mw2 = new ModelWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);

		mm.closeWorkspace(1);
		assertTrue(mm.getActiveWorkspaceModel() == mw1);
	}

	@Test
	public void testConnectComponents() {
		new ModelManager().connectComponents(new GateWrapper(new AndGate(2)),
				new GateWrapper(new NandGate(2)), 0, 0);
	}

	@Test
	public void testGetActiveSelectionModel() {
		ModelManager mm = new ModelManager();
		assertNull(mm.getActiveSelectionModel());
	}

	@Test
	public void testGetActiveWorkspaceIndex() {
		ModelManager mm = new ModelManager();
		assertTrue(mm.getActiveWorkspaceIndex() == -1);
	}

	@Test
	public void testGetActiveWorkspaceModel() {
		ModelManager mm = new ModelManager();
		assertNull(mm.getActiveWorkspaceModel());
	}

	@Test
	public void testGetWorkspaces() {
		ModelManager mm = new ModelManager();
		assertTrue(mm.getWorkspaces().size() == 0);
	}

	@Test
	public void testIsSelectedComponent() {
		new ModelManager().isSelectedComponent(new GateWrapper(new NandGate(220)));
	}

	@Test
	public void testManualPropertyChanged() {
		new ModelManager().manualPropertyChanged();
	}

	@Test
	public void testModelManager() {
		new ModelManager();
	}

	@Test
	public void testNewWorkspace() {
		ModelManager mm = new ModelManager();

		assertTrue(mm.getWorkspaces().size() == 0);
		mm.newWorkspace();
		assertTrue(mm.getWorkspaces().size() == 1);
	}

	@Test
	public void testRemoveComponent() {
		new ModelManager().removeComponent(new GateWrapper(new ConstantGate(true)));
	}

	@Test
	public void testRemovePropertyChangeListener() {
		PropertyChangeListener listener = new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				return;
			}
		};
		new ModelManager().addPropertyChangeListener(listener);
		new ModelManager().removePropertyChangeListener(listener);
	}

	@Test
	public void testRemoveSelectedComponents() {
		new ModelManager().removeSelectedComponents();
	}

	@Test
	public void testSelectAllComponents() {
		new ModelManager().selectAllComponents();
	}

	@Test
	public void testSelectComponent() {
		new ModelManager().selectComponent(new Point(0, 0), false);
	}

	@Test
	public void testSetActiveWorkspace() {
		ModelManager mm = new ModelManager();
		ModelWrapper mw1 = new ModelWrapper();
		ModelWrapper mw2 = new ModelWrapper();

		mm.addWorkspace(mw1);
		mm.addWorkspace(mw2);
		mm.setActiveWorkspace(0);

		assertTrue(mm.getActiveWorkspaceModel() == mw1);
	}

}
