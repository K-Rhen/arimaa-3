package de.htwg.se.arimaa.model;

import java.util.ArrayList;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class Pitch {

	ArrayList<Player> pl = new ArrayList<>();
	private static final int PITCHSIZE = 8;

	public Pitch(String player1Name, String player2Name, ArrayList<Character> figures1, ArrayList<Character> figures2) {
		pl.add(new Player(player1Name, figures1));
		pl.add(new Player(player2Name, figures2));
	}

	public Player getP1() {
		return pl.get(0);
	}

	public Player getP2() {
		return pl.get(1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

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
		return sb.toString();
	}

}
