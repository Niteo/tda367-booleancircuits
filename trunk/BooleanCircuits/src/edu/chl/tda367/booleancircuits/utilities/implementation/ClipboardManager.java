package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.util.ArrayList;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;

public class ClipboardManager implements IClipboardManager {

	private List<IAbstractCircuitGate> clipboardList = new ArrayList<IAbstractCircuitGate>();

	@Override
	public void copy(List<IAbstractCircuitGate> componentsList) {
		clipboardList.clear();

		/*
		 * Add gates
		 */
		for (IAbstractCircuitGate component : componentsList) {
			IAbstractCircuitGate gate = component.clone();
			clipboardList.add(gate);
		}
		/*
		 * Check internal connections
		 */
		for (IAbstractCircuitGate component : clipboardList) {
			for (IGateInput input : component.getInputs()) {
				if (componentsList.contains(input.getInputComponent())) {
					component.connectInput(
							component.getInputs().indexOf(input), clipboardList
									.get(componentsList.indexOf(input
											.getInputComponent())), input
									.getInputPort());
				} else {
					System.out.println("Hello");
					component.connectInput(0, null, -1);
				}
			}
		}
	}

	@Override
	public List<IAbstractCircuitGate> paste() {
		return clipboardList;
	}

}
