package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.position.Position;
import de.htwg.se.arimaa.controller.impl.PLAYER_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;

import de.htwg.se.arimaa.model.IPlayer;

public class PitchTest {
	IPitch pitch;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<IFigure> goldFigures = new ArrayList<>();
		goldFigures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		goldFigures.add(new Figure(new Position(0, 1), FIGURE_NAME.E));
		
		ArrayList<IFigure> silverFigures = new ArrayList<>();
		silverFigures.add(new Figure(new Position(0, 7), FIGURE_NAME.r));
		silverFigures.add(new Figure(new Position(0, 6), FIGURE_NAME.e));
		

		pitch = new Pitch();
		pitch.setGoldPlayerFigures(goldFigures);
		pitch.setSilverPlayerFigures(silverFigures);
	}

	@Test
	public void getGoldPlayertest() {
		assertEquals(PLAYER_NAME.GOLD, pitch.getGoldPlayer().getPlayerName());
	}

	@Test
	public void getSilverPlayertest() {
		assertEquals(PLAYER_NAME.SILVER, pitch.getSilverPlayer().getPlayerName());
	}

	@Test
	public void testToString() {
		String pitchString = pitch.toString();
		String oughtPitchString = "+-------------SILVER------------+\n"
				+ "| R |   |   |   |   |   |   |   | 8\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "| E |   |   |   |   |   |   |   | 7\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "|   |   | # |   |   | # |   |   | 6\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "|   |   |   |   |   |   |   |   | 5\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "|   |   |   |   |   |   |   |   | 4\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "|   |   | # |   |   | # |   |   | 3\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "| e |   |   |   |   |   |   |   | 2\n"
				+ "+---+---+---+---+---+---+---+---+\n"
				+ "| r |   |   |   |   |   |   |   | 1\n"
				+ "+--------------GOLD-------------+\n"
				+ "  a   b   c   d   e   f   g   h\n";

		assertTrue(oughtPitchString.equals(pitchString));
	}
		
}
