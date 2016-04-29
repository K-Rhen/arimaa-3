package de.htwg.se.arimaa.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		Player p1 = pitch.getP1();
		Player p2 = pitch.getP2();

		sb.append("+-------------------------------+\n");
		for (int y = 0; y < 8; y++) {
			if (y > 0)
				sb.append("+---+---+---+---+---+---+---+---+\n");
			for (int x = 0; x <= 8; x++) {
				if ((x == 2 || x == 5) &&( y == 2 || y == 5))
					sb.append("| # ");
				else
					sb.append("|   ");
			}
			sb.append(8 - y);
			sb.append("\n");
		}
		sb.append("+-------------------------------+\n");
		sb.append("  a   b   c   d   e   f   g   h\n");
		System.out.println(sb.toString());
	}
}
