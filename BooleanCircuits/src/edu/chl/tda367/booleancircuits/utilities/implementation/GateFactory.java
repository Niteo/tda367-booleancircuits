package edu.chl.tda367.booleancircuits.utilities.implementation;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;

public final class GateFactory {

	public static enum Components {
		AND, NAND, NOR, OR, XNOR, XOR
	}

	public static enum PredefinedComponents {
		CLOCK, CONSTANTGATE_ONE, CONSTANTGATE_ZERO, EQUAL, NOT
	}

	/**
	 * Gets an instance of the component with the specified amount of inputs
	 * 
	 * @param: component the new component
	 * @param: noOfInputs the amount of inputs on the component
	 * @return: a new instance of the component
	 */
	public static ICircuitGate getNewComponent(final Components component,
			final int noOfInputs) {
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

	/**
	 * Gets an instance of the component
	 * 
	 * @param: component the new component
	 * @return: a new instance of the component
	 */
	public static ICircuitGate getNewComponent(
			final PredefinedComponents component) {
		switch (component) {
			case NOT:
				return new NotGate();
			case CONSTANTGATE_ONE:
				return new ConstantGate(true);
			case CONSTANTGATE_ZERO:
				return new ConstantGate(false);
			case CLOCK:
				return new Clock();
			case EQUAL:
				return new Equal();
			default:
				System.out.println("That's not a component!");

		}
		return null;
	}

	public static ICircuitGate getNewComponent(final String name,
			final int noOfInputs) {

		if (name.equals("AND")) {
			return getNewComponent(Components.AND, noOfInputs);
		} else if (name.equals("NAND")) {
			return getNewComponent(Components.NAND, noOfInputs);
		} else if (name.equals("OR")) {
			return getNewComponent(Components.OR, noOfInputs);
		} else if (name.equals("NOR")) {
			return getNewComponent(Components.NOR, noOfInputs);
		} else if (name.equals("XOR")) {
			return getNewComponent(Components.XOR, noOfInputs);
		} else if (name.equals("XNOR")) {
			return getNewComponent(Components.XNOR, noOfInputs);
		} else if (name.equals("NOT")) {
			return getNewComponent(PredefinedComponents.NOT);
		} else if (name.equals("1")) {
			return new ConstantGate(true);
		} else if (name.equals("0")) {
			return new ConstantGate(false);
		} else if (name.equals("CLOCK")) {
			return new Clock();
		} else if (name.equals("EQUAL")) {
			return new Equal();
		} else {
			System.out.println("Not a component!");
			return null;
		}
	}

	private GateFactory() {
		return;
	}
}
