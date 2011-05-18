package edu.chl.tda367.booleancircuits.utilities.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;

public class ClipboardManagerTest {

	@Test
	public void testPaste() {
		ClipboardManager manager = new ClipboardManager();
		assertTrue(manager.paste().size() == 0);
	}

	@Test
	public void testCopy() {
		ClipboardManager manager = new ClipboardManager();
		List<ICircuitGate> list = new ArrayList<ICircuitGate>();
		AndGate and = new AndGate(2);
		NandGate nand = new NandGate(2);

		list.add(and);
		list.add(nand);
		manager.copy(list);

		assertTrue(manager.paste().size() == 2);
		assertTrue(manager.paste().get(0) instanceof AndGate);
		assertTrue(manager.paste().get(1) instanceof NandGate);
	}
}
