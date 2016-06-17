package de.htwg.se.arimaa.model.impl;

import java.util.ArrayList;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.util.character.Position;

public class Pitch implements IPitch {

	private List<Player> pl = new ArrayList<>();
	
	private static final int PITCHSIZE = 8;

	public Pitch(String player1Name, String player2Name,
			List<ICharacter> figures1, List<ICharacter> figures2) {
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

			sb.append(getPitchRow(y));

			sb.append("| ");
			sb.append(PITCHSIZE - y);
			sb.append("\n");
		}
		sb.append("+-------------------------------+\n");
		sb.append("  a   b   c   d   e   f   g   h\n");
		return sb.toString();
	}

	private String getPitchRow(int y) {
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < PITCHSIZE; x++) {
			if ((x == 2 || x == 5) && (y == 2 || y == 5))
				sb.append("| # ");
			else {
				sb.append("| ");

				Position pos = new Position(x, y);
				sb.append(getCellFigure(pos));
			}
		}
		return sb.toString();
	}

	private String getCellFigure(Position pos) {
		StringBuilder sb = new StringBuilder();

		boolean occupied = false;
		for (Player p : pl) {
			CHARAKTER_NAME c = p.getFigur(pos);
			if (c != null) {
				sb.append(c);
				occupied = true;
				break;
			}
		}

		if (occupied)
			sb.append(" ");
		else
			sb.append("  ");

		return sb.toString();
	}

	public boolean pitchEquals(IPitch pitch) {
		if (pitch.toString().equals(this.toString()))
			return true;
		return false;
	}



	

}
