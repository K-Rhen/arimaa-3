package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class ModelTest {

	Character character;
	Position p;
	
	@Before
	public void setUp() throws Exception {
		p = new Position(0, 0);
		character = new Character(p, CHARAKTER_NAME.RABBIT);
	}

	@Test
	public void testsetPositon(){
		character.setPosition(1, 1);
		p = new Position(1, 1);

		assertTrue(p.equals(character.getPosition()));
	}
	
	
	@Test
	public void testgetName(){
		assertEquals(CHARAKTER_NAME.RABBIT, character.getName());
	}
}
