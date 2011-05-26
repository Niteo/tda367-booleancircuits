package edu.chl.tda367.booleancircuits.model.components.implementation;

import java.awt.Point;
import java.util.*;

import edu.chl.tda367.booleancircuits.model.components.*;

public class GateWrapper implements IGateWrapper {

	private ICircuitGate gate;
	private Point position = new Point();

	public GateWrapper(final ICircuitGate gate) {
		this.gate = gate;
	}

	@Override
	public void connectInput(final int inputPort, final ICircuitGate component,
			final int outputPort) {
		gate.connectInput(inputPort, component, outputPort);
	}

	@Override
	public boolean connectsTo(final ICircuitGate g)
			throws IllegalArgumentException {
		return gate.connectsTo(g);
	}

	@Override
	public int getComponentTier() {
		return gate.getComponentTier();
	}

	@Override
	public ICircuitGate getGate() {
		return gate;
	}

	@Override
	public ICircuitGate getGateClone() {
		return ((ICloneableGate) gate).clone();
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
	public boolean getOutputValue(final int index) {
		return gate.getOutputValue(index);
	}

	@Override
	public Point getPosition() {
		return new Point(position);
	}

	@Override
	public Collection<ICircuitGate> getRecoupledTo() {
		return gate.getRecoupledTo();
	}

	@Override
	public boolean isFullyConnected() {
		return gate.isFullyConnected();
	}

	@Override
	public void move(final int deltaX, final int deltaY) {
		position = new Point(position.x + deltaX, position.y + deltaY);
	}

	@Override
	public void overwriteGate(final ICircuitGate g) {
		gate.overwriteGate(g);
	}

	@Override
	public void setPosition(final Point position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return gate.toString();
	}

	@Override
	public boolean update() {
		return gate.update();
	}

}
