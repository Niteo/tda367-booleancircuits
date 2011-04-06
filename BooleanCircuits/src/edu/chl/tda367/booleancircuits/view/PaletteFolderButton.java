package edu.chl.tda367.booleancircuits.view;

import java.util.List;
import javax.swing.*;

public final class PaletteFolderButton extends JPanel {
	
	JLabel folderNameLabel;
	JPanel buttonsContainerPanel;
	JSplitPane splitPane;
	//String kommer bytas ut mot Component när sistnämnda implementerats
	List<String> componentList; 
	
	boolean isOpened;
	
	public PaletteFolderButton(String name){
		isOpened = false;
		folderNameLabel = new JLabel();
		folderNameLabel.setText(name);
		
		buttonsContainerPanel = new JPanel();
		buttonsContainerPanel.setLayout(new BoxLayout(buttonsContainerPanel,BoxLayout.Y_AXIS));
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(folderNameLabel);
		splitPane.setBottomComponent(buttonsContainerPanel);
	}
	/**
	 * Inserts a panel representing a component in to folder.
	 */
	public void addComponent(JPanel componentButton){
		buttonsContainerPanel.add(componentButton);
	}
	/**
	 * toggles if selected folder on palette is open or closed
	 */
	public void toggleIsOpened(){
		isOpened = !isOpened;
	}

}
