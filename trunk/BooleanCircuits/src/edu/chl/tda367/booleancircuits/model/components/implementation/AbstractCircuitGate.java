package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.awt.Point;
import java.util.*;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory;

/**
 * Abstract class representing an abstract circuit component.
 * 
 * @author Kaufmann
 * 
 */
public abstract class AbstractCircuitGate implements ICircuitGate {
	private List<IGateInput> inputs;
	private Boolean[] outputs;
	private boolean isInTiercalculation;
	private boolean isInConnectToCalculation;
	private Point position = new Point();

	public AbstractCircuitGate(int inPorts, int outPorts){
		createOutputs(outPorts);
		createInputs(inPorts);	
	}
	
	private void createOutputs(int amount) {
		outputs = new Boolean[amount];
		for (int i = 0; i < amount; i++) {
			outputs[i] = false;
		}
	}

	private void createInputs(int amount) {
		inputs = new ArrayList<IGateInput>();
		for (int i = 0; i < amount; i++) {
			inputs.add(new GateInput());
		}
	}

	public List<IGateInput> getInputs() {
		return inputs;
	}

	/**
	 * Sets a specific output to a given value
	 * 
	 * @param index
	 *            specifies which output to set
	 * @param value
	 *            the value to set the output to
	 */
	protected void setOutput(int index, boolean value) {
		outputs[index] = value;
	}

	public void overwriteGate(AbstractCircuitGate gate) {
		this.inputs = gate.inputs;
		this.isInTiercalculation = gate.isInTiercalculation;
		this.outputs = gate.outputs;
	}

	public void connectInput(int inputPort, ICircuitGate component,
			int outputPort) {
		inputs.get(inputPort).setInputComponent(component, outputPort);
	}

	public boolean getOutputValue(int index) {
		return outputs[index];
	}

	public int getNoOfInputs() {
		return inputs.size();
	}

	public int getNoOfOutputs() {
		return outputs.length;
	}

	public void update() {
		updateOutput();
	}

	public int getComponentTier() {
		if (isInTiercalculation) {
			return 0;
		} else {
			isInTiercalculation = true;

			int maxTier = 0;
			for (IGateInput gp : inputs) {
				if (gp.getInputComponent() != null) {
					int tmpTier = gp.getInputComponent().getComponentTier();
					if (tmpTier > maxTier) {
						maxTier = tmpTier;
					}
				}
			}
			isInTiercalculation = false;
			return maxTier + 1;
		}
	}

	@Override
	public Collection<ICircuitGate> getRecoupledTo() {
		Collection<ICircuitGate> col = new ArrayList<ICircuitGate>();
		
		for(IGateInput input : inputs){
			ICircuitGate inputGate = input.getInputComponent();
			if(inputGate != null){
				if(inputGate.connectsTo(this)){
					col.add(inputGate);
				}
			}
		}
		
		return col;
	}
	
	public boolean connectsTo(ICircuitGate gate){
		boolean ret = false;
		if(isInConnectToCalculation){
			return false;
		} else {
			isInConnectToCalculation = true;
		}
		for(IGateInput input : inputs){
			ICircuitGate inputGate = input.getInputComponent();
			if(inputGate != null){
				if(inputGate == gate || inputGate.connectsTo(gate)){
					ret = true;
					break;
				}
			}
		}
		
		isInConnectToCalculation = false;
		return ret;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void move(int deltaX, int deltaY) {
		this.position = new Point(this.position.x + deltaX, this.position.y
				+ deltaY);
	}

	public AbstractCircuitGate clone() {
		AbstractCircuitGate c = emptyGateClone();

		for (int i = 0; i < this.outputs.length; i++) {
			c.setOutput(i, outputs[i]);
		}

		int port = 0;
		for (IGateInput gi : this.getInputs()) {
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
	 * 
	 * @return a gate of the used type
	 */
	protected abstract AbstractCircuitGate emptyGateClone();

	public abstract String toString();
}
