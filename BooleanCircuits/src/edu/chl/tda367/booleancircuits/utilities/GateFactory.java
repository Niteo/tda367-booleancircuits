package edu.chl.tda367.booleancircuits.utilities;

import edu.chl.tda367.booleancircuits.model.components.*;

public final class GateFactory {

	public static enum Components {
		AND, NOT, NAND, OR, NOR, CONSTANTGATE, XOR, XNOR
	}

	private GateFactory() {
	}

	/**
	 * gets an instance of the component
	 * 
	 * @param: the new component
	 * @return: returns a new instance of the component
	 */
	public static AbstractCircuitGate getNewComponent(
			AbstractCircuitGate component) {
		AbstractCircuitGate newComponent = null;
		try {
			newComponent = component.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO: Check if ret == null? Maybe? :)

		return newComponent;
	}

	public static AbstractCircuitGate getNewComponent(Components component) {

		switch (component) {
		case AND:
			return new AndGate(2);
		case NOT:
			return new NotGate();
		case NAND:
			return new NandGate(2);
		case OR:
			return new OrGate(2);
		case NOR:
			return new NorGate(2);
		case CONSTANTGATE:
			return new ConstantGate(false);
		case XOR:
			return new XorGate(2);
		case XNOR:
			return new XnorGate(2);
		default:
			System.out.println("It's not a component!");

		}

		return null;

	}
}
