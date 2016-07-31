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
		String oughtPitchString = "+-------------SILVER------------+\n"
				+ "| R | R | R | D | D | R | R | R | 8\n"
				+ "+---+---+---+---+---+---+---+---+\n"
+ "| R | H | C | L | E | C | H | R | 7\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "|   |   | # |   |   | # |   |   | 6\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "|   |   |   |   |   |   |   |   | 5\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "|   |   |   |   |   |   |   |   | 4\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "|   |   | # |   |   | # |   |   | 3\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "| r | h | c | l | e | c | h | r | 2\n"
+ "+---+---+---+---+---+---+---+---+\n"
+ "| r | r | r | d | d | r | r | r | 1\n"
+ "+--------------GOLD-------------+\n"
+ "  a   b   c   d   e   f   g   h\n";
		
		assertTrue(oughtPitchString.equals(pitchString));
	}

}
