package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.*;

public class GateWrapper implements IGateWrapper{

	private Point position = new Point();
	private ICircuitGate gate;

	public GateWrapper(ICircuitGate gate) {
		this.gate = gate;
	}

	@Override
	public Point getPosition() {
		return new Point(position);
	}

	@Override
	public void move(int deltaX, int deltaY) {
		position = new Point(position.x + deltaX, position.y + deltaY);
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public void connectInput(int inputPort, ICircuitGate component,
			int outputPort) {
		gate.connectInput(inputPort, component, outputPort);
	}

	@Override
	public boolean connectsTo(ICircuitGate g)
			throws IllegalArgumentException {
		return gate.connectsTo(g);
	}

	@Override
	public int getComponentTier() {
		return gate.getComponentTier();
	}

	@Override
	public List<IGateInput> getInputs() {
		return gate.getInputs();
	}

	@Override
	public int getNoOfInputs() {
		return gate.getNoOfInputs();
	}

	@Override
	public int getNoOfOutputs() {
		return gate.getNoOfOutputs();
	}

	@Override
	public boolean[] getOutputs() {
		return gate.getOutputs();
	}

	@Override
	public boolean getOutputValue(int index) {
		return gate.getOutputValue(index);
	}

	@Override
	public Collection<ICircuitGate> getRecoupledTo() {
		return gate.getRecoupledTo();
	}

	@Override
	public void overwriteGate(ICircuitGate g) {
		gate.overwriteGate(g);
	}

	@Override
	public boolean update() {
		return gate.update();
	}

	@Override
	public ICircuitGate getGateClone() {
		return ((ICloneableGate) gate).clone();
	}

	@Override
	public ICircuitGate getGate() {
		return gate;
	}

	@Override
	public String toString(){
		return gate.toString();
	}

	@Override
	public boolean isFullyConnected() {
		return gate.isFullyConnected();
	}

}
