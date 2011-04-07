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
	
	/**
	 * Creates inputs for this component.
	 * @param amount the amount of inputs to be created
	 */
	protected void createInputs(int amount){
		inputs = new ArrayList<ComponentInput>();
		for(int i = 0; i < amount; i++){
			inputs.add(new ComponentInput());
		}
	}
	
	/**
	 * Creates outputs for this component.
	 * @param amount the amount of outputs to be created
	 */
	protected void createOutputs(int amount){
		outputs = new Boolean[amount];
	}
	
	/**
	 * Sets a specific output to a given value
	 * @param index specifies which output to set
	 * @param value the value to set the output to
	 */
	protected void setOutput(int index, boolean value){
		outputs[index] = value;
	}
	
	/**
	 * Retrieves the inputs of the component
	 * @return inputs of the component
	 */
	protected List<ComponentInput> getInputs(){
		return inputs;
	}
	
	/**
	 * Connects a specific input of this component with a specific output of another component.
	 * @param inputPort the input port of this component to connect
	 * @param component the component to connect this component to
	 * @param outputPort the output port to connect to
	 */
	public void connectInput(int inputPort, AbstractCircuitComponent component, int outputPort){
		inputs.get(inputPort).setInputComponent(component, outputPort);
	}
	/**
	 * Retrieves the value of a specific port on this component
	 * @param index the port to retrieve from
	 * @return
	 */
	public boolean getOutputValue(int index){
		return outputs[index];
	}
	
	/**
	 * Updates this component
	 */
	public void update(){
		updateOutput();
	}
	
	public abstract void updateOutput();
}