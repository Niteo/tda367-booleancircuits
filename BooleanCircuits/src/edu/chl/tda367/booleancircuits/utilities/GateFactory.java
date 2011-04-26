package edu.chl.tda367.booleancircuits.utilities;

import edu.chl.tda367.booleancircuits.model.components.*;

public final class GateFactory {

	public static enum components{AND,NOT,NAND,OR,NOR,CONTANTGATE,XOR,XNOR} 

	private GateFactory() {
	}


	/**
	 * gets an instance of the component
	 * @param: 
	 */
	public static AbstractCircuitGate getNewComponent(AbstractCircuitGate component) {
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
}
