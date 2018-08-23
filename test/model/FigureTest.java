package model;

import model.impl.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FigureTest {

    IFigure figure;
    Position pos;

    @BeforeEach
    void setUp() {
        pos = new Position(0, 0);
        figure = new Figure(pos, FIGURE_NAME.R);
    }

    @Test
    void testSetPosition() {
        figure.setPosition(new Position(1, 1));
        pos = new Position(1, 1);

        assertEquals(new Position(1, 1), figure.getPosition());
    }

    @Test
    void testGetName() {
        assertEquals(FIGURE_NAME.R, figure.getName());
    }

    @Test
    void testToString() {
        String oughtText = "{R (0, 0)}";
        assertEquals(oughtText, figure.toString());
    }
}
