package edu.chl.tda367.booleancircuits.view;

import java.awt.*;
import javax.swing.*;

import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.ModelManager;

/**
 * A class that represents a workspace with a panel and potentially several tabs.
 * @author Boel
 *
 */
public class CenterStage {

	private JPanel centerStagePanel = new JPanel();
	private JPanel tabPanel = new JPanel();
	private Canvas canvas = new Canvas();

	/** Returns an instance of Canvas. */
	public CenterStage() {
		tabPanel.setLayout(new FlowLayout());
		centerStagePanel.setLayout(new BorderLayout());
		centerStagePanel.add(tabPanel, BorderLayout.NORTH);
		centerStagePanel.add(canvas.getCanvas(), BorderLayout.CENTER);
	}
	
	private void newTab(String s, Color c){
		tabPanel.add(new Tab(s, c));
	}

	public void update(ModelManager mm) {
		tabPanel.removeAll();
		for(Model m  : mm.getWorkspaces()){
			if(m == mm.getActiveWorkspace()){
				newTab(m.toString(), new Color(228,228,255));
			} else{
				newTab(m.toString(), new Color(212,208,200));
			}
		}
		tabPanel.repaint();
		tabPanel.revalidate();
	}
	
	/**
	 * Returns the panel of the canvas.
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel() {
		return centerStagePanel;
	}

}
