package edu.chl.tda367.booleancircuits.model.components.implementation;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.implementation.Model;

public class ConstantGateTest {
	@Test
	public void testConstantGate() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);
	}

	@Test
	public void testGetInputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		assertTrue(falseConstantGate.getInputs().size() == 0);

		ConstantGate trueConstantGate = new ConstantGate(true);
		assertTrue(trueConstantGate.getInputs().size() == 0);

	}

	@Test
	public void testUpdateOutput() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmptyGateClone() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ICircuitGate emptyGate = falseConstantGate.emptyGateClone();
		
		//assertTrue(falseConstantGate instanceof emptyGate.);
	}

	@Test
	public void testToString() {
		String name = "name";
		assertTrue(name.toString().equals("name"));
	}

	@Test
	public void testSetOutput() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);
		
		falseConstantGate.setOutput(0, false);
		trueConstantGate.setOutput(0, true);
		
		assertTrue(falseConstantGate.getNoOfOutputs()==1);
		assertTrue(falseConstantGate.getOutputValue(0)==false);
		
		
		assertTrue(trueConstantGate.getNoOfOutputs()==1);
		assertTrue(trueConstantGate.getOutputValue(0)==true);
	}

	@Test
	public void testOverwriteGate() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectInput() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);
		
		
		ICircuitGate newComponent = new AbstractCircuitGate(1, 1){

			@Override
			public void connectInput(int inputPort,
					ICircuitGate component, int outputPort) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean connectsTo(ICircuitGate gate) {
				return false;
			}

			@Override
			public int getComponentTier() {
				return 0;
			}

			@Override
			public List<IGateInput> getInputs() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getNoOfInputs() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getNoOfOutputs() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean getOutputValue(int index) {
				return false;
			}

			@Override
			public Point getPosition() {
				return null;
			}

			@Override
			public Collection<ICircuitGate> getRecoupledTo() {
				
				return null;
			}

			@Override
			public void move(int deltaX, int deltaY) {
						
			}

			@Override
			public void overwriteGate(AbstractCircuitGate gate) {
							
			}

			@Override
			public void setPosition(Point position) {
				
			}

			@Override
			public void update() {
				
			}

			@Override
			protected AbstractCircuitGate emptyGateClone() {
				return null;
			}

			@Override
			public String toString() {
				return null;
			}

			@Override
			protected void updateOutput() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
	
		
		assertTrue(falseConstantGate.getInputs().size()==1);
	
		
	}

	@Test
	public void testGetOutputValue() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		falseConstantGate.setOutput(0, false);
		trueConstantGate.setOutput(0,true);

		assertTrue(falseConstantGate.getOutputValue(0) == false);
		assertTrue(trueConstantGate.getOutputValue(0) == true);
	}

	@Test
	public void testGetNoOfInputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		assertTrue(falseConstantGate.getNoOfInputs() == 0);
		assertTrue(trueConstantGate.getNoOfInputs() == 0);
	}

	@Test
	public void testGetNoOfOutputs() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);

		assertTrue(falseConstantGate.getNoOfOutputs() == 1);
		assertTrue(trueConstantGate.getNoOfOutputs() == 1);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComponentTier() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);
		
		falseConstantGate.setOutput(0, trueConstantGate.getOutputValue(0));
		
		assertTrue(falseConstantGate.getComponentTier()==1);
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testMove() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testClone() {
		ConstantGate falseConstantGate = new ConstantGate(false);
		ConstantGate trueConstantGate = new ConstantGate(true);
		
		ICircuitGate falseClone = falseConstantGate.clone();
		assertTrue(falseClone instanceof ConstantGate);
		assertTrue(falseClone.getNoOfInputs()==falseConstantGate.getNoOfInputs());
		assertTrue(falseClone.getNoOfOutputs()==falseConstantGate.getNoOfOutputs());
		
		ICircuitGate trueClone = trueConstantGate.clone();
		assertTrue(trueClone instanceof ConstantGate);
		assertTrue(trueClone.getNoOfInputs()==trueConstantGate.getNoOfInputs());
		assertTrue(trueClone.getNoOfOutputs()==trueConstantGate.getNoOfOutputs());
	}

}
