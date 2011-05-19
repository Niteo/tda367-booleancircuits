package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;

/**
 * Abstract class representing an abstract circuit component.
 *
 * @author Kaufmann
 *
 */
public abstract class AbstractCircuitGate implements ICircuitGate {
	private List<IGateInput> inputs;
	private boolean[] outputs;
	private boolean isInTiercalculation;
	private boolean isInConnectToCalculation;
	private Point position = new Point();

	public AbstractCircuitGate(int inPorts, int outPorts) {
		createOutputs(outPorts);
		createInputs(inPorts);
		update();
	}

	private void createOutputs(int amount) {
		outputs = new boolean[amount];
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

	@Override
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

	@Override
	public void overwriteGate(AbstractCircuitGate gate) {
		this.inputs = gate.inputs;
		this.isInTiercalculation = gate.isInTiercalculation;
		this.outputs = gate.outputs;
	}

	@Override
	public void connectInput(int inputPort, ICircuitGate component,
			int outputPort) throws IllegalArgumentException {
		if (inputPort > inputs.size() - 1) {
			throw new IllegalArgumentException();
		} else {
			inputs.get(inputPort).setInputComponent(component, outputPort);
		}
	}

	@Override
	public boolean getOutputValue(int index) {
		return outputs[index];
	}

	@Override
	public int getNoOfInputs() {
		return inputs.size();
	}

	@Override
	public int getNoOfOutputs() {
		return outputs.length;
	}

	@Override
	public boolean update() {
		boolean[] temp = outputs.clone();
		updateOutput();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != outputs[i]) {
				return true;
			}
		}
		return false;
	}

	@Override
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
		Collection<ICircuitGate> temp = new ArrayList<ICircuitGate>();
		Collection<ICircuitGate> store = new ArrayList<ICircuitGate>();

		for (IGateInput input : inputs) {
			ICircuitGate inputGate = input.getInputComponent();
			if (inputGate != null) {
				if (inputGate.connectsTo(this)) {
					temp.add(inputGate);
				}
			}
		}
		while (temp.size() > 0) {
			for (ICircuitGate gate : temp) {
				if (gate != this) {
					Collection<IGateInput> gateInputs = gate.getInputs();
					for (IGateInput input : gateInputs) {
						ICircuitGate inputGate = input.getInputComponent();
						if (inputGate != null && inputGate != this) {
							if (inputGate.connectsTo(this) && !col.contains(inputGate)) {
								store.add(inputGate);
							}
						}
					}
				}
			}
			col.addAll(temp);
			temp.clear();
			temp.addAll(store);
			store.clear();
		}
		return col;
	}

	@Override
	public boolean connectsTo(ICircuitGate gate) {
		boolean ret = false;
		if (isInConnectToCalculation) {
			return false;
		} else {
			isInConnectToCalculation = true;
		}
		for (IGateInput input : inputs) {
			ICircuitGate inputGate = input.getInputComponent();
			if (inputGate != null) {
				if (inputGate == gate || inputGate.connectsTo(gate)) {
					ret = true;
					break;
				}
			}
		}

		isInConnectToCalculation = false;
		return ret;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public void move(int deltaX, int deltaY) {
		this.position = new Point(this.position.x + deltaX, this.position.y
				+ deltaY);
	}

	@Override
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
	 * Updates the output of the gate.
	 *
	 */
	protected abstract void updateOutput();

	/**
	 * Template-method for returning a gate of the used type.
	 *
	 * @return a gate of the used type
	 */
	protected abstract AbstractCircuitGate emptyGateClone();

	@Override
	public abstract String toString();
}
