package edu.chl.tda367.booleancircuits.test;
import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;
import edu.chl.tda367.booleancircuits.utilities.GateFactory.Components;


public class TestApplication {
	public static void main(String []args){
		/*Model model = new Model();
		model.addComponent(new AndGate(2));*/
		
		AbstractCircuitGate not = new NotGate();
		
		System.out.println(GateFactoryTester.testGetnewComponent(Components.AND));
		
		
	}
}