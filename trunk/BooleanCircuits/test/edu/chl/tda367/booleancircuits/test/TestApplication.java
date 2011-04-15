package edu.chl.tda367.booleancircuits.test;
import edu.chl.tda367.booleancircuits.model.components.*;

public class TestApplication {
	public static void main(String []args){
		boolean result = ComponentTester.testComponent(new OrGate(2),
				new boolean[]{false, false},
				new boolean[]{true});
		
		if(result == true){
			System.out.println("WORKS!");
		} else {
			System.out.println("Does not work! >:");
		}
	}
}