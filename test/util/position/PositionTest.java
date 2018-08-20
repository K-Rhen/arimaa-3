package util.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(0, 1);
    }

    @Test
    public void testGetPosition() {
        assertEquals(0, position.getX());
        assertEquals(1, position.getY());
        position.setPosition(1, 0);
        assertEquals(1, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testPositionOnPitch() {
        Position end = new Position(0, 0);
        assertTrue(Position.positionOnPitch(end));

        end = new Position(7, 7);
        assertTrue(Position.positionOnPitch(end));
    }

    @Test
    public void illegalArgumentConstructor() {
        illegalArgumentConstructorHelper(-1, 2);
        illegalArgumentConstructorHelper(1, 8);
        illegalArgumentConstructorHelper(8, 1);
        illegalArgumentConstructorHelper(2, -1);
    }

    private void illegalArgumentConstructorHelper(int x, int y) {
        @SuppressWarnings("unused")
        Position test = null;
        boolean catched = false;
        try {
            test = new Position(x, y);
        } catch (Exception e) {
            catched = true;
        }
        assertTrue(catched);
    }

    @Test
    public void testEqual() {
        assertEquals(new Position(0, 1), position);
        assertEquals(position, position);
        assertNotEquals(new Position(1, 0), position);
        assertNotEquals(new Position(0, 0), position);
        assertNotEquals(position, null);
    }

    @Test
    public void testHashCode() {
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(0, 0);
        assertEquals(pos1.hashCode(), pos2.hashCode());

        Position pos3 = new Position(1, 1);
        assertNotEquals(pos1.hashCode(), pos3.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("(0, 1)", position.toString());
    }

    @Test
    public void getSurroundPositionForPitch() {
        List<Position> leftUpperCorner = Position.getSurroundPositionForPitch(new Position(0, 0));
        assertEquals(2, leftUpperCorner.size());
        assertTrue(leftUpperCorner.contains(new Position(0, 1)));
        assertTrue(leftUpperCorner.contains(new Position(1, 0)));

        List<Position> rightUpperCorner = Position.getSurroundPositionForPitch(new Position(7, 0));
        assertEquals(2, rightUpperCorner.size());
        assertTrue(rightUpperCorner.contains(new Position(6, 0)));
        assertTrue(rightUpperCorner.contains(new Position(7, 1)));

        List<Position> leftBottomCorner = Position.getSurroundPositionForPitch(new Position(0, 7));
        assertEquals(2, leftBottomCorner.size());
        assertTrue(leftBottomCorner.contains(new Position(1, 7)));
        assertTrue(leftBottomCorner.contains(new Position(0, 6)));

        List<Position> rightBottomCorner = Position.getSurroundPositionForPitch(new Position(7, 7));
        assertEquals(2, rightBottomCorner.size());
        assertTrue(rightBottomCorner.contains(new Position(6, 7)));

        assertTrue(rightBottomCorner.contains(new Position(7, 6)));
        List<Position> middle = Position.getSurroundPositionForPitch(new Position(3, 3));
        assertEquals(4, middle.size());
        assertTrue(middle.contains(new Position(3, 2)));
        assertTrue(middle.contains(new Position(3, 4)));
        assertTrue(middle.contains(new Position(2, 3)));
        assertTrue(middle.contains(new Position(4, 3)));
    }

    @Test
    public void testGetDirection() {
        assertEquals(DIRECTION.NORTH, Position.getDirection(new Position(3, 3), new Position(3, 2)));
        assertEquals(DIRECTION.EAST, Position.getDirection(new Position(3, 3), new Position(4, 3)));
        assertEquals(DIRECTION.SOUTH, Position.getDirection(new Position(3, 3), new Position(3, 4)));
        assertEquals(DIRECTION.WEST, Position.getDirection(new Position(3, 3), new Position(2, 3)));
        assertEquals(null, Position.getDirection(new Position(3, 3), new Position(0, 0)));
    }

}
