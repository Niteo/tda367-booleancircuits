package edu.chl.tda367.booleancircuits.utilities;

import edu.chl.tda367.booleancircuits.model.components.*;

public final class GateFactory {

	public static enum Components {
		AND, NAND, OR, NOR, XOR, XNOR
	}

	public static enum PredefinedComponents {
		NOT, CONSTANTGATE
	}

	private GateFactory() {
	}

	/**
	 * Gets an new instance of the component type specified.
	 * 
	 * @param: component the new component
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

	/**
	 * Gets an instance of the component
	 * 
	 * @param: component the new component
	 * @return: a new instance of the component
	 */
	public static AbstractCircuitGate getNewComponent(
			PredefinedComponents component) {
		switch (component) {
		case NOT:
			return new NotGate();
		case CONSTANTGATE:
			return new ConstantGate(false);
		default:
			System.out.println("That's not a component!");

		}
		return null;
	}

	/**
	 * Gets an instance of the component with the specified amount of inputs
	 * 
	 * @param: component the new component
	 * @param: noOfInputs the amount of inputs on the component
	 * @return: a new instance of the component
	 */
	public static AbstractCircuitGate getNewComponent(Components component,
			int noOfInputs) {
		switch (component) {
		case AND:
			return new AndGate(noOfInputs);
		case NAND:
			return new NandGate(noOfInputs);
		case OR:
			return new OrGate(noOfInputs);
		case NOR:
			return new NorGate(noOfInputs);
		case XOR:
			return new XorGate(noOfInputs);
		case XNOR:
			return new XnorGate(noOfInputs);
		default:
			System.out.println("That's not a component!");

		}

		return null;

	}
}
