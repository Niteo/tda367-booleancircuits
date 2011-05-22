package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class AboutBox extends JDialog {

	private static final long serialVersionUID = 1L;
	private JSplitPane horizontalPane = new JSplitPane();
	private JLabel infoLabel = new JLabel();
	private transient ActionListener listener;
	private JButton okButton = new JButton("OK");
	private JLabel picLabel = new JLabel();
	private JSplitPane verticalPane = new JSplitPane();

	/**
	 * Returns an instance of AboutBox.
	 * 
	 * @param s
	 *            String the info text
	 * @param i
	 *            Icon to use as about image
	 */
	public AboutBox(final String s, final Icon i) {
		super();

		listener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		};

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
		picLabel.setBorder(new LineBorder(Color.BLACK));
		infoLabel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		
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

	private void setInfoText(final String info) {
		infoLabel.setText(info);
	}

	private void setPic(final Icon i) {
		picLabel.setIcon(i);
	}
}
