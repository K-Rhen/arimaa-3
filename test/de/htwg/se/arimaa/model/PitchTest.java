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

	@Before
	public void setUp() throws Exception {
		pitch = new Pitch();
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
	public void testGetPlayerName() {
		assertEquals(PLAYER_NAME.GOLD, pitch.getPlayerName(new Position(0, 6)));
		assertEquals(PLAYER_NAME.SILVER, pitch.getPlayerName(new Position(0, 1)));
		assertEquals(null, pitch.getPlayerName(new Position(3, 3)));
	}

	@Test
	public void testReduceRemainingMoves() {
		assertEquals(4, pitch.getRemainingMoves());
		assertTrue(pitch.reduceRemainingMoves());
		assertEquals(3, pitch.getRemainingMoves());

		assertTrue(pitch.reduceRemainingMoves());
		assertTrue(pitch.reduceRemainingMoves());
		assertTrue(pitch.reduceRemainingMoves());
		assertEquals(0, pitch.getRemainingMoves());

		assertFalse(pitch.reduceRemainingMoves());
		assertEquals(0, pitch.getRemainingMoves());
	}

	@Test
	public void testSetCurrentPlayer() {
		assertEquals(PLAYER_NAME.GOLD, pitch.getCurrentPlayerName());
		pitch.setCurrentPlayer(PLAYER_NAME.SILVER);
		assertEquals(PLAYER_NAME.SILVER, pitch.getCurrentPlayerName());
	}

	@Test
	public void testSetRemainingMoves() {
		assertEquals(4, pitch.getRemainingMoves());
		pitch.setRemainingMoves(-5);
		assertEquals(4, pitch.getRemainingMoves());
		pitch.setRemainingMoves(5);
		assertEquals(4, pitch.getRemainingMoves());

		pitch.setRemainingMoves(2);
		assertEquals(2, pitch.getRemainingMoves());
	}

	@Test
	public void testGetFigureName() {
		assertEquals(null, pitch.getFigureName(new Position(0, 2)));
		assertEquals(FIGURE_NAME.R, pitch.getFigureName(new Position(0, 0)));
		assertEquals(FIGURE_NAME.R, pitch.getFigureName(new Position(0, 7)));
	}

	@Test
	public void testChangePlayer() {
		assertFalse(pitch.isChangePlayerEable());
		assertEquals(PLAYER_NAME.GOLD, pitch.getCurrentPlayerName());
		pitch.changePlayer();
		assertEquals(PLAYER_NAME.GOLD, pitch.getCurrentPlayerName());

		pitch.moveFigure(new Position(0, 6), new Position(0, 5));
		assertTrue(pitch.isChangePlayerEable());
		pitch.changePlayer();
		assertEquals(PLAYER_NAME.SILVER, pitch.getCurrentPlayerName());
		assertFalse(pitch.isChangePlayerEable());

		pitch.moveFigure(new Position(0, 1), new Position(0, 2));
		assertTrue(pitch.isChangePlayerEable());
		pitch.changePlayer();
		assertFalse(pitch.isChangePlayerEable());
	}

	@Test
	public void testDisableFigure() {
		assertFalse(pitch.disableFigure(new Position(0, 2)));
		assertTrue(pitch.disableFigure(new Position(0, 0)));
		assertEquals(null, pitch.getFigureName(new Position(0, 0)));
		assertEquals(null, pitch.getPlayerName(new Position(0, 0)));
	}

	@Test
	public void testNoRabbits() {
		assertFalse(pitch.noRabbits(PLAYER_NAME.GOLD));

		// Disable all Gold Rabbits
		pitch.disableFigure(new Position(0, 6));
		pitch.disableFigure(new Position(0, 7));
		pitch.disableFigure(new Position(1, 7));
		pitch.disableFigure(new Position(2, 7));
		pitch.disableFigure(new Position(5, 7));
		pitch.disableFigure(new Position(6, 7));
		pitch.disableFigure(new Position(7, 7));
		pitch.disableFigure(new Position(7, 6));

		assertTrue(pitch.noRabbits(PLAYER_NAME.GOLD));
	}

	@Test
	public void testToString() {
		String isString = pitch.toString();
		String oughtPitchString = "+-------------SILVER------------+\n" + "| r | r | r | d | d | r | r | r | 8\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| r | h | c | e | m | c | h | r | 7\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 6\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 5\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   |   |   |   |   |   |   | 4\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "|   |   | # |   |   | # |   |   | 3\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| R | H | C | M | E | C | H | R | 2\n"
				+ "+---+---+---+---+---+---+---+---+\n" + "| R | R | R | D | D | R | R | R | 1\n"
				+ "+--------------GOLD-------------+\n" + "  a   b   c   d   e   f   g   h\n";

		assertTrue(oughtPitchString.equals(isString));
	}

}
