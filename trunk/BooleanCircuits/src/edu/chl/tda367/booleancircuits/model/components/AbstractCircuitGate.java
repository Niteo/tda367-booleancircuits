package edu.chl.tda367.booleancircuits.model.components;

import java.util.*;

/**
 * Abstract class representing an abstract circuit component.
 * @author Kaufmann
 *
 */
public abstract class AbstractCircuitGate {
	private List<GateInput> inputs;
	private Boolean[] outputs;
	private boolean isInTiercalculation;
	
	/**
	 * Creates I/O for this component.
	 * @param in the amount of inputs to be created
	 * @param out the amount of outputs to be created
	 */
	protected void createIO(int in, int out){
		createOutputs(out);
		createInputs(in);
	}
	
	private void createOutputs(int amount){
		outputs = new Boolean[amount];
		for(Boolean o : outputs){
			o = false;
		}
	}
	
	private void createInputs(int amount){
		inputs = new ArrayList<GateInput>();
		for(int i = 0; i < amount; i++){
			inputs.add(new GateInput());
		}
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
	protected List<GateInput> getInputs(){
		return inputs;
	}
	
	/**
	 * Connects a specific input of this component with a specific output of another component.
	 * @param inputPort the input port of this component to connect
	 * @param component the component to connect this component to
	 * @param outputPort the output port to connect to
	 */
	public void connectInput(int inputPort, AbstractCircuitGate component, int outputPort){
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
	 * Gets the number of inputs
	 * @return number of inputs
	 */
	public int getNoOfInputs(){
		return inputs.size();
	}
	
	/**
	 * Gets the number of outputs
	 * @return number of outputs
	 */
	public int getNoOfOutputs(){
		return outputs.length;
	}
	
	/**
	 * Updates this components output based on its input.
	 */
	public void update(){
		updateOutput();
	}
	
	/**
	 * Calculates the component's tier.
	 * @return the tier of the component
	 */
	public int getComponentTier(){
		if(isInTiercalculation){
			return 0;
		} else {
			isInTiercalculation = true;
			
			int maxTier = 0;
			for(GateInput gp : inputs){
				if(gp.getInputComponent() != null){
					int tmpTier = gp.getInputComponent().getComponentTier();
					if(tmpTier > maxTier){
						maxTier = tmpTier;
					}
				}
			}
			isInTiercalculation = false;
			return maxTier + 1;
		}
	}
	
	/**
	 * Template-method for updating outputs of the component.
	 */
	protected abstract void updateOutput();
}