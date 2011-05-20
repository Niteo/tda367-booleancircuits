package edu.chl.tda367.booleancircuits.io.implementation;

import java.awt.Point;
import java.io.*;
import java.util.*;

import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.model.implementation.ModelWrapper;
import edu.chl.tda367.booleancircuits.utilities.implementation.GateFactory;

/**
 * Writes and reads save files.
 *
 * @author antonlin
 *
 */
public final class FileManager implements IFileManager {

	@Override
	public List<ICircuitGate> importFile(final File file) {
		return readFile(file);
	}

	/**
	 * Opens a saved circuit by reading a saved .txt file.
	 */
	@Override
	public ModelWrapper openFile(final File file) {

		ModelWrapper model = new ModelWrapper(file);
		List<ICircuitGate> components = readFile(file);

		for (ICircuitGate component : components) {
			model.addComponent(component, component.getPosition());
		}

		return model;

	}

	/**
	 * Saves a circuit in form of a .txt file.
	 *
	 * @param components
	 * @param name
	 */
	@Override
	public void saveFile(final Collection<ICircuitGate> components,
			final File file) {
		try {
			PrintWriter saveFile = new PrintWriter(file);
			List<ICircuitGate> tempList = new ArrayList<ICircuitGate>();
			// Print all gates
			for (ICircuitGate gate : components) {
				String txt = "ADD";
				tempList.add(gate);
				txt += " " + gate.toString() + " " + gate.getNoOfInputs() + " "
						+ gate.getNoOfOutputs() + " " + gate.getPosition().x
						+ " " + gate.getPosition().y;
				saveFile.println(txt);
			}
			// Print all connections
			for (ICircuitGate gate : components) {
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

	private List<ICircuitGate> readFile(final File file) {

		List<ICircuitGate> components = new ArrayList<ICircuitGate>();

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
					ICircuitGate component = GateFactory.getNewComponent(name,
							noOfInputs);
					component.setPosition(position);
					components.add(component);
					sc.nextLine();
				} else if (sc.hasNext("CNCT")) {
					sc.next();

					ICircuitGate toCpt = components.get(sc.nextInt());
					int inputNo = sc.nextInt();
					int fromCptNo = sc.nextInt();
					if (fromCptNo >= 0) {
						ICircuitGate fromCpt = components.get(fromCptNo);
						int output = sc.nextInt();
						toCpt.connectInput(inputNo, fromCpt, output);

					}
					sc.nextLine();

				} else {
					sc.nextLine();
				}
			}

			return components;

		} catch (FileNotFoundException e) {
			System.out.println("Specified file does not exist");
			return null;
		}
	}
}
