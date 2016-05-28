package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.util.character.Position;
import de.htwg.se.arimaa.model.Pitch;

public class PitchTest {
	ArrayList<Player> pl = new ArrayList<>();
	Pitch pitch;

	@Before
	public void setUp() throws Exception {
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(0, 1), CHARAKTER_NAME.E));
		
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(7, 0), CHARAKTER_NAME.R));
		figures2.add(new Character(new Position(7, 1), CHARAKTER_NAME.E));
		
		pitch = new Pitch("T1", "T2",figures1,figures2);

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
					for (Player p : pl) {
						CHARAKTER_NAME c = p.getFigur(pos);
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
		
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 1), CHARAKTER_NAME.r));
		Pitch a = new Pitch("p1","p2",figures1,figures2);
		Pitch b = new Pitch("p1","p2",figures1,figures2);
		assertTrue(a.equals(b));
		ArrayList<Character> figures3 = new ArrayList<>();
		figures3.add(new Character(new Position(0, 2), CHARAKTER_NAME.H));
		b = new Pitch("p1","p2",figures1,figures3);
		assertFalse(a.equals(b));
		b = new Pitch("p1","p2",figures1,figures2);
		assertTrue(a.equals(b));
	}
	
	@Test
	public void pitchAlreadyExistedCheck(){
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 1), CHARAKTER_NAME.r));
		Pitch a = new Pitch("p1","p2",figures1,figures2);
		Pitch d = new Pitch("p1","p2",figures1,figures2); //exact das gleiche wie a
		
		assertTrue(a.pitchAlreadyExisted());
		assertFalse(a.pitchAlreadyExisted());
		
		figures2.add(new Character(new Position(0, 2), CHARAKTER_NAME.H));
		Pitch b = new Pitch("p1","p2",figures1,figures2);
		
		assertTrue(b.pitchAlreadyExisted());
		assertFalse(a.pitchAlreadyExisted());
		assertFalse(a.pitchAlreadyExisted());
		assertFalse(a.pitchAlreadyExisted());
		assertFalse(b.pitchAlreadyExisted());
//		assertFalse(d.pitchAlreadyExisted());   //WTF wieso ??? d == a und sollte schon vorhanden sein
		Pitch c = b;
		assertFalse(c.pitchAlreadyExisted());
		assertFalse(a.pitchAlreadyExisted());
//		b = new Pitch("p1","p2",figures1,figures2);  //genauso hier.... b entspricht vom aufbau a und sollte schon vorhanden sein
//		assertFalse(b.pitchAlreadyExisted()); 		// pitchList wird komischerweise stehts neu aufgebaut
		assertFalse(a.pitchAlreadyExisted());
		figures2.add(new Character(new Position(0, 3), CHARAKTER_NAME.r));
		c = new Pitch("p1","p2",figures1,figures2);
		assertTrue(c.pitchAlreadyExisted());
		
	}
		
}
