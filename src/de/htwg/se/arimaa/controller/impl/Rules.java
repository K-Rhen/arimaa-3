package de.htwg.se.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IArimaaController controller;
	private GameStatus status;
	private String statusText;

	public Rules(IArimaaController controller) {
		this.controller = controller;
		status = GameStatus.CREATE;

		statusText = "";
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

		// is figure a rabbit move backwards
		if (isRabbitMoveBackward(from, to)) {
			statusText = "Rabbits can't go backwards";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// -PUSH
		// pushed must be finished
		if (controller.getGameStatus().equals(GameStatus.PUSHFIGURE)) {
			Position lastFromPosition = controller.getLastMoveFromPosition();
			if (!lastFromPosition.equals(to)) {
				statusText = "First finish your push to " + Coordinate.convert(lastFromPosition);
				status = GameStatus.PUSHFIGURE;
				return false;
			}
		}

		// is Pushed Start
		if (isPushedStart(from, to)) {
			statusText = "Figure is pushed";
			status = GameStatus.PUSHFIGURE;
			return true;
		}

		// is hold
		if (isHold(from)) {
			statusText = "Figure is hold";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// is pulled
		if (isPulled(from, to)) {
			statusText = "Figure is pulled";
			status = GameStatus.MOVEFIGURE;
			return true;
		}

		// is to position a possible move
		List<Position> possibleMoves = getFreeOwnSurroundPositions(from);
		if (possibleMoves.isEmpty() || !possibleMoves.contains(to)) {
			statusText = Coordinate.convert(to) + " is not a permitted position";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		status = GameStatus.MOVEFIGURE;
		statusText = "from " + Coordinate.convert(from) + "  to " + Coordinate.convert(to);
		return true;
	}

	private boolean isRabbitMoveBackward(Position from, Position to) {
		// is Figure a Rabbit
		FIGURE_NAME figureName = controller.getFigureName(from);
		if (!figureName.equals(FIGURE_NAME.R))
			return false;

		PLAYER_NAME playerName = controller.getPlayerName(from);
		String direction = Position.getDirection(from, to);
		if (direction == null)
			return false;

		if (playerName.equals(PLAYER_NAME.GOLD) && !direction.equals("s"))
			return false;

		if (playerName.equals(PLAYER_NAME.SILVER) && !direction.equals("n"))
			return false;

		return true;
	}

	private boolean isHold(Position pos) {
		List<Position> ownFigures = new ArrayList<>();
		PLAYER_NAME ownPlayerName = controller.getPlayerName(pos);
		ownFigures = getSurroundFigures(ownPlayerName, pos);

		// is surround by a own figure
		if (!ownFigures.isEmpty())
			return false;

		List<Position> otherFigures = new ArrayList<>();
		PLAYER_NAME otherPlayerName = PLAYER_NAME.invers(ownPlayerName);
		otherFigures = getSurroundFigures(otherPlayerName, pos);

		// own figure is not surrounded by a enemy
		if (otherFigures.isEmpty())
			return false;

		FIGURE_NAME ownFigureName = controller.getFigureName(pos);
		FIGURE_NAME otherStrongestFigureName = getStrongestFigure(otherFigures);

		// other surround figure is stronger than own figure
		if (ownFigureName.compareTo(otherStrongestFigureName) >= 0)
			return false;

		return true;
	}

	private List<Position> getSurroundFigures(PLAYER_NAME playerName, Position pos) {
		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPositionForPitch(pos);
		canditates = getOccupiedPositions(canditates);

		return getFigursPositionsFromPlayer(playerName, canditates);
	}

	private boolean isPushedStart(Position from, Position to) {
		PLAYER_NAME actPlayerName = controller.getPlayerName(from);
		// actual move figure is from other player
		if (actPlayerName == null || actPlayerName.equals(controller.getCurrentPlayerName()))
			return false;

		// actual move figure to position equals surround position
		List<Position> actSurroundPositions = new ArrayList<>();
		actSurroundPositions = Position.getSurroundPositionForPitch(from);
		if (!actSurroundPositions.contains(to))
			return false;

		// actual move figure surround by a other player figure
		List<Position> otherPlayerPositons = new ArrayList<>();
		otherPlayerPositons = getFigursPositionsFromPlayer(controller.getCurrentPlayerName(), actSurroundPositions);

		if (otherPlayerPositons.isEmpty())
			return false;

		// other player figure stronger than actual move figure
		FIGURE_NAME strongestOtherFigure = getStrongestFigure(otherPlayerPositons);
		FIGURE_NAME actFigureName = controller.getFigureName(from);
		if (strongestOtherFigure == null || strongestOtherFigure.compareTo(actFigureName) <= 0)
			return false;

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

	// TODO postcondition RULELS
	public boolean postcondition(Position from, Position to) {
		// trap rule
		if (isCaptured(from, to)) {
			statusText = "Figure captured";
			status = GameStatus.CAPTURED;
		}

		// -GAMEENDE
		PLAYER_NAME winner = getWinner(from, to);
		if (winner != null) {
			statusText = winner.toString() + " won the game";
			 status = GameStatus.FINISH;
		}

		// TODO circular move

		return true;
	}

	
	private PLAYER_NAME getWinner(Position from, Position to) {
		// elimination
		if(controller.noRabbits(PLAYER_NAME.GOLD))
			return PLAYER_NAME.SILVER;
		else if(controller.noRabbits(PLAYER_NAME.SILVER))
			return PLAYER_NAME.GOLD;
		
		
//		// goal
//		FIGURE_NAME figureName = controller.getFigureName(to);
//		if (!figureName.equals(FIGURE_NAME.R))
//			return null;
//
//		PLAYER_NAME playerName = controller.getPlayerName(to);
//		
//		if (to.getY() == 0 && playerName.equals(PLAYER_NAME.GOLD))
//			return PLAYER_NAME.GOLD;
//		else if (to.getY() == 7 && playerName.equals(PLAYER_NAME.SILVER))
//			return PLAYER_NAME.SILVER;


		
		
		// imobilisation

		
		return null;
	}

	private boolean isCaptured(Position from, Position to) {
		List<Position> traps = new ArrayList<>();
		traps.add(new Position(2, 2));
		traps.add(new Position(5, 2));
		traps.add(new Position(2, 5));
		traps.add(new Position(5, 5));

		boolean result = false;

		for (Position actTrapPos : traps) {
			if (controller.getFigureName(actTrapPos) != null) {

				List<Position> ownFigures = new ArrayList<>();
				PLAYER_NAME ownPlayerName = controller.getPlayerName(actTrapPos);
				ownFigures = getSurroundFigures(ownPlayerName, actTrapPos);

				// is surround by a own figure
				if (ownFigures.isEmpty()) {
					controller.disableFigure(actTrapPos);
					result = true;
				}
			}
		}
		return result;
	}

	public List<Position> getFreeOwnSurroundPositions(Position pos) {
		List<Position> canditates = new ArrayList<>();

		if (controller.getPlayerName(pos) != controller.getCurrentPlayerName())
			return canditates;

		canditates = Position.getSurroundPositionForPitch(pos);

		canditates.removeAll(getOccupiedPositions(canditates));

		return canditates;
	}

	public List<Position> getPossibleMoves(Position pos) {
		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPositionForPitch(pos);
		canditates.removeAll(getOccupiedPositions(canditates));

		for (int i = canditates.size() - 1; i >= 0; i--) {
			if (!precondition(pos, canditates.get(i)))
				canditates.remove(i);
		}

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
			PLAYER_NAME actPlayerName = controller.getPlayerName(pos);
			if (actPlayerName != null && actPlayerName.equals(playerName))
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
