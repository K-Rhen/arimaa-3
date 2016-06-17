package de.htwg.se.arimaa.util.character;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PositionTest {

	Position position;

	@Before
	public void setUp() throws Exception {
		position = new Position(0, 1);
	}

	@Test
	public void testgetPosition() {
		assertEquals(0, position.getX());
		assertEquals(1, position.getY());
		position.setPositon(1, 0);
		assertEquals(1, position.getX());
		assertEquals(0, position.getY());
	}

	@Test
	public void testpositionOnPitch() {
		Position end = new Position(0, 0);
		assertTrue(Position.positionOnPitch(end));

		end = new Position(7, 7);
		assertTrue(Position.positionOnPitch(end));
	}

	@Test
	public void testIlligalArgumentConstrukor() {
		testIlligalArgumentConstrukorhelper(-1, 2);
		testIlligalArgumentConstrukorhelper(1, 8);
		testIlligalArgumentConstrukorhelper(8, 1);
		testIlligalArgumentConstrukorhelper(2, -1);
	}

	private void testIlligalArgumentConstrukorhelper(int x, int y) {
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
	public void testequal() {
		Position pos = new Position(0, 1);

		assertTrue(position.equals(pos));
		pos.setPositon(1, 0);
		assertFalse(position.equals(pos));
		pos.setPositon(0, 0);
		assertFalse(position.equals(pos));

		assertFalse(position.equals(null));
	}

	@Test
	public void testhashCode() {
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, 0);
		assertEquals(pos1.hashCode(), pos2.hashCode());
		
		Position pos3 = new Position(1, 1);
		assertNotEquals(pos1.hashCode(), pos3.hashCode());
	}
	
	@Test
	public void testnextTo(){
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, 1);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(0, 0);
		pos2 = new Position(1, 0);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(7, 0);
		pos2 = new Position(6, 0);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(7, 0);
		pos2 = new Position(7, 1);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(0, 7);
		pos2 = new Position(0, 6);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(0, 7);
		pos2 = new Position(1, 7);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(7, 7);
		pos2 = new Position(6, 7);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(7, 7);
		pos2 = new Position(7, 6);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(3, 0);
		pos2 = new Position(3, 1);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(3, 7);
		pos2 = new Position(3, 6);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(0, 3);
		pos2 = new Position(1, 3);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(7, 3);
		pos2 = new Position(6, 3);
		assertTrue(position.nextTo(pos1, pos2));
		
		pos1 = new Position(1, 1);
		pos2 = new Position(1, 3);
		assertFalse(position.nextTo(pos1, pos2));
		
		pos1 = new Position(1, 3);
		pos2 = new Position(3, 3);
		assertFalse(position.nextTo(pos1, pos2));
		
		pos1 = new Position(1, 3);
		pos2 = new Position(1, 3);
		assertFalse(position.nextTo(pos1, pos2));
	}
}
