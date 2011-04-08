import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.chl.tda367.booleancircuits.view.Palette;

public class testPalette extends javax.swing.JPanel {

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	private final Palette palette;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new testPalette());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public testPalette() {
		super();
		initGUI();
		palette = new Palette();
		this.add(palette.getView(), BorderLayout.CENTER);
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			setLayout(new BorderLayout());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
