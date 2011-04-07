package edu.chl.tda367.booleancircuits.model.components;


public final class ComponentInput {
	private AbstractCircuitGate inputComponent;
	private int inputComponentPort;
	
	public ComponentInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}
	
	public AbstractCircuitGate getInputComponent(){
		return inputComponent;
	}
	
	public void setInputComponent(AbstractCircuitGate component, int port){
		this.inputComponent = component;
	}
	
	public boolean getInputValue(){
		return inputComponent.getOutputValue(inputComponentPort);
	}
}
