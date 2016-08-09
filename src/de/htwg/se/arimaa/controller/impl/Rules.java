package de.htwg.se.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IArimaaController controller;
	private GameStatus status;
	private String statusText = "";

	public Rules(IArimaaController controller) {
		this.controller = controller;
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
		PLAYER_NAME playerName = controller.getPlayerName(from);
		if (playerName == null) {
			statusText = "No figure on " + Coordinate.convert(from);
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// to position is occupied
		playerName = controller.getPlayerName(to);
		if (playerName != null) {
			statusText = Coordinate.convert(from) + " is not empty";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// no moves remain
		if (controller.getRemainingMoves() == 0) {
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
		if (isPulled(from, to)) {
			statusText = "Figure is pulled";
			status = GameStatus.MOVEFIGURE;
			return true;
		}

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

	private boolean isPulled(Position from, Position to) {
		PLAYER_NAME actPlayerName = controller.getPlayerName(from);
		// actual move figure is from other player
		if (actPlayerName == null || actPlayerName.equals(controller.getCurrentPlayerName()))
			return false;

		// last move figure stronger than pulled figure
		FIGURE_NAME lastFigureName = controller.getLastMoveFigureName();
		FIGURE_NAME actFigureName = controller.getFigureName(from);
		if (lastFigureName == null || lastFigureName.compareTo(actFigureName) <= 0)
			return false;

		// last from position must be act to position
		Position lastFromPosition = controller.getLastMoveFromPosition();
		if (lastFromPosition == null || !lastFromPosition.equals(to))
			return false;

		return true;
	}

	private boolean isHold(Position pos) {
		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPositionForPitch(pos);
		canditates = getOccupiedPositions(canditates);

		if (canditates.isEmpty())
			return false;

		List<Position> ownFigures = new ArrayList<>();
		PLAYER_NAME playerName = controller.getPlayerName(pos);
		ownFigures = getFigursPositionsFromPlayer(playerName, canditates);

		if (!ownFigures.isEmpty())
			return false;

		List<Position> otherFigures = new ArrayList<>();
		otherFigures = getFigursPositionsFromPlayer(PLAYER_NAME.invers(playerName), canditates);

		FIGURE_NAME own = controller.getFigureName(pos);
		FIGURE_NAME otherStrongestFigure = getStrongestFigure(otherFigures);

		if (own.compareTo(otherStrongestFigure) >= 0)
			return false;

		return true;
	}

	// TODO postcondition RULELS
	public boolean postcondition(Position from, Position to) {

		// TODO TRAPP rule

		// TODO is finish rule
		return true;
	}

	public List<Position> getPossibleMoves(Position pos) {
		if (controller.getPlayerName(pos) != controller.getCurrentPlayerName())
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
				figureName = controller.getFigureName(pos);

			FIGURE_NAME actFigureName = controller.getFigureName(pos);
			if (actFigureName.compareTo(figureName) > 0)
				figureName = actFigureName;
		}
		return figureName;

	}

	private List<Position> getFigursPositionsFromPlayer(PLAYER_NAME playerName, List<Position> surroundPosList) {
		List<Position> posList = new ArrayList<>();
		for (Position pos : surroundPosList) {
			if (controller.getPlayerName(pos).equals(playerName))
				posList.add(pos);
		}
		return posList;
	}

	private List<Position> getOccupiedPositions(List<Position> surroundPosList) {
		List<Position> occupied = new ArrayList<>();
		for (Position pos : surroundPosList) {
			if (controller.getPlayerName(pos) != null)
				occupied.add(pos);
		}
		return occupied;
	}

}
