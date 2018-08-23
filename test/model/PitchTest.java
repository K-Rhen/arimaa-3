package model;

import model.impl.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.*;


class PitchTest {
    IPitch pitch;

    @BeforeEach
    void setUp() {
        pitch = new Pitch();
    }

    @Test
    void testGetGoldPlayerTest() {
        assertEquals(PLAYER_NAME.GOLD, pitch.getGoldPlayer().getPlayerName());
    }

    @Test
    void testGetSilverPlayerTest() {
        assertEquals(PLAYER_NAME.SILVER, pitch.getSilverPlayer().getPlayerName());
    }

    @Test
    void testGetPlayerName() {
        assertEquals(PLAYER_NAME.GOLD, pitch.getPlayerName(new Position(0, 6)));
        assertEquals(PLAYER_NAME.SILVER, pitch.getPlayerName(new Position(0, 1)));
        assertNull(pitch.getPlayerName(new Position(3, 3)));
    }

    @Test
    void testSetCurrentPlayer() {
        assertEquals(PLAYER_NAME.GOLD, pitch.getCurrentPlayerName());
        pitch.setCurrentPlayer(PLAYER_NAME.SILVER);
        assertEquals(PLAYER_NAME.SILVER, pitch.getCurrentPlayerName());
    }

    @Test
    void testSetRemainingMoves() {
        assertEquals(4, pitch.getRemainingMoves());
        pitch.setRemainingMoves(-5);
        assertEquals(4, pitch.getRemainingMoves());
        pitch.setRemainingMoves(5);
        assertEquals(4, pitch.getRemainingMoves());

        pitch.setRemainingMoves(2);
        assertEquals(2, pitch.getRemainingMoves());
    }

    @Test
    void testGetFigureName() {
        assertNull(pitch.getFigureName(new Position(0, 2)));
        assertEquals(FIGURE_NAME.R, pitch.getFigureName(new Position(0, 0)));
        assertEquals(FIGURE_NAME.R, pitch.getFigureName(new Position(0, 7)));
    }

    @Test
    void testChangePlayer() {
        pitch.changePlayer();
        assertEquals(PLAYER_NAME.SILVER, pitch.getCurrentPlayerName());
        assertEquals(4, pitch.getRemainingMoves());
        pitch.changePlayer();
        assertEquals(PLAYER_NAME.GOLD, pitch.getCurrentPlayerName());
        assertEquals(4, pitch.getRemainingMoves());
    }

    @Test
    void testDisableFigure() {
        assertFalse(pitch.disableFigure(new Position(0, 2)));
        assertTrue(pitch.disableFigure(new Position(0, 0)));
        assertNull(pitch.getFigureName(new Position(0, 0)));
        assertNull(pitch.getPlayerName(new Position(0, 0)));
    }

    @Test
    void testNoRabbits() {
        assertFalse(pitch.noRabbits(PLAYER_NAME.GOLD));

        // Disable all Gold Rabbits
        pitch.disableFigure(new Position(0, 6));
        pitch.disableFigure(new Position(0, 7));
        pitch.disableFigure(new Position(1, 7));
        pitch.disableFigure(new Position(2, 7));
        pitch.disableFigure(new Position(5, 7));
        pitch.disableFigure(new Position(6, 7));
        pitch.disableFigure(new Position(7, 7));
        pitch.disableFigure(new Position(7, 6));

        assertTrue(pitch.noRabbits(PLAYER_NAME.GOLD));
    }

    @Test
    void testToString() {
        String isString = pitch.toString();
        String oughtPitchString = "+-------------SILVER------------+\n" + "| r | r | r | d | d | r | r | r | 8\n"
                + "+---+---+---+---+---+---+---+---+\n" + "| r | h | c | e | m | c | h | r | 7\n"
                + "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 6\n"
                + "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 5\n"
                + "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 4\n"
                + "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 3\n"
                + "+---+---+---+---+---+---+---+---+\n" + "| R | H | C | M | E | C | H | R | 2\n"
                + "+---+---+---+---+---+---+---+---+\n" + "| R | R | R | D | D | R | R | R | 1\n"
                + "+--------------GOLD-------------+\n" + "  a   b   c   d   e   f   g   h\n";

        assertEquals(oughtPitchString, isString);
    }

}
