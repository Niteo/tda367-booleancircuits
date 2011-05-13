package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;

/**
 * handles copying and pasting components
 * 
 * @author antonlin
 * 
 */

public class ClipboardManager implements IClipboardManager {

	private List<ICircuitGate> clipboardList;
	private Map<ICircuitGate, ICircuitGate> componentsMap = new HashMap<ICircuitGate, ICircuitGate>();
	private List<ICircuitGate> lastPastedComponents;

	@Override
	public void copy(List<ICircuitGate> originalList) {
		clipboardList = duplicateList(originalList);
	}

	@Override
	public List<ICircuitGate> paste() {

		return duplicateList(clipboardList);
	}

	private List<ICircuitGate> duplicateList(
			List<ICircuitGate> list) {

		List<ICircuitGate> dupList = new ArrayList<ICircuitGate>();
		for (ICircuitGate gate : list) {
			componentsMap.put(gate, gate.clone());
		}

		for (ICircuitGate gate : list) {
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
	public List<ICircuitGate> getLastPastedComponents() {

		return lastPastedComponents;
	}
}
