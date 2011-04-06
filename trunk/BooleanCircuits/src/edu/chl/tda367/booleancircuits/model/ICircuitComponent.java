package edu.chl.tda367.booleancircuits.model;

public interface ICircuitComponent {
	public abstract Coordinate getPosition();
	public abstract void setPosition(Coordinate coordinate);
	public abstract void setConnectedOutput(ICircuitComponent component);
	public abstract void update();
	
	// TODO: THINK!!
}