package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public class Player implements IPlayer {
	private List<IFigure> figures;
	private PLAYER_NAME playerName;

	public Player(PLAYER_NAME playerName, List<IFigure> figures) {
		this.playerName = playerName;
		this.figures = figures;
	}

	@Override
	public PLAYER_NAME getPlayerName() {
		return playerName;
	}

	@Override
	public FIGURE_NAME getFigure(Position pos) {
		for (IFigure figure : figures) {
			Position actFigurePos = figure.getPosition();
			if (actFigurePos.equals(pos) && !figure.isDisbale())
				return figure.getName();
		}
		return null;
	}

	@Override
	public void moveFigure(Position start, Position end) {
		if (getFigure(start) == null)
			throw new IllegalArgumentException("No figure on " + start.toString());

		for (IFigure figure : figures) {
			if (figure.getPosition().equals(start)) {
				figure.setPosition(end);
				break;
			}
		}
	}

	@Override
	public List<IFigure> getFigures() {
		List<IFigure> tmpFigures = figures;

		for (int i = tmpFigures.size() - 1; i >= 0; i--) {
			if (tmpFigures.get(i).isDisbale())
				tmpFigures.remove(i);
		}

		return tmpFigures;
	}

	@Override
	public boolean disableFigure(Position pos) {
		if (getFigure(pos) == null)
			return false;
		for (int i = 0; i < figures.size(); i++) {
			IFigure figur = figures.get(i);
			if (figur.getPosition().equals(pos))
				figur.setDisable(true);
		}
		return true;
	}

}
