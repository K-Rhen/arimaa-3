package de.htwg.se.arimaa.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

public class ArimaaController extends Observable implements IArimaaController {
	private static final Logger LOGGER = LogManager.getLogger(ArimaaController.class.getName());

	private IPitch pitch;
	private Rules rules;
	private int remainingMoves = 4;
	private PLAYER_NAME currentPlayer = PLAYER_NAME.GOLD;
	private GameStatus gameStatus;

	@Inject
	public ArimaaController() {
		pitch = new Pitch();
		rules = new Rules(this);
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
		currentPlayer = getNextPlayer();

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
	public PLAYER_NAME getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public PLAYER_NAME getNextPlayer() {
		if (currentPlayer.equals(PLAYER_NAME.GOLD))
			return PLAYER_NAME.SILVER;
		else
			return PLAYER_NAME.GOLD;
	}

	@Override
	public String CurrentPitchView() {
		return pitch.toString();
	}

	private boolean reduceMove() {
		if (remainingMoves == 0)
			return false;

		remainingMoves--;
		gameStatus = GameStatus.MOVECHANGE;
		notifyObservers();
		return true;
	}

	@Override
	public boolean moveFigure(PLAYER_NAME player, Position from, Position to) {
		// TODO refactor
		boolean able = moveFigur(player, from, to);

		// after calls
		// TODO is finish rule
		reduceMove();
		// TODO TRAPP rule

		gameStatus = GameStatus.MOVEFIGURE;
		notifyObservers();
		return able;
	}

	private boolean moveFigur(PLAYER_NAME player, Position from, Position to) {
		if (player.equals(PLAYER_NAME.GOLD))
			return pitch.getGoldPlayer().moveFigure(from, to);
		else
			return pitch.getSilverPlayer().moveFigure(from, to);
	}

}
