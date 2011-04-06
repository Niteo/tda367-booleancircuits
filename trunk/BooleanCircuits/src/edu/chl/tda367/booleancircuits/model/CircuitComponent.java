package edu.chl.tda367.booleancircuits.model;

public class CircuitComponent {
	private Coordinate coordinate;
	public Coordinate getPosition(){
		return coordinate.clone(); 
	}
	public void setPosition(Coordinate coordinate){
		this.coordinate = coordinate.clone();
	}
	public void setConnectedOutput(ICircuitComponent component){
		throw new UnsupportedOperationException();
		//TODO: Write
	}
	public void update(){
		// TODO: Write
		throw new UnsupportedOperationException();
	}
	
	// TODO: THINK!!
}