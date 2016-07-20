package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.FIGURE_NAME;
import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.util.position.Position;

public class FigureTest {

	Figure character;
	Position p;

	@Before
	public void setUp() throws Exception {
		p = new Position(0, 0);
		character = new Figure(p, FIGURE_NAME.R);
	}

	@Test
	public void testsetPositon() {
		character.setPosition(new Position(1,1));
		p = new Position(1, 1);

		assertTrue(p.equals(character.getPosition()));
	}

	@Test
	public void testgetName() {
		assertEquals(FIGURE_NAME.R, character.getName());
	}
}
