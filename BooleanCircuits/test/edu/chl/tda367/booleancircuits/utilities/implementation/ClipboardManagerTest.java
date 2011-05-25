package edu.chl.tda367.booleancircuits.utilities.implementation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;

public class ClipboardManagerTest {

	@Test
	public void testCopy() {
		ClipboardManager manager = new ClipboardManager();
		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper nand = new GateWrapper(new NandGate(2));

		list.add(and);
		list.add(nand);
		manager.copy(list);

		assertTrue(manager.paste().size() == 2);
		assertTrue(manager.paste().get(0).getGate() instanceof AndGate);
		assertTrue(manager.paste().get(1).getGate() instanceof NandGate);
	}

	@Test
	public void testPaste() {
		ClipboardManager manager = new ClipboardManager();
		assertTrue(manager.paste().size() == 0);

		List<IGateWrapper> list = new ArrayList<IGateWrapper>();
		IGateWrapper and = new GateWrapper(new AndGate(2));
		IGateWrapper nand = new GateWrapper(new NandGate(2));

		list.add(and);
		list.add(nand);
		manager.copy(list);

		assertTrue(manager.paste().size() == 2);
	}
}
