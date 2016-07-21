package de.htwg.se.arimaa.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

public class ArimaaController extends Observable implements IArimaaController {
	private static final Logger LOGGER = LogManager.getLogger(ArimaaController.class.getName());

	private IPitch pitch;
	private Rules rules;
	private String player1Name = "Player1";
	private String player2Name = "Player2";
	private int remainingMoves = 4;
	private int lastPlayer = 1;
	private GameStatus gameStatus;

	@Inject
	public ArimaaController() {
		pitch = new Pitch(player1Name, player2Name);
		rules = new Rules(pitch);
	}

	@Override
	public int getRemainingMoves() {
		return remainingMoves;
	}

	@Override
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	@Override
	public void arimaaExit() {
		gameStatus = GameStatus.EXIT;
		notifyObservers();
	}

	@Override
	public void changePlayer() {
		lastPlayer = getNextPlayer();

		remainingMoves = 4;

		gameStatus = GameStatus.CHANGEPLAYER;
		notifyObservers();
	}

	@Override
	public void create() {
		gameStatus = GameStatus.CREATE;
		notifyObservers();
	}

	@Override
	public int getCurrentPlayer() {
		return lastPlayer;
	}

	@Override
	public int getNextPlayer() {
		if (lastPlayer == 1)
			return 2;
		else
			return 1;
	}

	@Override
	public String CurrentPitchView() {
		return pitch.toString();
	}

	private boolean reduceMove(int player) {
		if (remainingMoves == 0)
			return false;

		remainingMoves--;
		gameStatus = GameStatus.MOVECHANGE;
		notifyObservers();
		return true;
	}

	// TODO refactor evt rules ?
	private boolean isfinish() {
		// Player1
		if (finishPlayer(FIGURE_NAME.R, 7)) {
			gameStatus = GameStatus.WinPLAYER1;
			notifyObservers();
			return true;
		}

		// Player2
		if (finishPlayer(FIGURE_NAME.r, 0)) {
			gameStatus = GameStatus.WinPLAYER2;
			notifyObservers();
			return true;
		}

		return false;

	}

	// TODO refactor evt rules ?
	private boolean finishPlayer(FIGURE_NAME figureName, int y) {
		for (IFigure figure : pitch.getPlayer1().getFigures()) {
			if (figure.getName() == figureName && figure.getPosition().getY() == y)
				return true;
		}
		return false;

	}

	@Override
	public boolean moveFigure(int player, Position from, Position to) {
		if (remainingMoves == 0) {
			gameStatus = GameStatus.MOVESDONE;
			notifyObservers();
			return false;
		}

		boolean able = moveFigur(player, from, to);

		if (!able) {
			gameStatus = GameStatus.WRONGTURN;
			notifyObservers();
			return false;
		}

		// after calls
		isfinish();
		reduceMove(player);

		// TODO TRAPP
		// trapp
		if ((to.getX() == 2 || to.getX() == 5) && (to.getY() == 2 || to.getY() == 5))
			pitch.getPlayer1().deleteFigure(to);

		gameStatus = GameStatus.MOVEFIGURE;
		notifyObservers();

		return able;
	}

	private boolean moveFigur(int player, Position from, Position to) {
		if (!rules.posDistance(from, to))
			throw new IllegalArgumentException("Du kannst hoechstens 1 Feld weiter ziehen.");
		if (rules.occupiedCell(to))
			throw new IllegalArgumentException("Die Position auf welche du ziehen willst ist bereits belegt");

		if (player == 1)
			return moveFigure(pitch.getPlayer1(), from, to);
		if (player == 2)
			return moveFigure(pitch.getPlayer2(), from, to);
		return false;

	}

	private boolean moveFigure(IPlayer p, Position from, Position to) {
		return p.moveFigure(from, to);
	}

}
