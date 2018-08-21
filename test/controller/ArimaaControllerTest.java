package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import main.ArimaaModule;
import model.FIGURE_NAME;
import model.PLAYER_NAME;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.*;


class ArimaaControllerTest {

    private IArimaaController controller;
    private Injector injector = Guice.createInjector(new ArimaaModule());

    @BeforeEach
    void setUp() {
        controller = injector.getInstance(IArimaaController.class);
    }

    @Test
    void testCreate() {
        controller.createNewGame();
        assertEquals(GameStatus.CREATE, controller.getGameStatus());
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());
    }

    @Test
    void testGetRemainingMoves() {
        assertEquals(controller.getRemainingMoves(), 4);
    }

    @Test
    void testChangePlayer() {
        controller.changePlayer();
        assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        controller.changePlayer();
        assertEquals(PLAYER_NAME.SILVER, controller.getCurrentPlayerName());
        assertEquals(GameStatus.CHANGE_PLAYER, controller.getGameStatus());
    }

    @Test
    void testGetStatusText() {
        assertEquals("New game started", controller.getStatusText());
    }

    @Test
    void testArimaaExit() {
        controller.quitGame();
        controller.getPitchView();
    }

    @Test
    void testMoveFigure() {
        // move gold figure
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertFalse(controller.moveFigure(new Position(0, 6), new Position(0, 5)));

        controller.changePlayer();

        // move silver figure
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertFalse(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
    }

    @Test
    void testGetFigureNameByPosition() {
        assertEquals(FIGURE_NAME.R, controller.getFigureName(new Position(0, 6)));
        assertEquals(FIGURE_NAME.R, controller.getFigureName(new Position(0, 1)));

        assertEquals(null, controller.getFigureName(new Position(0, 3)));
    }

    @Test
    void testGetPlayerNameByPosition() {
        assertEquals(PLAYER_NAME.GOLD, controller.getPlayerName(new Position(0, 6)));
        assertEquals(PLAYER_NAME.SILVER, controller.getPlayerName(new Position(0, 1)));

        assertEquals(null, controller.getPlayerName(new Position(0, 2)));
    }

    @Test
    void testUndoRedo() {
        controller.moveFigure(new Position(0, 6), new Position(0, 5));
        assertEquals(null, controller.getFigureName(new Position(0, 6)));
        assertEquals(FIGURE_NAME.R, controller.getFigureName(new Position(0, 5)));

        controller.undo();
        assertEquals(FIGURE_NAME.R, controller.getFigureName(new Position(0, 6)));
        assertEquals(null, controller.getFigureName(new Position(0, 5)));

        controller.redo();
        assertEquals(null, controller.getFigureName(new Position(0, 6)));
        assertEquals(FIGURE_NAME.R, controller.getFigureName(new Position(0, 5)));
    }

    @Test
    void testGetMoveHistoryText() {
        assertEquals("", controller.getMoveHistoryText());

        controller.moveFigure(new Position(0, 6), new Position(0, 5));

        String oughtText = "1g Ra2n ";
        String isText = controller.getMoveHistoryText();
        assertEquals(oughtText, isText);
    }

    @Test
    void testIsUndoRedoListEmpty() {
        assertTrue(controller.isUndoListEmpty());
        assertTrue(controller.isRedoListEmpty());

        controller.moveFigure(new Position(0, 6), new Position(0, 5));

        assertFalse(controller.isUndoListEmpty());
        assertTrue(controller.isRedoListEmpty());

        controller.undo();

        assertTrue(controller.isUndoListEmpty());
        assertFalse(controller.isRedoListEmpty());

    }
}
