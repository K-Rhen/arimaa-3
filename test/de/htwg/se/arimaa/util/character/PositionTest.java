package de.htwg.se.arimaa.util.character;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.Player;

public class PositionTest {
	
	Position position;
	
	@Before
	public void setUp() throws Exception{
		position = new Position(0, 1);
	}
	@Test
	public void testgetPosition(){
		assertEquals(0, position.getX());
		assertEquals(1, position.getY());
		position.setPositon(1, 0);
		assertEquals(1, position.getX());
		assertEquals(0, position.getY());
	}
	
	@Test
	public void testequal(){
		Position po = new Position(0, 1);
		
		assertTrue(position.equals(po));
		po.setPositon(1, 0);
		assertFalse(position.equals(po));
		
		assertFalse(position.equals(new Player("Alias")));
		
	
	}

}
