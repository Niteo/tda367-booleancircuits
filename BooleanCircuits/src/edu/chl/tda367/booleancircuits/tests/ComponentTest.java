package edu.chl.tda367.booleancircuits.tests;

import edu.chl.tda367.booleancircuits.model.components.AndComponent;
import edu.chl.tda367.booleancircuits.model.components.ConstantComponent;

public class ComponentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AndComponent and = new AndComponent();
		ConstantComponent one = new ConstantComponent(true);
		ConstantComponent zero = new ConstantComponent(false);
		
		and.connectInput(0, one, 0);
		and.connectInput(1, one, 0);
		
		one.update();
		zero.update();
		and.update();
		
		
		System.out.println(and.getOutputValue(0));
	}

}
