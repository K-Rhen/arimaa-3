package de.htwg.se.arimaa.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.util.character.Position;

public class Pitch implements IPitch {

	private List<Player> pl = new ArrayList<>();
	
	private static final int PITCHSIZE = 8;

	public Pitch(String player1Name, String player2Name) {
		List<IFigure> figures1 = new ArrayList<>(); 
		List<IFigure> figures2 = new ArrayList<>();
		initializeDefaultPitch(figures1, figures2);
		pl.add(new Player(player1Name, figures1));
		pl.add(new Player(player2Name, figures2));
	}
	
	private void initializeDefaultPitch(List<IFigure> figures1, List<IFigure> figures2){
		figures1.add(new Figure(new Position(0, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(1, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(2, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(3, 0), FIGURE_NAME.D));
		figures1.add(new Figure(new Position(4, 0), FIGURE_NAME.D));
		figures1.add(new Figure(new Position(5, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(6, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(7, 0), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(0, 1), FIGURE_NAME.R));
		figures1.add(new Figure(new Position(1, 1), FIGURE_NAME.H));
		figures1.add(new Figure(new Position(2, 1), FIGURE_NAME.C));
		figures1.add(new Figure(new Position(3, 1), FIGURE_NAME.L));
		figures1.add(new Figure(new Position(4, 1), FIGURE_NAME.E));
		figures1.add(new Figure(new Position(5, 1), FIGURE_NAME.C));
		figures1.add(new Figure(new Position(6, 1), FIGURE_NAME.H));
		figures1.add(new Figure(new Position(7, 1), FIGURE_NAME.R));
	
		figures2.add(new Figure(new Position(0, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(1, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(2, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(3, 7), FIGURE_NAME.d));
		figures2.add(new Figure(new Position(4, 7), FIGURE_NAME.d));
		figures2.add(new Figure(new Position(5, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(6, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(7, 7), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(0, 6), FIGURE_NAME.r));
		figures2.add(new Figure(new Position(1, 6), FIGURE_NAME.h));
		figures2.add(new Figure(new Position(2, 6), FIGURE_NAME.c));
		figures2.add(new Figure(new Position(3, 6), FIGURE_NAME.l));
		figures2.add(new Figure(new Position(4, 6), FIGURE_NAME.e));
		figures2.add(new Figure(new Position(5, 6), FIGURE_NAME.c));
		figures2.add(new Figure(new Position(6, 6), FIGURE_NAME.h));
		figures2.add(new Figure(new Position(7, 6), FIGURE_NAME.r));
	}

	public Player getP1() {
		return pl.get(0);
	}

	public Player getP2() {
		return pl.get(1);
	}
	@Override
	public void setP1Figures(ArrayList<IFigure> figures1) {
		//TODO
	}

	@Override
	public void setP2Figures(ArrayList<IFigure> figures2) {
		// TODO Auto-generated method stub
		
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
			IFIGURE_NAME c = p.getFigure(pos);
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
