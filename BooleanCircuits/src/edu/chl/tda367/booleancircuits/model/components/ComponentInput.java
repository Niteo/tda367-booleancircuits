package edu.chl.tda367.booleancircuits.model.components;


public final class ComponentInput {
	private AbstractCircuitComponent inputComponent;
	private int inputComponentPort;
	
	public ComponentInput(){
		inputComponent = null;
		inputComponentPort = 0;
	}
	
	public AbstractCircuitComponent getInputComponent(){
		return inputComponent;
	}
	
	public void setInputComponent(AbstractCircuitComponent component, int port){
		this.inputComponent = component;
	}
	
	public boolean getInputValue(){
		return inputComponent.getOutputValue(inputComponentPort);
	}
}
