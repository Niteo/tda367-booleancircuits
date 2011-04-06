package edu.chl.tda367.booleancircuits.view;


import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public final class PaletteView {
	
	List<String> folderNames;
	List<String> componentNames;
	List<PaletteFolderButton> folderButtonList;
	
	JPanel paletteView;
	
	public PaletteView(){
		
		//Hårdkodning av folderNames tillsvidare
		folderNames = new ArrayList<String>();
		folderNames.add("Hallå");
		folderNames.add("Hejdå");
				
		paletteView = new JPanel();
		paletteView.setLayout(new BoxLayout(paletteView,BoxLayout.Y_AXIS));
		
		initFolderButtons();
	}	
	/**
	 * Initiates all the folderButtons
	 */
	public void initFolderButtons(){
		
		folderButtonList = new ArrayList<PaletteFolderButton>();
		
		for(int i = 0; i < folderNames.size(); i++){
			folderButtonList.add(new PaletteFolderButton(folderNames.get(i)));
		}		
	}
	/**
	 * Adds all folderButtons to palette
	 */
	public void addFolderButtons(){
		for(int i = 0; i < folderButtonList.size(); i++){
			paletteView.add(folderButtonList.get(i));
		}
	}
}
