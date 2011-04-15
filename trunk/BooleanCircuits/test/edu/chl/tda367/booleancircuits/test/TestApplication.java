package edu.chl.tda367.booleancircuits.test;
import edu.chl.tda367.booleancircuits.model.components.*;

public class TestApplication {
	public static void main(String []args){
		ConstantGate cg = new ConstantGate(true);
		ConstantGate cg2 = new ConstantGate(false);
		AndGate ag = new AndGate(2);
		OrGate og = new OrGate(2);
		
		ag.connectInput(0, cg, 0);
		ag.connectInput(1, cg2, 0);
		og.connectInput(0, ag, 0);
		og.connectInput(1, cg2, 0);
		
		cg.update();
		cg2.update();
		ag.update();
		og.update();
		
		System.out.println(og.getComponentTier());
	}
}