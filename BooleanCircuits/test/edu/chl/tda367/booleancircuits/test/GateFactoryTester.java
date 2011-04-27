package edu.chl.tda367.booleancircuits.test;

import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.*;
import edu.chl.tda367.booleancircuits.utilities.GateFactory.Components;



public class GateFactoryTester {

	public GateFactoryTester() {

	}

	public static boolean testGetnewComponent(AbstractCircuitGate component) {

		if(component.getClass().equals(GateFactory.getNewComponent(component).getClass())){
			return true;
		}
	return false;
	}

	public static boolean testGetnewComponent(Components component) {

		switch (component) {
		case AND:
			if(GateFactory.getNewComponent(component).getClass().equals(new AndGate(2)));
			return true;
		case NOT:
			if(GateFactory.getNewComponent(component).getClass().equals(new NotGate()));
			return true;
		case NAND:
			if(GateFactory.getNewComponent(component).getClass().equals(new NandGate(2)));
			return true;
		case OR:
			if(GateFactory.getNewComponent(component).getClass().equals(new OrGate(2)));
			return true;
		case NOR:
			if(GateFactory.getNewComponent(component).getClass().equals(new NorGate(2)));
			return true;
		case CONSTANTGATE:
			if(GateFactory.getNewComponent(component).getClass().equals(new ConstantGate(false)));
			return true;
		case XOR:
			if(GateFactory.getNewComponent(component).getClass().equals(new XorGate(2)));
			return true;
		case XNOR:
			if(GateFactory.getNewComponent(component).getClass().equals(new XnorGate(2)));
			return true;
		default:
			System.out.println("It's not a component!");

		}
	return false;
	}
}
