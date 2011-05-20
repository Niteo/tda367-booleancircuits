package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Popup menu for canvas
 *
 * @author Kaufmann
 *
 */
public final class CanvasPopup extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	private JMenuItem copy = new JMenuItem("Copy gate");
	private JMenuItem cut = new JMenuItem("Cut gate");
	private JMenu inputMenu = new JMenu();
	private ActionListener listener;
	private JMenu outputMenu = new JMenu();
	private JMenuItem remove = new JMenuItem("Remove gate");

	/**
	 * Creates a pop-up menu.
	 *
	 * @param l
	 *            ActionListener
	 */
	public CanvasPopup(final ActionListener listener) {
		super();

		this.listener = listener;
		remove.addActionListener(listener);
		copy.addActionListener(listener);
		cut.addActionListener(listener);
		add(outputMenu);
		add(inputMenu);
		addSeparator();
		add(copy);
		add(cut);
		addSeparator();
		add(remove);
	}

	/**
	 * Returns the index of the input button.
	 *
	 * @param item
	 * @return the index of the input button. Returns -1 if button is not an
	 *         input button
	 */
	public int getInputIndex(final JMenuItem item) {
		for (int i = 0; i < inputMenu.getItemCount(); i++) {
			if (item == inputMenu.getItem(i)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the output button.
	 *
	 * @param item
	 * @return the index of the output button. Returns -1 if button is not an
	 *         output button
	 */
	public int getOutputIndex(final JMenuItem item) {
		for (int i = 0; i < outputMenu.getItemCount(); i++) {
			if (item == outputMenu.getItem(i)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns true if the JMenuItem is a copy button.
	 *
	 * @param item
	 *            JMenuItem
	 * @return boolean
	 */
	public boolean isCopyButton(final JMenuItem item) {
		return copy == item;
	}

	/**
	 * Returns true if the JMenuItem is a remove button.
	 *
	 * @param item
	 *            JMenuItem
	 * @return boolean
	 */
	public boolean isCutButton(final JMenuItem item) {
		return cut == item;
	}

	/**
	 * Checks if item is an input button.
	 *
	 * @param item
	 *            the item to check
	 * @return true if item is input button
	 */
	public boolean isInputItem(final JMenuItem item) {
		return getInputIndex(item) != -1;
	}

	/**
	 * Checks if item is an output button.
	 *
	 * @param item
	 *            the item to check
	 * @return true if item is output button
	 */
	public boolean isOutputItem(final JMenuItem item) {
		return getOutputIndex(item) != -1;
	}

	/**
	 * Returns true if the JMenuItem is a remove button.
	 *
	 * @param item
	 *            JMenuItem
	 * @return boolean
	 */
	public boolean isRemoveButton(final JMenuItem item) {
		return remove == item;
	}

	/**
	 * Updates the menu to show items based on input parameters.
	 *
	 * @param inputs
	 *            amount of inputs in the input menu
	 * @param outputs
	 *            amount of outputs in the output menu
	 * @param showInput
	 *            if true, menu shows inputs
	 * @param showOutput
	 *            if true, menu shows inputs
	 */
	public void updateMenu(final int inputs, final int outputs,
			final boolean showInput, final boolean showOutput) {
		inputMenu.removeAll();
		outputMenu.removeAll();

		if (showInput && showOutput) {
			changeIOMenu("Connect input...", "Connect output...");
		} else if (showOutput) {
			changeIOMenu(null, "To output...");
		} else if (showInput) {
			changeIOMenu("To input...", null);
		}

		// Add inputs
		for (int i = 1; i <= inputs; i++) {
			JMenuItem temp = new JMenuItem("" + i);
			temp.addActionListener(listener);
			inputMenu.add(temp);
		}
		// Add outputs
		for (int i = 1; i <= outputs; i++) {
			JMenuItem temp = new JMenuItem("" + i);
			temp.addActionListener(listener);
			outputMenu.add(temp);
		}

		enableIOMenu(inputs > 0, outputs > 0);
	}

	private void changeIOMenu(final String input, final String output) {
		if (input == null) {
			inputMenu.setVisible(false);
		} else {
			inputMenu.setText(input);
			inputMenu.setVisible(true);
		}

		if (output == null) {
			outputMenu.setVisible(false);
		} else {
			outputMenu.setText(output);
			outputMenu.setVisible(true);
		}
	}

	private void enableIOMenu(final boolean inputEnabled,
			final boolean outputEnabled) {
		inputMenu.setEnabled(inputEnabled);
		outputMenu.setEnabled(outputEnabled);
	}
}