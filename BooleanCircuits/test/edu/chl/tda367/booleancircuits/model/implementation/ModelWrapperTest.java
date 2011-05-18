package edu.chl.tda367.booleancircuits.model.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;

public class ModelWrapperTest {

	@Test
	public void testModelWrapper() {
		new ModelWrapper();

	}

	@Test
	public void testModelWrapperFile() {
		new ModelWrapper(new File("test"));
	}

	@Test
	public void testModelWrapperFileModel() {
		new ModelWrapper(new File("test"), new Model());
	}

	@Test
	public void testGetComponent() {
		ModelWrapper wrapper = new ModelWrapper();
		assertNull(wrapper.getComponent(new Point(0, 0)));
	}

	@Test
	public void testGetComponents() {
		ModelWrapper wrapper = new ModelWrapper();

		assertTrue(wrapper.getComponents() != null);
		assertTrue(wrapper.getComponents().size() == 0);
	}

	@Test
	public void testAddComponent() {
		ModelWrapper wrapper = new ModelWrapper();
		wrapper.addComponent(new ConstantGate(true), new Point(10, 10));

		assertTrue(wrapper.getComponent(new Point(10, 10)) instanceof ConstantGate);

	}
	@Test
	public void testSetChangedFalse() {
		ModelWrapper wrapper = new ModelWrapper();
		wrapper.setChanged(true);
		
		
	}
	
	@Test
	public void testHasChanged() {
		ModelWrapper wrapper = new ModelWrapper();
		assertTrue(wrapper.hasChanged()== false);
		
	}

	@Test
	public void testAddComponents() {
		ModelWrapper wrapper = new ModelWrapper();
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();
		assertTrue(wrapper.getComponents().size() == 0);

		list.add(new AndGate(2));
		list.add(new NandGate(2));
		list.add(new OrGate(2));

		wrapper.addComponents(list);
		assertTrue(wrapper.getComponents().size() == 3);
	}

	@Test
	public void testRemoveComponents() {
		ModelWrapper wrapper = new ModelWrapper();
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();
		assertTrue(wrapper.getComponents().size() == 0);

		list.add(new AndGate(2));
		list.add(new NandGate(2));
		list.add(new OrGate(2));

		wrapper.addComponents(list);
		assertTrue(wrapper.getComponents().size() == 3);

		wrapper.removeComponents(list);
		assertTrue(wrapper.getComponents().size() == 0);
	}

	@Test
	public void testRemoveComponent() {
		ModelWrapper wrapper = new ModelWrapper();
		AndGate and = new AndGate(2);
		wrapper.addComponent(and, new Point(0, 0));

		assertTrue(wrapper.getComponent(new Point(0, 0)) instanceof AndGate);
		assertTrue(wrapper.getComponents().size() == 1);

		wrapper.removeComponent(and);
		assertTrue(wrapper.getComponents().size() == 0);
	}

	@Test
	public void testUpdateComponents() {
		ModelWrapper wrapper = new ModelWrapper();
		Clock clock = new Clock();

		wrapper.addComponent(clock, new Point(0, 0));
		wrapper.updateComponents();
		assertFalse(clock.getOutputValue(0));
		wrapper.clock();
		wrapper.updateComponents();
		assertTrue(clock.getOutputValue(0));
	}

	@Test
	public void testClock() {
		ModelWrapper wrapper = new ModelWrapper();
		Clock clock = new Clock();

		wrapper.addComponent(clock, new Point(0, 0));
		clock.update();
		assertFalse(clock.getOutputValue(0));
		wrapper.clock();
		clock.update();
		assertTrue(clock.getOutputValue(0));
	}

	@Test
	public void testGetFile() {
		ModelWrapper wrapper = new ModelWrapper();
		
		assertNull(wrapper.getFile());
		
		
	}



	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasFile() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testSetFile() {
		ModelWrapper wrapper = new ModelWrapper();
		File file = new File("file");
		wrapper.setFile(file);
		assertTrue(wrapper.getFile() instanceof File);
		
	}

}
