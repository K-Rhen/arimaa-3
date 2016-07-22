package de.htwg.se.arimaa.aview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.arimaa.Arimaa;
import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;
import de.htwg.se.arimaa.util.position.Position;

public class TextUI implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(TextUI.class.getName());

	IArimaaController controller;

	boolean gameRunning = true;

	public TextUI(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nif you lost, type help\n");
		sb.append("\nturn: " + controller.getCurrentPlayer().toString() + "  remaining moves: "
				+ controller.getRemainingMoves() + "\n");
		sb.append(controller.CurrentPitchView());

		sb.append("INFO: " + controller.getStatusText() + "\n");
		sb.append("READY: :-");
		return sb.toString();
	}

	public boolean processInputLine(String inputLine) {
		// TODO next
		// TEST
	

		if (inputLine.matches("exit")) {
			controller.arimaaExit();
			return false;
		} else if (inputLine.matches("help")) {
			LOGGER.entry(helpText());
		} else if (inputLine.matches("done")) {
			controller.changePlayer();
		} else if (inputLine.matches("[a-h][1-8]-[a-h][1-8]#[a-h][1-8]-[a-h][1-8]")) {
			// TODO pull

		} else if (inputLine.matches("[a-h][1-8]-[a-h][1-8]")) {
				moveFigureByString(inputLine);
		}

		// Print pitch
		controller.CurrentPitchView();
		return gameRunning;
	}

	private String helpText() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nUse:\n");
		sb.append("  a2-a3        ->move figure       [fromPosition-toPostion]\n");
		sb.append("  a2-b2#a4-a3  ->push/pull figures [GOLDmoveFigure#SILVERmoveFigure]\n");
		sb.append("  done         ->change player\n");
		sb.append("  exit         ->exit the programm\n");
		return sb.toString();
	}

	public boolean moveFigureByString(String inputLine) {
		char[] parts = inputLine.toCharArray();
		Position from = new Position(readPosX(parts[0]), readPosY(parts[1]));
		Position to = new Position(readPosX(parts[3]), readPosY(parts[4]));

		PLAYER_NAME currentPlayer = controller.getCurrentPlayer();
		return controller.moveFigure(currentPlayer, from, to);
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
			throw new IllegalArgumentException(c + " wrong coordinate");
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
			throw new IllegalArgumentException(c + " wrong coordinate");
		}
	}


	@Override
	public void update(Event e) {
		// Show TUI
		LOGGER.entry(toString());

		// TODO refactor: gs in toString ?
		GameStatus gs = controller.getGameStatus();
		if (gs.equals(GameStatus.WIN_GOLD)) {
			LOGGER.entry("GOLD wins");

		} else if (gs.equals(GameStatus.WIN_SILVER)) {
			LOGGER.entry("SILVER wins");

		} else if (gs.equals(GameStatus.EXIT)) {
			LOGGER.entry("GOODBY");
			gameRunning = false;
		}
	}

}
