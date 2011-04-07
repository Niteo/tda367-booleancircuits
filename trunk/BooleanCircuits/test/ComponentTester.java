import java.util.ArrayList;
import java.util.List;
import edu.chl.tda367.booleancircuits.model.components.*;


public final class ComponentTester {
	private ComponentTester(){
	}
	
	public static boolean testComponent(AbstractCircuitGate component,
			boolean[] input,
			boolean[] desiredOutput){
		//
		if(component.getNoOfInputs() != input.length ||
				component.getNoOfOutput() != desiredOutput.length)
			throw new IllegalArgumentException("Number of component inputs must ");
		
		// Set up constants
		List<ConstantComponent> constantList = new ArrayList<ConstantComponent>();
		for(boolean b : input){
			constantList.add(new ConstantComponent(b));
		}
		
		// Set up components
		for(int i = 0; i < constantList.size(); i++){
			component.connectInput(i, constantList.get(i), 0);
		}
		
		// Update all
		for(ConstantComponent c : constantList){
			c.update();
		}
		component.update();
		
		// Compare output with desired output
		boolean passed = true;
		for(int i = 0; i < desiredOutput.length ; i++){
			if(component.getOutputValue(i) != desiredOutput[i]){
				passed = false;
				break;
			}
		}
		
		return passed;
	}

}