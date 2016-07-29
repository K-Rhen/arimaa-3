package de.htwg.se.arimaa.util.position;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testConvertPos() {
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
	public void testConvertString() {
		// first row
		assertTrue(Coordinate.convert("a8").equals(new Position(0, 0)));
		assertTrue(Coordinate.convert("b8").equals(new Position(1, 0)));
		assertTrue(Coordinate.convert("c8").equals(new Position(2, 0)));
		assertTrue(Coordinate.convert("d8").equals(new Position(3, 0)));
		assertTrue(Coordinate.convert("e8").equals(new Position(4, 0)));
		assertTrue(Coordinate.convert("f8").equals(new Position(5, 0)));
		assertTrue(Coordinate.convert("g8").equals(new Position(6, 0)));
		assertTrue(Coordinate.convert("h8").equals(new Position(7, 0)));
	
		// last row
		assertTrue(Coordinate.convert("a1").equals(new Position(0, 7)));
		assertTrue(Coordinate.convert("b1").equals(new Position(1, 7)));
		assertTrue(Coordinate.convert("c1").equals(new Position(2, 7)));
		assertTrue(Coordinate.convert("d1").equals(new Position(3, 7)));
		assertTrue(Coordinate.convert("e1").equals(new Position(4, 7)));
		assertTrue(Coordinate.convert("f1").equals(new Position(5, 7)));
		assertTrue(Coordinate.convert("g1").equals(new Position(6, 7)));
		assertTrue(Coordinate.convert("h1").equals(new Position(7, 7)));
	
	}
	
}