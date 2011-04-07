package edu.chl.tda367.booleancircuits.model.components;

import java.util.*;

/**
 * Abstract class representing an abstract circuit component.
 * @author Kaufmann
 *
 */
public abstract class AbstractCircuitComponent {
	private List<ComponentInput> inputs;
	private Boolean[] outputs;
	
	protected void createInputs(int amount){
		inputs = new ArrayList<ComponentInput>();
		for(int i = 0; i < amount; i++){
			inputs.add(new ComponentInput());
		}
	}
	protected void createOutputs(int amount){
		outputs = new Boolean[amount];
	}
	protected void setOutput(int index, boolean value){
		outputs[index] = value;
	}
	protected List<ComponentInput> getInputs(){
		return inputs;
	}
	
	public void connectInput(int inputPort, AbstractCircuitComponent component, int outputPort){
		inputs.get(inputPort).setInputComponent(component, outputPort);
	}
	public boolean getOutputValue(int index){
		return outputs[index];
	}
	public void update(){
		updateStrategy();
	}
	
	public abstract void updateStrategy();
}