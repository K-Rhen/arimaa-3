package model;

import model.impl.Figure;
import model.impl.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {
    IPlayer player;
    List<IFigure> figures;

    @BeforeEach
    void setUp() {
        figures = new ArrayList<>();
        figures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
        figures.add(new Figure(new Position(1, 0), FIGURE_NAME.R));

        player = new Player(PLAYER_NAME.GOLD, figures);
    }

    @Test
    void testGetName() {
        assertEquals(PLAYER_NAME.GOLD, player.getPlayerName());
    }

    @Test
    void testMoveFigure() {
        player.moveFigure(new Position(1, 0), new Position(1, 1));

        assertEquals(null, player.getFigure(new Position(1, 0)));
        assertEquals(FIGURE_NAME.R, player.getFigure(new Position(1, 1)));
    }

    @Test
    void testMoveFigureException() {
        assertThrows(IllegalArgumentException.class, () ->
                player.moveFigure(new Position(1, 1), new Position(1, 0)));
    }

    @Test
    void testGetFigure() {
        assertEquals(FIGURE_NAME.R, player.getFigure(new Position(0, 0)));
        assertEquals(null, player.getFigure(new Position(3, 4)));

        player.disableFigure(new Position(0, 0));
        assertEquals(null, player.getFigure(new Position(0, 0)));
    }

    @Test
    void testDisableFigure() {
        assertFalse(player.disableFigure(new Position(0, 1)));
        assertTrue(player.disableFigure(new Position(0, 0)));
        assertEquals(null, player.getFigure(new Position(0, 0)));
    }

    @Test
    void testGetFigures() {
        player.disableFigure(new Position(0, 0));
        List<IFigure> figuresList = player.getFigures();
        IFigure figure = new Figure(new Position(0, 0), FIGURE_NAME.R);

        assertFalse(figuresList.contains(figure));
    }
}
