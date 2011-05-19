package edu.chl.tda367.booleancircuits.utilities.implementation;

import java.awt.Color;

public class Constants {
	public static final int canvasBackgroundLineDistance = 32;
	public static final int componentSize = 32;
	public static final int componentCircleRadius = 10;
	public static final int canvasDotDiameter = 1;
	public static final int clockFrequency = 500;
	public static final Color connectionLow = Color.black;
	public static final Color connectionHigh = Color.red;
	public static final Color drawSelectFill = new Color(100, 100, 240, 140);
	public static final Color drawSelectBorder = new Color(20, 20, 240, 180);
	public static final String manualPath = "manual.pdf";
	public static final String creditsText = "<html><H1>Boolean Circuits</H1><H3>Version: 1.0</H3>"
			+ "This software is developed by Robert Kaufmann, Anton Lin, "
			+ "Boel Nelson and Jennifer Panditha at Chalmers university "
			+ "of technology. It is published under GNU GPL v3.<br><br>"
			+ "<H2>Icon sources</H2>"
			+ "Save, Undo, Redo, Open<br><i>VisualPharm.com</i><br><br>"
			+ "Import circuit, Cut, Copy, Paste, Play Clock, Pause Clock<br>"
			+ "<i>www.iconarchive.com/artist/fatcow.html</i><br><br>"
			+ "Cross<br><i>www.iconarchive.com/artist/yusuke-kamiyamane.html</i></html>";
}
