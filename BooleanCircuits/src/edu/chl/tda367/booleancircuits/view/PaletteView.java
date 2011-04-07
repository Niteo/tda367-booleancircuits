package edu.chl.tda367.booleancircuits.view;

import java.util.List;

import javax.swing.JPanel;

public final class PaletteView {

	private final JPanel paletteView;
	private final List<ComponentFolder> folderList;

	// kolla JTree...
	public PaletteView(List<ComponentFolder> folderList) {

		this.folderList = folderList;
		paletteView = new JPanel();

	}
}
