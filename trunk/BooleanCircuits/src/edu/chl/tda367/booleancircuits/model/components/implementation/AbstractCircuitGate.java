package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.awt.Point;
import java.util.*;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

/**
 * Abstract class representing an abstract circuit component.
 * @author Kaufmann
 *
 */
public abstract class AbstractCircuitGate implements IAbstractCircuitGate {
	private List<GateInput> inputs;
	private Boolean[] outputs;
	private boolean isInTiercalculation;
	private Point position = new Point();
	
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
	public List<GateInput> getInputs(){
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
	
	
	public void overwriteGate(AbstractCircuitGate gate){
		this.inputs = gate.inputs;
		this.isInTiercalculation = gate.isInTiercalculation;
		this.outputs = gate.outputs;
	}
	

	public void connectInput(int inputPort, AbstractCircuitGate component, int outputPort){
		inputs.get(inputPort).setInputComponent(component, outputPort);
	}

	public boolean getOutputValue(int index){
		return outputs[index];
	}
	
	
	public int getNoOfInputs(){
		return inputs.size();
	}
	
	
	public int getNoOfOutputs(){
		return outputs.length;
	}
	
	
	public void update(){
		updateOutput();
	}
	
	
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
	
	
	public Point getPosition(){
		return this.position;
	}
	
	
	public void setPosition(Point position){
		this.position = position;
	}
	

	public void move(int deltaX, int deltaY){
		this.position = new Point(this.position.x + deltaX,
				this.position.y + deltaY);
	}
	

	
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
	 * updates the output of the gate.
	 */
	protected abstract void updateOutput();
	
	/**
	 * Template-method for returning a gate of the used type.
	 * @return a gate of the used type
	 */
	protected abstract AbstractCircuitGate emptyGateClone();
	
	
	public abstract String toString();
}
