package edu.chl.tda367.booleancircuits.io.implementation;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import edu.chl.tda367.booleancircuits.utilities.GateFactory;

public final class FileManager implements IFileManager {

	/**
	 * Saves a circuit in form of a .txt file.
	 * 
	 * @param components
	 * @param name
	 */
	@Override
	public void saveFile(Collection<IAbstractCircuitGate> components,
			String name) {
		System.out.println("savning: " + name);
		try {
			PrintWriter saveFile = new PrintWriter(new BufferedWriter(
					new FileWriter(name)));
			List<IAbstractCircuitGate> tempList = new ArrayList<IAbstractCircuitGate>();
			saveFile.println(name);
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
					String txt = "CNCT";

					txt += " " + tempList.indexOf(gate) + " "
							+ gateInputs.indexOf(input) + " "
							+ tempList.indexOf(input.getInputComponent()) + " "
							+ input.getInputPort();
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
	public Model openFile(String path) {
		System.out.println("opening: " + path);
		File file = new File(path);
		List<AbstractCircuitGate> components = new ArrayList<AbstractCircuitGate>();

		try {
			Scanner sc = new Scanner(file);
			Model model = new Model(sc.next());
			sc.nextLine();
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
				} else {
					break;
				}
			}
			// Connecting gates
			while (sc.hasNext()) {
				if (sc.hasNext("CNCT")) {
					sc.next();

					AbstractCircuitGate toCpt = components.get(sc.nextInt());
					int inputNo = sc.nextInt();
					int fromCptNo = sc.nextInt();
					if (fromCptNo > 0) {
						AbstractCircuitGate fromCpt = components.get(fromCptNo);
						int output = sc.nextInt();
						toCpt.connectInput(inputNo, fromCpt, output);
					}
					sc.nextLine();

				} else {
					break;
				}
			}
			return model;

		} catch (FileNotFoundException e) {
			System.out.println("Specified file does not exist");
			return null;
		}
	}
}
