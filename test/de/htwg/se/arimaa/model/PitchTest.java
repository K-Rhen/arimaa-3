package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.position.Position;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;

import de.htwg.se.arimaa.model.IPlayer;

public class PitchTest {
	ArrayList<IPlayer> pl = new ArrayList<>();
	IPitch pitch;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<IFigure> figures1 = new ArrayList<>();
		figures1.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(0, 1), FIGURE_NAME.E));
		
		ArrayList<IFigure> figures2 = new ArrayList<>();
		figures2.add(new Figure(new Position(0, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(0, 6), FIGURE_NAME.e));
		

		pitch = new Pitch("T1", "T2");
		pitch.setPlayer1Figures(figures1);
		pitch.setPlayer2Figures(figures2);
		
		pl.add(pitch.getPlayer1());
		pl.add(pitch.getPlayer2());
	}

	@Test
	public void getP1test() {
		assertEquals("T1", pitch.getPlayer1().getPlayerName());
	}

	@Test
	public void getP2test() {
		assertEquals("T2", pitch.getPlayer2().getPlayerName());
	}

	@Test
	public void testToString() {
		String pitchString = pitch.toString();
		String oughtPitchString = "+-------------------------------+\n"
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
				+ "+-------------------------------+\n"
				+ "  a   b   c   d   e   f   g   h\n";

		assertTrue(oughtPitchString.equals(pitchString));
	}
		
}
