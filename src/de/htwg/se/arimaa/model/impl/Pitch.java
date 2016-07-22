package de.htwg.se.arimaa.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.impl.PLAYER_NAME;
import de.htwg.se.arimaa.model.FIGURE_NAME;

import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.util.position.Position;

public class Pitch implements IPitch {

	private Player goldPlayer;
	private Player silverPlayer;

	private static final int PITCHSIZE = 8;

	public Pitch() {
		List<IFigure> goldFigures = new ArrayList<>();
		List<IFigure> silverFigures = new ArrayList<>();
		initializeDefaultPitch(silverFigures,goldFigures);
		goldPlayer = new Player(PLAYER_NAME.GOLD, goldFigures);
		silverPlayer = new Player(PLAYER_NAME.SILVER, silverFigures);
	}

	private void initializeDefaultPitch(List<IFigure> silverFigures,List<IFigure> goldFigures) {

		silverFigures.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(1, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(2, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(3, 0), FIGURE_NAME.D));
		silverFigures.add(new Figure(new Position(4, 0), FIGURE_NAME.D));
		silverFigures.add(new Figure(new Position(5, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(6, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(7, 0), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(0, 1), FIGURE_NAME.R));
		silverFigures.add(new Figure(new Position(1, 1), FIGURE_NAME.H));
		silverFigures.add(new Figure(new Position(2, 1), FIGURE_NAME.C));
		silverFigures.add(new Figure(new Position(3, 1), FIGURE_NAME.L));
		silverFigures.add(new Figure(new Position(4, 1), FIGURE_NAME.E));
		silverFigures.add(new Figure(new Position(5, 1), FIGURE_NAME.C));
		silverFigures.add(new Figure(new Position(6, 1), FIGURE_NAME.H));
		silverFigures.add(new Figure(new Position(7, 1), FIGURE_NAME.R));

		goldFigures.add(new Figure(new Position(0, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(1, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(2, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(3, 7), FIGURE_NAME.d));
		goldFigures.add(new Figure(new Position(4, 7), FIGURE_NAME.d));
		goldFigures.add(new Figure(new Position(5, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(6, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(7, 7), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(0, 6), FIGURE_NAME.r));
		goldFigures.add(new Figure(new Position(1, 6), FIGURE_NAME.h));
		goldFigures.add(new Figure(new Position(2, 6), FIGURE_NAME.c));
		goldFigures.add(new Figure(new Position(3, 6), FIGURE_NAME.l));
		goldFigures.add(new Figure(new Position(4, 6), FIGURE_NAME.e));
		goldFigures.add(new Figure(new Position(5, 6), FIGURE_NAME.c));
		goldFigures.add(new Figure(new Position(6, 6), FIGURE_NAME.h));
		goldFigures.add(new Figure(new Position(7, 6), FIGURE_NAME.r));
	}

	@Override
	public Player getGoldPlayer() {
		return goldPlayer;
	}

	@Override
	public Player getSilverPlayer() {
		return silverPlayer;
	}

	@Override
	public void setGoldPlayerFigures(List<IFigure> figures) {
		goldPlayer.setFigures(figures);
	}

	@Override
	public void setSilverPlayerFigures(List<IFigure> figures) {
		silverPlayer.setFigures(figures);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("+-------------SILVER------------+\n");
		for (int y = 0; y < PITCHSIZE; y++) {
			if (y > 0)
				sb.append("+---+---+---+---+---+---+---+---+\n");

			sb.append(getPitchRow(y));

			sb.append("| ");
			sb.append(PITCHSIZE - y);
			sb.append("\n");
		}
		sb.append("+--------------GOLD-------------+\n");
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

		FIGURE_NAME figureName = null;
		figureName = goldPlayer.getFigure(pos);
		if (figureName == null)
			figureName = silverPlayer.getFigure(pos);

		if (figureName != null) {
			sb.append(figureName);
			sb.append(" ");
		} else
			sb.append("  ");

		return sb.toString();
	}

}
