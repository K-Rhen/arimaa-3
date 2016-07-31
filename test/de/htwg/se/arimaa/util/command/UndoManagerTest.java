package de.htwg.se.arimaa.util.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.util.command.UndoManager;

public class UndoManagerTest {

	UndoManager undoManager;
	TestReceiver sum;
	TestUndoableCommand testObj;

	@Before
	public void setup() {
		undoManager = new UndoManager();
		sum = new TestReceiver();
		testObj = new TestUndoableCommand(sum);
	}

	@Test
	public void testDoUndoRedo() {
		undoManager.doCommand(testObj);
		assertEquals(1, sum.getSum());
		undoManager.undoCommand();
		assertEquals(0, sum.getSum());
		undoManager.redoCommand();
		assertEquals(1, sum.getSum());
	}

	@Test
	public void testEmptyUndoStack() {
		undoManager.undoCommand();
		assertEquals(0, sum.getSum());
		undoManager.redoCommand();
		assertEquals(0, sum.getSum());

	}

	@Test
	public void testReset() {
		undoManager.reset();
		assertEquals("", undoManager.toString());
	}

	@Test
	public void testToString() {
		undoManager.doCommand(testObj);

		String ougthText = "1 1\n";
		String isText = undoManager.toString();

		assertEquals(ougthText, isText);
	}

}
