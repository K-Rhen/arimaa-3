package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.position.Position;

public class PitchTest {
	IPitch pitch;
	ArrayList<IFigure> goldFigures = new ArrayList<>();
	ArrayList<IFigure> silverFigures = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		goldFigures.add(new Figure(new Position(0, 7), FIGURE_NAME.R));
		goldFigures.add(new Figure(new Position(0, 6), FIGURE_NAME.E));

		silverFigures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(0, 1), FIGURE_NAME.E));

		pitch = new Pitch();
		pitch.setGoldPlayerFigures(goldFigures);
		pitch.setSilverPlayerFigures(silverFigures);
	}

	@Test
	public void testGetGoldPlayertest() {
		assertEquals(PLAYER_NAME.GOLD, pitch.getGoldPlayer().getPlayerName());
	}

	@Test
	public void testGetSilverPlayertest() {
		assertEquals(PLAYER_NAME.SILVER, pitch.getSilverPlayer().getPlayerName());
	}

	@Test
	public void testGetPlayer() {
		assertEquals(pitch.getGoldPlayer(), pitch.getPlayer(PLAYER_NAME.GOLD));
		assertEquals(pitch.getSilverPlayer(), pitch.getPlayer(PLAYER_NAME.SILVER));
	}

	@Test
	public void testReduceRemainingMoves() {
		assertEquals(4,pitch.getRemainingMoves());
		
		pitch.reduceRemainingMoves(1);
		assertEquals(3,pitch.getRemainingMoves());
		
		pitch.reduceRemainingMoves(4);
		assertEquals(3,pitch.getRemainingMoves());
		
		pitch.reduceRemainingMoves(3);
		pitch.reduceRemainingMoves(3);
		assertEquals(0,pitch.getRemainingMoves());
		
	}
	

	@Test
	public void testToString() {
		String pitchString = pitch.toString();
		String oughtPitchString = "+-------------SILVER------------+\n" + "| R |   |   |   |   |   |   |   | 8\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| E |   |   |   |   |   |   |   | 7\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 6\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 5\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 4\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 3\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| e |   |   |   |   |   |   |   | 2\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| r |   |   |   |   |   |   |   | 1\n"
				+ "+--------------GOLD-------------+\n" + "  a   b   c   d   e   f   g   h\n";
		assertTrue(oughtPitchString.equals(pitchString));
	}

	@Test
	public void testGetAllFiguresOnPitch() {
		List<IFigure> oughtfigures = new ArrayList<>();
		oughtfigures.addAll(goldFigures);
		oughtfigures.addAll(silverFigures);
		List<IFigure> figures = pitch.getAllFiguresOnPitch();

		assertTrue(oughtfigures.equals(figures));
		oughtfigures.remove(0);
		assertFalse(oughtfigures.equals(figures));
	}

}
