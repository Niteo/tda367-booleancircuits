package edu.chl.tda367.booleancircuits.io.implementation;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.Model;
import edu.chl.tda367.booleancircuits.model.implementation.ModelWrapper;
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

public final class FileManager implements IFileManager {

	/**
	 * Saves a circuit in form of a .txt file.
	 * 
	 * @param components
	 * @param name
	 */
	@Override
	public void saveFile(Collection<IAbstractCircuitGate> components, File file) {
		try {
			PrintWriter saveFile = new PrintWriter(file);
			List<IAbstractCircuitGate> tempList = new ArrayList<IAbstractCircuitGate>();
			// Print all gates
			for (IAbstractCircuitGate gate : components) {
				String txt = "ADD";
				tempList.add(gate);
				txt += " " + gate.toString() + " " + gate.getNoOfInputs() + " "
						+ gate.getNoOfOutputs() + " " + gate.getPosition().x
						+ " " + gate.getPosition().y;
				txt.toUpperCase();
				saveFile.println(txt);
			}
			// Print all connections
			for (IAbstractCircuitGate gate : components) {
				List<IGateInput> gateInputs = gate.getInputs();

				for (IGateInput input : gateInputs) {
					if (input.getInputComponent() != null) {
						String txt = "CNCT";
						txt += " " + tempList.indexOf(gate) + " "
								+ gateInputs.indexOf(input) + " "
								+ tempList.indexOf(input.getInputComponent())
								+ " " + input.getInputPort();
						saveFile.println(txt);
					}
				}
			}

			saveFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a saved circuit by reading a saved .txt file.
	 */
	@Override
	public ModelWrapper openFile(File file) {
		List<AbstractCircuitGate> components = new ArrayList<AbstractCircuitGate>();

		try {
			Scanner sc = new Scanner(file);
			ModelWrapper model = new ModelWrapper(file);
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
					model.addComponent(component, component.getPosition());
					sc.nextLine();
				} else if (sc.hasNext("CNCT")) {
					sc.next();

					AbstractCircuitGate toCpt = components.get(sc.nextInt());
					int inputNo = sc.nextInt();
					int fromCptNo = sc.nextInt();
					if (fromCptNo >= 0) {
						AbstractCircuitGate fromCpt = components.get(fromCptNo);
						int output = sc.nextInt();
						toCpt.connectInput(inputNo, fromCpt, output);
						System.out.println("jasega");
					}
					sc.nextLine();

				} else {
					sc.nextLine();
				}
			}
			/*
			 * // Connecting gates while (sc.hasNext()) { if
			 * (sc.hasNext("CNCT")) { sc.next();
			 * 
			 * AbstractCircuitGate toCpt = components.get(sc.nextInt()); int
			 * inputNo = sc.nextInt(); int fromCptNo = sc.nextInt(); if
			 * (fromCptNo >= 0) { AbstractCircuitGate fromCpt =
			 * components.get(fromCptNo); int output = sc.nextInt();
			 * toCpt.connectInput(inputNo, fromCpt, output);
			 * System.out.println("jasega"); } sc.nextLine();
			 * 
			 * } else { sc.nextLine(); } }
			 */
			return model;

		} catch (FileNotFoundException e) {
			System.out.println("Specified file does not exist");
			return null;
		}
	}
}
