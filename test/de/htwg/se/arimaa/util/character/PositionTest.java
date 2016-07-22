package de.htwg.se.arimaa.util.character;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.util.position.Position;


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
	public void testToString(){
		String target = "(0, 1)";
		assertEquals(target, position.toString());
	}
}
