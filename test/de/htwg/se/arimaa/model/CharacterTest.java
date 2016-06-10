package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.util.character.Position;

public class CharacterTest {

	Character character;
	Position p;
	
	@Before
	public void setUp() throws Exception {
		p = new Position(0, 0);
		character = new Character(p, CHARAKTER_NAME.R);
	}

	@Test
	public void testsetPositon(){
		character.setPosition(1, 1);
		p = new Position(1, 1);

		assertTrue(p.equals(character.getPosition()));
	}
	
	
	@Test
	public void testgetName(){
		assertEquals(CHARAKTER_NAME.R, character.getName());
	}
}
