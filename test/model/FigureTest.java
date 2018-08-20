package model;

import model.impl.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FigureTest {

    IFigure figure;
    Position pos;

    @BeforeEach
    public void setUp() {
        pos = new Position(0, 0);
        figure = new Figure(pos, FIGURE_NAME.R);
    }

    @Test
    public void testSetPosition() {
        figure.setPosition(new Position(1, 1));
        pos = new Position(1, 1);

        assertTrue(pos.equals(figure.getPosition()));
    }

    @Test
    public void testGetName() {
        assertEquals(FIGURE_NAME.R, figure.getName());
    }

    @Test
    public void testToString() {
        String oughtText = "{R (0, 0)}";
        assertEquals(oughtText, figure.toString());
    }
}
