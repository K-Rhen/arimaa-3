package util.command;

import controller.impl.MoveFigureCommand;
import model.FIGURE_NAME;
import model.IPitch;
import model.impl.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.*;


class UndoManagerTest {

    UndoManager undoManager;
    IPitch pitch;

    @BeforeEach
    void setup() {
        undoManager = new UndoManager();
        pitch = new Pitch();
    }

    @Test
    void testDoUndoRedo() {
        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertNull(pitch.getFigureNameForPitch(new Position(0, 6)));
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 5)));

        undoManager.undoCommand();
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 6)));
        assertNull(pitch.getFigureNameForPitch(new Position(0, 5)));

        undoManager.redoCommand();
        assertNull(pitch.getFigureNameForPitch(new Position(0, 6)));
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 5)));
    }

    @Test
    void testEmptyUndoStack() {
        String p = pitch.toString();
        undoManager.undoCommand();
        assertEquals(p, pitch.toString());
        undoManager.redoCommand();
        assertEquals(p, p);
    }

    @Test
    void testReset() {
        undoManager.reset();
        assertEquals("", undoManager.toString());

        assertTrue(undoManager.isUndoListEmpty());
        assertTrue(undoManager.isRedoListEmpty());
    }

    @Test
    void testGetLastMoveFigureName() {
        assertNull(undoManager.getLastMoveFigureName());

        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertEquals(FIGURE_NAME.R, undoManager.getLastMoveFigureName());
    }

    @Test
    void testGetLastMoveFromPosition() {
        assertNull(undoManager.getLastMoveFromPosition());

        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertEquals(new Position(0, 6), undoManager.getLastMoveFromPosition());
    }

    @Test
    void testToString() {
        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        pitch.changePlayer();
        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 1), new Position(0, 2)));

        String oughtText = "1g Ra2n pass \n1s ra7s ";
        String isText = undoManager.toString();
        assertEquals(oughtText, isText);
    }

}
