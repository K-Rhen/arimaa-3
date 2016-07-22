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
	private GameStatus status;
	private String statusText = "";

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
		return status;
	}

	@Override
	public String getStatusText() {
		return statusText;
	}

	@Override
	public void arimaaExit() {
		status = GameStatus.EXIT;
		notifyObservers();
	}

	@Override
	public void changePlayer() {
		currentPlayer = getNextPlayer();

		remainingMoves = 4;

		status = GameStatus.CHANGEPLAYER;
		notifyObservers();
	}

	@Override
	public void create() {
		status = GameStatus.CREATE;
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
		status = GameStatus.MOVECHANGE;
		notifyObservers();
		return true;
	}

	// TODO refactor
	@Override
	public boolean moveFigure(PLAYER_NAME player, Position from, Position to) {
		String preStatusText = rules.precondition(player, from, to);
		if (preStatusText != null) {
			statusText = preStatusText;
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			notifyObservers();
			return false;
		}
		
		
		boolean able = moveFigur(player, from, to);
		// move not able
		if (able == false){
			LOGGER.error("No Figure on "+ from.toString());
			return false;
		}
			

		reduceMove();

		
		String postStatusText = rules.postcondition(player, from, to);
		if (postStatusText != null) {
			statusText = postStatusText;
			status = GameStatus.POSTCONDITIONRULES_VIOLATED;
			notifyObservers();
			return false;
		}

		statusText = "";
		status = GameStatus.MOVEFIGURE;
		notifyObservers();
		return true;
	}

	private boolean moveFigur(PLAYER_NAME player, Position from, Position to) {
		if (player.equals(PLAYER_NAME.GOLD))
			return pitch.getGoldPlayer().moveFigure(from, to);
		else
			return pitch.getSilverPlayer().moveFigure(from, to);
	}

}
