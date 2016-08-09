package de.htwg.se.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IPitch pitch;
	private GameStatus status;
	private String statusText = "";

	public Rules(IPitch pitch) {
		this.pitch = pitch;
	}

	public GameStatus getStatus() {
		return status;
	}

	public String getStatusText() {
		return statusText;
	}

	// TODO precondition RULES
	public boolean precondition(Position from, Position to) {

		// from position is empty
		PLAYER_NAME playerName = pitch.getPlayerName(from);
		if (playerName == null) {
			statusText = "No figure on " + Coordinate.convert(from);
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// to position is occupied
		playerName = pitch.getPlayerName(to);
		if (playerName != null) {
			statusText = Coordinate.convert(from) + " is not empty";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// no moves remain
		if (pitch.getRemainingMoves() == 0) {
			statusText = "No remain moves";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// is hold
		if (isHold(from)) {
			statusText = "Figure is hold";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// -PULL

		// -PUSH

		// is to position a possible move
		List<Position> possibleMoves = getPossibleMoves(from);
		if (possibleMoves == null || !possibleMoves.contains(to)) {
			statusText = Coordinate.convert(to) + " is not a permitted position";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		return true;
	}

	private boolean isHold(Position pos) {
		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPositionForPitch(pos);
		canditates = getOccupiedPositions(canditates);

		if (canditates.isEmpty())
			return false;

		List<Position> ownFigures = new ArrayList<>();
		PLAYER_NAME playerName = pitch.getPlayerName(pos);
		ownFigures = getFigursPositionsFromPlayer(playerName, canditates);

		if (!ownFigures.isEmpty())
			return false;

		List<Position> otherFigures = new ArrayList<>();
		otherFigures = getFigursPositionsFromPlayer(PLAYER_NAME.invers(playerName), canditates);

		FIGURE_NAME own = pitch.getFigureName(pos);
		FIGURE_NAME otherStrongestFigure = getStrongestFigure(otherFigures);

		if (own.compareTo(otherStrongestFigure) >= 0)
			return false;
		
		return true;
	}

	// TODO postcondition RULELS
	public boolean postcondition(Position from, Position to) {
		// TODO move steps
		pitch.reduceRemainingMoves(1);

		// TODO is finish rule
		// TODO TRAPP rule

		return true;
	}

	public List<Position> getPossibleMoves(Position pos) {
		if (pitch.getPlayerName(pos) != pitch.getCurrentPlayer())
			return null;

		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPositionForPitch(pos);

		canditates.removeAll(getOccupiedPositions(canditates));

		return canditates;
	}

	private FIGURE_NAME getStrongestFigure(List<Position> surroundPosList) {
		FIGURE_NAME figureName = null;
		for (Position pos : surroundPosList) {
			if (figureName == null)
				figureName = pitch.getFigureName(pos);

			FIGURE_NAME actFigureName = pitch.getFigureName(pos);
			if (actFigureName.compareTo(figureName) > 0)
				figureName = actFigureName;
		}
		return figureName;

	}

	private List<Position> getFigursPositionsFromPlayer(PLAYER_NAME playerName, List<Position> surroundPosList) {
		List<Position> posList = new ArrayList<>();
		for (Position pos : surroundPosList) {
			if (pitch.getPlayerName(pos).equals(playerName))
				posList.add(pos);
		}
		return posList;
	}

	private List<Position> getOccupiedPositions(List<Position> surroundPosList) {
		List<Position> occupied = new ArrayList<>();
		for (Position pos : surroundPosList) {
			if (pitch.getPlayerName(pos) != null)
				occupied.add(pos);
		}
		return occupied;
	}

}
