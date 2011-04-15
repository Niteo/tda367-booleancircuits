package edu.chl.tda367.booleancircuits.model.components;

import java.util.List;

public interface IGateStrategy {

	public void updateOutput(List <Boolean> input,List <Boolean> output);
}
