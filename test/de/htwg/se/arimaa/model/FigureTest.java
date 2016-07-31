package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.util.position.Position;

public class FigureTest {

	IFigure figure;
	Position pos;

	@Before
	public void setUp() throws Exception {
		pos = new Position(0, 0);
		figure = new Figure(pos, FIGURE_NAME.R);
	}

	@Test
	public void testSetPositon() {
		figure.setPosition(new Position(1, 1));
		pos = new Position(1, 1);

		assertTrue(pos.equals(figure.getPosition()));
	}

	@Test
	public void testGetName() {
		assertEquals(FIGURE_NAME.R, figure.getName());
	}

	@Test
	public void testToString() {
		String ougthText = "{R (0, 0)}";
		assertEquals(ougthText, figure.toString());
	}
}
