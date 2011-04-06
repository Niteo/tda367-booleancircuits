package edu.chl.tda367.booleancircuits.view;

import java.util.List;
import javax.swing.*;

public final class PaletteFolderButton extends JPanel {
	
	JLabel folderName;
	JPanel componentButtonsContainer;
	JSplitPane splitPane;
	//String kommer bytas ut mot Component när sistnämnda implementerats
	List<String> componentList; 
	
	boolean isOpened;
	
	public PaletteFolderButton(String name){
		isOpened = false;
		folderName = new JLabel();
		folderName.setText(name);
		
		componentButtonsContainer = new JPanel();
		componentButtonsContainer.setLayout(new BoxLayout(componentButtonsContainer,BoxLayout.Y_AXIS));
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(folderName);
		splitPane.setBottomComponent(componentButtonsContainer);
	}
	/**
	 * Inserts a panel representing a component in to folder.
	 */
	public void addComponent(JPanel componentButton){
		componentButtonsContainer.add(componentButton);
	}
	/**
	 * toggles if selected folder on palette is open or closed
	 */
	public void toggleIsOpened(){
		isOpened = !isOpened;
	}

}
