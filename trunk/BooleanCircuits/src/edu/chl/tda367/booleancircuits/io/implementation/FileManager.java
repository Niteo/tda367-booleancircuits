package edu.chl.tda367.booleancircuits.io.implementation;

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

import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateInput;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

public final class FileManager implements IFileManager {

	/**
	 * Saves a circuit in form of a .txt file.
	 * 
	 * @param components
	 * @param name
	 */
	@Override
	public void saveFile(List<AbstractCircuitGate> components, String name) {

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

					txt += " " + components.indexOf(gate) + " "
							+ gateInputs.indexOf(input) + " "
							+ components.indexOf(input.getInputComponent())
							+ " " + input.getInputPort();
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
	@Override
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
			// Connecting gates
			while (sc.hasNext()) {
				if (sc.hasNext("CNCT")) {
					sc.next();
					/*
					 * AbstractCircuitGate toCpt = components.get(sc.nextInt());
					 * int inputNo = sc.nextInt(); AbstractCircuitGate fromCpt =
					 * components.get(sc.nextInt()); int output = sc.nextInt();
					 */
					components.get(sc.nextInt()).connectInput(sc.nextInt(),
							components.get(sc.nextInt()), sc.nextInt());

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
