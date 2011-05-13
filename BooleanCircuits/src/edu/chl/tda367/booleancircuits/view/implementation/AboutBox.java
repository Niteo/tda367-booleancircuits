package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AboutBox extends JDialog{
	
	private JLabel infoLabel = new JLabel();
	private JLabel picLabel = new JLabel();
	private JButton okButton = new JButton("OK");
	private JSplitPane verticalPane = new JSplitPane();
	private JSplitPane horizontalPane = new JSplitPane();
	private ActionListener listener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
	
	/**
	 * Returns an instance of aboutbox.
	 * @param s String the info text
	 */
	public AboutBox(String s, Icon i){
		super();
		setAlwaysOnTop(true);
		setVisible(true);
		setTitle("About");
		setSize(new Dimension(600, 400));
		setResizable(false);
		setInfoText(s);
		setPic(i);
		verticalPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		horizontalPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		okButton.addActionListener(listener);
		
		add(horizontalPane);
		horizontalPane.setDividerLocation(150);
		horizontalPane.setRightComponent(verticalPane);
		horizontalPane.setLeftComponent(picLabel);
		horizontalPane.setEnabled(false);
		horizontalPane.setDividerSize(0);
		
		verticalPane.setDividerLocation(340);
		verticalPane.setTopComponent(infoLabel);
		verticalPane.setBottomComponent(okButton);
		verticalPane.setEnabled(false);
		verticalPane.setDividerSize(0);
		
		setModal(true);
	}
	
	private void setInfoText(String info){
		infoLabel.setText(info);
	}
	
	private void setPic(Icon i){
		picLabel.setIcon(i);
	}
}
