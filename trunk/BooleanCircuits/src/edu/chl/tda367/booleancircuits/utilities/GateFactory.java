package edu.chl.tda367.booleancircuits.utilities;

import edu.chl.tda367.booleancircuits.model.components.*;

public final class GateFactory {
	private GateFactory(){		
	}
	
	/**
	 * 
	 */
	public AbstractCircuitGate getNewComponent(AbstractCircuitGate gate){
		AbstractCircuitGate ret = null;
		try {
			ret = gate.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: Check if ret == null? Maybe? :)
		
		return ret;
	}
}
