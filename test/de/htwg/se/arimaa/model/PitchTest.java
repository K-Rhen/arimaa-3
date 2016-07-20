package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.model.impl.Pitch;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPitch;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.character.Position;

public class PitchTest {
	ArrayList<IPlayer> pl = new ArrayList<>();
	IPitch pitch;
	
	

	@Before
	public void setUp() throws Exception {
		ArrayList<ICharacter> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(0, 1), CHARAKTER_NAME.E));
		
		ArrayList<ICharacter> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(7, 0), CHARAKTER_NAME.R));
		figures2.add(new Character(new Position(7, 1), CHARAKTER_NAME.E));
		

		pitch = new Pitch("T1", "T2");
		pitch.setP1Figures(figures1);
		pitch.setP2Figures(figures2);
		
		pl.add(pitch.getP1());
		pl.add(pitch.getP2());
	}

	@Test
	public void getP1test() {
		assertEquals("T1", pitch.getP1().getPlayerName());
	}

	@Test
	public void getP2test() {
		assertEquals("T2", pitch.getP2().getPlayerName());
	}

	@Test
	public void testToString() {
		StringBuilder sb = new StringBuilder();
		
		int PITCHSIZE = 8;
		sb.append("+-------------------------------+\n");
		for (int y = 0; y < PITCHSIZE; y++) {
			if (y > 0)
				sb.append("+---+---+---+---+---+---+---+---+\n");
			for (int x = 0; x < PITCHSIZE; x++) {
				if ((x == 2 || x == 5) && (y == 2 || y == 5))
					sb.append("| # ");
				else {
					sb.append("| ");

					boolean occupied = false;
					Position pos = new Position(x, y);
					for (IPlayer p : pl) {
						ICHARAKTER_NAME c = p.getFigur(pos);
						if (c != null) {
							sb.append(c);
							occupied = true;
						}
					}
					if (occupied == true)
						sb.append(" ");
					else
						sb.append("  ");
				}
			}
			sb.append("| ");
			sb.append(PITCHSIZE - y);
			sb.append("\n");
		}
		sb.append("+-------------------------------+\n");
		sb.append("  a   b   c   d   e   f   g   h\n");

		String testpitch = sb.toString();
		assertEquals(testpitch, pitch.toString());
	}
	
	@Test
	public void equalsCheck(){
		//TODO refactor
//		ArrayList<ICharacter> figures1 = new ArrayList<>();
//		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
//		ArrayList<ICharacter> figures2 = new ArrayList<>();
//		figures2.add(new Character(new Position(0, 1), CHARAKTER_NAME.r));
//		IPitch a = pf.getInstance("p1","p2",figures1,figures2);
//		IPitch b = pf.getInstance("p1","p2",figures1,figures2);
//		assertTrue(a.pitchEquals(b));
//		ArrayList<ICharacter> figures3 = new ArrayList<>();
//		figures3.add(new Character(new Position(0, 2), CHARAKTER_NAME.H));
//		b = pf.getInstance("p1","p2",figures1,figures3);
//		assertTrue(a.pitchEquals(b));
//		
		
	}
	
	
		
}
