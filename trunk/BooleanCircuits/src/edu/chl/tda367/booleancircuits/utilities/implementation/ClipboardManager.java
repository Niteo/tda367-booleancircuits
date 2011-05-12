package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;

public class ClipboardManager implements IClipboardManager {

	private List<IAbstractCircuitGate> clipboardList;
	private Map<IAbstractCircuitGate, IAbstractCircuitGate> componentsMap = new HashMap<IAbstractCircuitGate, IAbstractCircuitGate>();
	private List<IAbstractCircuitGate> lastPastedComponents;

	@Override
	public void copy(List<IAbstractCircuitGate> originalList) {
		clipboardList = duplicateList(originalList);
	}

	@Override
	public List<IAbstractCircuitGate> paste() {

		return duplicateList(clipboardList);
	}

	private List<IAbstractCircuitGate> duplicateList(
			List<IAbstractCircuitGate> list) {

		List<IAbstractCircuitGate> dupList = new ArrayList<IAbstractCircuitGate>();
		for (IAbstractCircuitGate gate : list) {
			componentsMap.put(gate, gate.clone());
		}

		for (IAbstractCircuitGate gate : list) {
			for (IGateInput input : gate.getInputs()) {
				if (!list.contains(input.getInputComponent())) {
					componentsMap.get(gate).connectInput(
							gate.getInputs().indexOf(input), null, 0);
				} else {
					componentsMap.get(gate).connectInput(
							gate.getInputs().indexOf(input),
							componentsMap.get(input.getInputComponent()),
							input.getInputPort());
				}
			}
			dupList.add(componentsMap.get(gate));
		}
		lastPastedComponents = dupList;
		return dupList;
	}

	@Override
	public List<IAbstractCircuitGate> getLastPastedComponents() {

		return lastPastedComponents;
	}
}
