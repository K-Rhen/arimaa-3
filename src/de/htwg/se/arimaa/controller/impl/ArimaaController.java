package de.htwg.se.arimaa.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.arimaa.aview.gui.PitchPanel;
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
	public IPlayer getPlayer1() {
		return pitch.getPlayer1();
	}

	@Override
	public IPlayer getPlayer2() {
		return pitch.getPlayer2();
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
		if (player != lastPlayer)
			return false;

		if (remainingMoves == 0) {
			return false;
		}

		remainingMoves--;
		gameStatus = GameStatus.MOVECHANGE;
		notifyObservers();
		return true;
	}

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

	private boolean finishPlayer(FIGURE_NAME figureName, int y) {
		for (IFigure figure : pitch.getPlayer1().getFigures()) {
			if (figure.getName() == figureName && figure.getPosition().getY() == y)
				return true;
		}
		return false;

	}

	private int readPosX(char c) {

		switch (c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			throw new IllegalArgumentException(
					c + " ist keine korrekte x-Koordinate. Sie muss zwischen a und h liegen");
		}
	}

	private int readPosY(char c) {

		switch (c) {
		case '1':
			return 7;
		case '2':
			return 6;
		case '3':
			return 5;
		case '4':
			return 4;
		case '5':
			return 3;
		case '6':
			return 2;
		case '7':
			return 1;
		case '8':
			return 0;
		default:
			throw new IllegalArgumentException(
					c + " ist keine korrekte y-Koordinate. Sie muss zwischen 1 und 8 liegen");
		}
	}

	@Override
	public boolean moveFigureByString(int player, String eingabe) {
		if (eingabe.length() != 5) {
			throw new IllegalArgumentException("Die Eingabe muss dem Format \"c6-d6\" entsprechen.");
		}

		char[] parts = eingabe.toCharArray();
		Position from = new Position(readPosX(parts[0]), readPosY(parts[1]));
		Position to = new Position(readPosX(parts[3]), readPosY(parts[4]));

		return moveFigureByPosition(player, from, to);
	}

	@Override
	public boolean moveFigureByPosition(int player, Position from, Position to) {
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

	// TODO refactor
	@Override
	public boolean pushFigurs(int firstPlayer, int secondPlayer, String line) {

		String[] parts = line.split("#");
		String part1 = parts[0];
		String part2 = parts[1];

		// TODO test
		boolean firstmove = moveFigureByString(firstPlayer, part1);
		if (!firstmove)
			return false;
		boolean secondmove = moveFigureByString(secondPlayer, part2);

		return secondmove;
	}

	// TODO refactor
	private Position toPull;

	public void pullFigureEnemy(boolean firstPlayer, String pull) {
		if (pull.length() != 2) {
			throw new IllegalArgumentException("Falschen Format. Die Eingabe muss z.b. \"d4\" sein");
		}
		char[] pullPosition = pull.toCharArray();
		toPull = new Position(readPosX(pullPosition[0]), readPosY(pullPosition[1]));
		if (firstPlayer) {
			if (isFigurOwn(pitch.getPlayer1().getFigures(), toPull))
				throw new IllegalArgumentException("Es muss eine gegnerische Figur sein.");
		} else {
			if (isFigurOwn(pitch.getPlayer2().getFigures(), toPull))
				throw new IllegalArgumentException("Es muss eine gegnerische Figur sein.");
		}

	}

	// TODO remove
	private boolean isFigurOwn(List<IFigure> figures, Position from) {

		for (IFigure usedchar : figures) {
			if (usedchar.getPosition().equals(from))
				return true;
		}
		return false;
	}

	@Override
	public void create() {
		gameStatus = GameStatus.CREATE;
		notifyObservers();
	}

}
