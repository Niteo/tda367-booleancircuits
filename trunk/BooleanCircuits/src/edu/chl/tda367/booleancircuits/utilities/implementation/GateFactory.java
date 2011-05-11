package edu.chl.tda367.booleancircuits.utilities.implementation;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;

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

		// TODO: Remove or create a functional one.

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

	public static AbstractCircuitGate getNewComponent(String name,
			int noOfInputs) {

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
			return new ConstantGate(false);
		} else {
			System.out.println("Not a component!");
			return null;
		}
	}
}
