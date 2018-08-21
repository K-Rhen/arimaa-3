package util.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CoordinateTest {

    @Test
    void testConvertPos() {
        // first row
        assertEquals("a8", Coordinate.convert(new Position(0, 0)));
        assertEquals("b8", Coordinate.convert(new Position(1, 0)));
        assertEquals("c8", Coordinate.convert(new Position(2, 0)));
        assertEquals("d8", Coordinate.convert(new Position(3, 0)));
        assertEquals("e8", Coordinate.convert(new Position(4, 0)));
        assertEquals("f8", Coordinate.convert(new Position(5, 0)));
        assertEquals("g8", Coordinate.convert(new Position(6, 0)));
        assertEquals("h8", Coordinate.convert(new Position(7, 0)));

        // last row
        assertEquals("a1", Coordinate.convert(new Position(0, 7)));
        assertEquals("b1", Coordinate.convert(new Position(1, 7)));
        assertEquals("c1", Coordinate.convert(new Position(2, 7)));
        assertEquals("d1", Coordinate.convert(new Position(3, 7)));
        assertEquals("e1", Coordinate.convert(new Position(4, 7)));
        assertEquals("f1", Coordinate.convert(new Position(5, 7)));
        assertEquals("g1", Coordinate.convert(new Position(6, 7)));
        assertEquals("h1", Coordinate.convert(new Position(7, 7)));
    }

    @Test
    void testConvertString() {
        // first row
        assertEquals(new Position(0, 0), Coordinate.convert("a8"));
        assertEquals(new Position(1, 0), Coordinate.convert("b8"));
        assertEquals(new Position(2, 0), Coordinate.convert("c8"));
        assertEquals(new Position(3, 0), Coordinate.convert("d8"));
        assertEquals(new Position(4, 0), Coordinate.convert("e8"));
        assertEquals(new Position(5, 0), Coordinate.convert("f8"));
        assertEquals(new Position(6, 0), Coordinate.convert("g8"));
        assertEquals(new Position(7, 0), Coordinate.convert("h8"));

        // last row
        assertEquals(new Position(0, 7), Coordinate.convert("a1"));
        assertEquals(new Position(1, 7), Coordinate.convert("b1"));
        assertEquals(new Position(2, 7), Coordinate.convert("c1"));
        assertEquals(new Position(3, 7), Coordinate.convert("d1"));
        assertEquals(new Position(4, 7), Coordinate.convert("e1"));
        assertEquals(new Position(5, 7), Coordinate.convert("f1"));
        assertEquals(new Position(6, 7), Coordinate.convert("g1"));
        assertEquals(new Position(7, 7), Coordinate.convert("h1"));
    }

    @Test
    void testCovertWrongFormat() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.convert("a9"));
    }

}