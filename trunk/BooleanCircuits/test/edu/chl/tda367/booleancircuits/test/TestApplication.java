package edu.chl.tda367.booleancircuits.test;
import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.*;

public class TestApplication {
	public static void main(String []args){
		Model model = new Model();
		model.addComponent(new AndGate(2));
		model.addComponent(new AndGate(2));
		model.addComponent(new OrGate(2));
		model.addComponent(new NorGate(2));
		
		
	}
}