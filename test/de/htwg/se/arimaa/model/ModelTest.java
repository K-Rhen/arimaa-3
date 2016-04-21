package de.htwg.se.arimaa.model;

import org.junit.Before;


import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class ModelTest {

	Character character;

	@Before
	public void setUp() throws Exception {
		Position p = new Position(0, 0);
		character = new Character(p, CHARAKTER_NAME.RABBIT);
	}

}
