package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;

/**
 * handles copying and pasting components
 *
 * @author antonlin
 *
 */

public class ClipboardManager implements IClipboardManager {

	private List<IGateWrapper> clipboardList = new ArrayList<IGateWrapper>();
	private Map<IGateWrapper, IGateWrapper> componentsMap = new HashMap<IGateWrapper, IGateWrapper>();
	private List<IGateWrapper> lastPastedComponents = new ArrayList<IGateWrapper>();

	@Override
	public void copy(final List<IGateWrapper> originalList) {
		clipboardList.clear();
		clipboardList = duplicateList(originalList);
	}

	@Override
	public List<IGateWrapper> getLastPastedComponents() {
		return lastPastedComponents;
	}

	@Override
	public List<IGateWrapper> paste() {
		return duplicateList(clipboardList);
	}

	private List<IGateWrapper> duplicateList(final List<IGateWrapper> list) {

		List<IGateWrapper> dupList = new ArrayList<IGateWrapper>();
		for (IGateWrapper gate : list) {
			if (gate != null) {
				GateWrapper temp = new GateWrapper(gate.getGateClone());
				temp.setPosition(gate.getPosition());
				componentsMap.put(gate, temp);
			}
		}

		for (IGateWrapper gate : list) {
			if (gate != null) {
				for (IGateInput input : gate.getInputs()) {
					if (!list.contains(input.getInputComponent())) {
						componentsMap.get(gate).connectInput(
								gate.getInputs().indexOf(input), null, 0);
					} else {
						componentsMap.get(gate).connectInput(
								gate.getInputs().indexOf(input),
								componentsMap.get(input.getInputComponent()).getGate(),
								input.getInputPort());
					}
				}
				dupList.add(componentsMap.get(gate));
			}
		}
		lastPastedComponents = dupList;
		return dupList;
	}
}
