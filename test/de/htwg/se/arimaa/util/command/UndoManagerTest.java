package de.htwg.se.arimaa.util.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.controller.impl.MoveFigureCommand;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.position.Position;
import jdk.net.NetworkPermission;

public class UndoManagerTest {

	UndoManager undoManager;
	IPitch pitch;

	@Before
	public void setup() {
		undoManager = new UndoManager();
		pitch = new Pitch();
	}

	@Test
	public void testDoUndoRedo() {
		undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
		assertEquals(null, pitch.getFigureNameForPitch(new Position(0,6)));		
		assertEquals("R", pitch.getFigureNameForPitch(new Position(0,5)));
		
		undoManager.undoCommand();
		assertEquals("R", pitch.getFigureNameForPitch(new Position(0,6)));
		assertEquals(null, pitch.getFigureNameForPitch(new Position(0,5)));
		
		undoManager.redoCommand();
		assertEquals(null, pitch.getFigureNameForPitch(new Position(0,6)));
		assertEquals("R", pitch.getFigureNameForPitch(new Position(0,5)));
	}

	@Test
	public void testEmptyUndoStack() {
		String p = pitch.toString();
		undoManager.undoCommand();
		assertEquals(p, pitch.toString());
		undoManager.redoCommand();
		assertEquals(p, p.toString());
	}

	@Test
	public void testReset() {
		undoManager.reset();
		assertEquals("", undoManager.toString());
		
		assertTrue(undoManager.isUndoListEmpty());
		assertTrue(undoManager.isRedoListEmpty());
	}

	@Test
	public void testToString() {
		undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
		pitch.changePlayer();
		undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 1), new Position(0, 2)));

		String ougthText = "1g Ra2n pass \n1s ra7s ";
		String isText = undoManager.toString();
		assertEquals(ougthText, isText);
	}

}
