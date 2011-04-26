package edu.chl.tda367.booleancircuits.test;

import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.*;


public class GateFactoryTester {

	public GateFactoryTester() {

	}

	public static boolean testGetnewComponent(AbstractCircuitGate component) {

		if(component.getClass().equals(GateFactory.getNewComponent(component).getClass())){
			return true;
		}
	return false;
	}

}
