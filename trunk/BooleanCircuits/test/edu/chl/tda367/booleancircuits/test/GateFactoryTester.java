package edu.chl.tda367.booleancircuits.test;

import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.*;
import edu.chl.tda367.booleancircuits.utilities.GateFactory.Components;
import edu.chl.tda367.booleancircuits.utilities.GateFactory.PredefinedComponents;

public class GateFactoryTester {

	public GateFactoryTester() {

	}

	public static boolean testGetnewComponent(AbstractCircuitGate component) {

		if (component.getClass().equals(
				GateFactory.getNewComponent(component).getClass())) {
			return true;
		}
		return false;
	}

	public static boolean testGetnewComponent(Components component,
			int noOfInputs) {

		switch (component) {
		case AND:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new AndGate(2).getClass())) {
				return true;
			}
			return false;

		case NAND:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new NandGate(3).getClass())) {
				return true;
			}
			return false;
		case OR:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new OrGate(2).getClass())) {

				return true;
			}
			return false;
		case NOR:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new NorGate(2).getClass())) {
				return true;
			}
			return false;
		case XOR:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new XorGate(3).getClass())) {
				return true;
			}
			return false;
		case XNOR:
			if (GateFactory.getNewComponent(component, noOfInputs).getClass()
					.equals(new XnorGate(2).getClass())) {
				return true;
			}
			return false;
		default:
			System.out.println("That's not a component!");

		}
		return false;
	}

	public static boolean testGetnewComponent(PredefinedComponents component) {

		switch (component) {
		case NOT:
			if (GateFactory.getNewComponent(component).getClass().equals(
					new NotGate().getClass())) {
				return true;

			}
			return false;
		case CONSTANTGATE:
			if (GateFactory.getNewComponent(component).getClass().equals(
					new ConstantGate(false).getClass())) {
				return true;
			}
			return false;

		default:
			System.out.println("It's not a component!");

		}

		return false;
	}

}
