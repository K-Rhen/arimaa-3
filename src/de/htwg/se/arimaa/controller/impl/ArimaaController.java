package de.htwg.se.arimaa.controller.impl;

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
	private int remainingMoves = 4;
	private int lastPlayer = 1;
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
		//TODO is finish rule
		reduceMove(player);

		// TODO TRAPP rule
		
		gameStatus = GameStatus.MOVEFIGURE;
		notifyObservers();

		return able;
	}

	private boolean moveFigur(int player, Position from, Position to) {
return false;

	}


}
