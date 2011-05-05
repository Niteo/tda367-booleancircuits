package edu.chl.tda367.booleancircuits.io;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateInput;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

public class FileManager {

	/**
	 * Saves a circuit in form of a .txt file.
	 * 
	 * @param components
	 * @param name
	 */
	public void saveCircuit(List<AbstractCircuitGate> components, String name) {

		try {
			PrintWriter saveFile = new PrintWriter(new BufferedWriter(
					new FileWriter(name)));
			// Print all gates
			for (AbstractCircuitGate gate : components) {
				String txt = "ADD";
				txt += gate.toString() + " " + gate.getNoOfInputs() + " "
						+ gate.getNoOfOutputs() + " " + gate.getPosition().x
						+ " " + gate.getPosition().y;
				txt.toUpperCase();
				saveFile.println(txt);
			}
			// Print all connections
			for (AbstractCircuitGate gate : components) {
				List<GateInput> gateInputs = gate.getInputs();

				for (GateInput input : gateInputs) {
					String txt = "CNCT";

					txt += " " + components.indexOf(input.getInputComponent())
							+ " " + input.getInputPort() + " "
							+ components.indexOf(gate) + " "
							+ gateInputs.indexOf(input);
					saveFile.println(txt);
				}
			}

			saveFile.close();
		} catch (IOException e) {
			System.out.println("maddafacka you!");
			e.printStackTrace();
		}
	}

	/**
	 * Opens a saved circuit by reading a saved .txt file.
	 */
	public void openFile(File file) {
		List<AbstractCircuitGate> components = new ArrayList<AbstractCircuitGate>();

		try {
			Scanner sc = new Scanner(file);
			// Create gates
			while (sc.hasNext()) {
				if (sc.hasNext("ADD")) {

					sc.next();
					String name = sc.next();
					int noOfInputs = sc.nextInt();
					sc.next();
					Point position = new Point(sc.nextInt(), sc.nextInt());
					AbstractCircuitGate component = GateFactory
							.getNewComponent(name, noOfInputs);
					component.setPosition(position);
					components.add(component);
					sc.nextLine();
				} else {
					break;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Specified file does not exist");
			e.printStackTrace();
		}

	}
}
