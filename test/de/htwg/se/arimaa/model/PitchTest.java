package de.htwg.se.arimaa.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class PitchTest {
	Pitch pitch;

	@Before
	public void setUp() throws Exception {
		pitch = new Pitch("T1", "T2");
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
		ArrayList<Player> pl = new ArrayList<>();
		pl.add(pitch.getP1());
		pl.add(pitch.getP2());

		sb.append("+-------------------------------+\n");
		for (int y = 0; y < 8; y++) {
			if (y > 0)
				sb.append("+---+---+---+---+---+---+---+---+\n");
			for (int x = 0; x <= 8; x++) {
				if ((x == 2 || x == 5) && (y == 2 || y == 5))
					sb.append("| # ");
				else
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
			sb.append(8 - y);
			sb.append("\n");
		}
		sb.append("+-------------------------------+\n");
		sb.append("  a   b   c   d   e   f   g   h\n");
		System.out.println(sb.toString());
	}
}
