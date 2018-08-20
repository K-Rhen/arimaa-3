package util.command;

import controller.impl.MoveFigureCommand;
import model.FIGURE_NAME;
import model.IPitch;
import model.impl.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UndoManagerTest {

    UndoManager undoManager;
    IPitch pitch;

    @BeforeEach
    public void setup() {
        undoManager = new UndoManager();
        pitch = new Pitch();
    }

    @Test
    public void testDoUndoRedo() {
        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertEquals(null, pitch.getFigureNameForPitch(new Position(0, 6)));
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 5)));

        undoManager.undoCommand();
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 6)));
        assertEquals(null, pitch.getFigureNameForPitch(new Position(0, 5)));

        undoManager.redoCommand();
        assertEquals(null, pitch.getFigureNameForPitch(new Position(0, 6)));
        assertEquals("R", pitch.getFigureNameForPitch(new Position(0, 5)));
    }

    @Test
    public void testEmptyUndoStack() {
        String p = pitch.toString();
        undoManager.undoCommand();
        assertEquals(p, pitch.toString());
        undoManager.redoCommand();
        assertEquals(p, p);
    }

    @Test
    public void testReset() {
        undoManager.reset();
        assertEquals("", undoManager.toString());

        assertTrue(undoManager.isUndoListEmpty());
        assertTrue(undoManager.isRedoListEmpty());
    }

    @Test
    public void testGetLastMoveFigureName() {
        assertEquals(null, undoManager.getLastMoveFigureName());

        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertEquals(FIGURE_NAME.R, undoManager.getLastMoveFigureName());
    }

    @Test
    public void testGetLastMoveFromPosition() {
        assertEquals(null, undoManager.getLastMoveFromPosition());

        undoManager.doCommand(new MoveFigureCommand(pitch, new Position(0, 6), new Position(0, 5)));
        assertEquals(new Position(0, 6), undoManager.getLastMoveFromPosition());
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
