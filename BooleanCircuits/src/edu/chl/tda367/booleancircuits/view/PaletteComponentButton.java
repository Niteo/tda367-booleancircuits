package edu.chl.tda367.booleancircuits.view;

import javax.swing.*;

public final class PaletteComponentButton extends JPanel {
	
	JLabel componentNameLabel;
	//H�rdkodning tills vi implementerat "Component", inparametern kommer med andra ord vara en Component
	PaletteComponentButton(String name){
		componentNameLabel = new JLabel();
		componentNameLabel.setText(name);
		add(componentNameLabel);
	}
	
	public String getText(){
		return componentNameLabel.getText();
	}
}
