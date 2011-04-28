package edu.chl.tda367.booleancircuits.model.components;

import java.awt.Point;
import java.util.*;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

/**
 * Abstract class representing an abstract circuit component.
 * @author Kaufmann
 *
 */
public abstract class AbstractCircuitGate {
	private List<GateInput> inputs;
	private Boolean[] outputs;
	private boolean isInTiercalculation;
	private Point position;
	
	private void createOutputs(int amount){
		outputs = new Boolean[amount];
		for(int i = 0; i < outputs.length; i++){
			outputs[i] = false;
		}
	}
	
	private void createInputs(int amount){
		inputs = new ArrayList<GateInput>();
		for(int i = 0; i < amount; i++){
			inputs.add(new GateInput());
		}
	}
	
	
	/**
	 * Retrieves the inputs of the component
	 * @return inputs of the component
	 */
	protected List<GateInput> getInputs(){
		return inputs;
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
	 * Creates I/O for this component.
	 * @param in the amount of inputs to be created
	 * @param out the amount of outputs to be created
	 */
	protected void createIO(int in, int out){
		createOutputs(out);
		createInputs(in);
	}
	
	/**
	 * Overwrites gate with another one, copying all properties.
	 * @param gate the gate to override with
	 */
	public void overwriteGate(AbstractCircuitGate gate){
		this.inputs = gate.inputs;
		this.isInTiercalculation = gate.isInTiercalculation;
		this.outputs = gate.outputs;
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
	 * Gets the position of the gate
	 * @return a reference to the position of the gate
	 */
	public Point getPosition(){
		return this.position;
	}
	
	/**
	 * Sets the position of the gate
	 * @param coordinates of the gate
	 */
	public void setPosition(Point position){
		this.position = position;
	}
	
	/**
	 * updates the position of the gate
	 * @param deltaX: the difference in the x-axis between the current position and the old position
	 * @param deltaY: the difference in the y-axis between the current position and the old position
	 */
	public void move(int deltaX, int deltaY){
		this.position = new Point(this.position.x + deltaX,
				this.position.y + deltaY);
	}
	

	/**
	 * Returns a copy of the gate.
	 */
	public AbstractCircuitGate clone(){
		AbstractCircuitGate c = emptyGateClone();
		
		for(int i = 0; i < this.outputs.length; i++){
			c.setOutput(i, outputs[i]);	
		}
		
		int port = 0;
		for(GateInput gi : this.getInputs()){
			c.connectInput(port++, gi.getInputComponent(), gi.getInputPort());
		}
		
		c.position = position;
		
		return c;
	}
	
	/**
	 * Template-method for updating outputs of the component.
	 */
	protected abstract void updateOutput();
	
	/**
	 * Template-method for returning a gate of the used type.
	 * @return a gate of the used type
	 */
	protected abstract AbstractCircuitGate emptyGateClone();
	
	/**
	 * Returns the name of the gate.
	 */
	public abstract String toString();
}
