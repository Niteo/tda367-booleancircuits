package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;

public class ClipboardManager implements IClipboardManager {
	
	private List<IAbstractCircuitGate> clipboardCollection = new ArrayList<IAbstractCircuitGate>();

	@Override
	public void copy(List<IAbstractCircuitGate> gate) {
		clipboardCollection.clear();
		clipboardCollection.addAll(gate);
	}

	@Override
	public List<IAbstractCircuitGate> paste() {
		return clipboardCollection;
	}

}
