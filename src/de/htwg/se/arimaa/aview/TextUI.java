package de.htwg.se.arimaa.aview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.arimaa.Arimaa;
import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
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
		sb.append("\nturn: GOLD  remaining moves: " + controller.getRemainingMoves() + "\n");
		sb.append(controller.CurrentPitchView());

		sb.append("News: Your turn was illegal\n");
		sb.append("READY: :-");
		return sb.toString();
	}

	public boolean processInputLine(String line) {
		if (line.matches("exit")) {
			controller.arimaaExit();
			return false;
		} else if (line.matches("help")) {
			LOGGER.entry("TODO");
		} else if (line.matches("done")) {
			controller.changePlayer();
		} else if (line.matches("[a-h][1-8]-[a-h][1-8]#[a-h][1-8]-[a-h][1-8]")) {
			// TODO pull
		
		} else if (line.matches("[a-h][1-8]-[a-h][1-8]")) {
			// TODO normal move
			moveFigureByString(controller.getCurrentPlayer(), line);

		}

		controller.CurrentPitchView();
		LOGGER.entry(
				"Spieler" + controller.getCurrentPlayer() + " hat noch " + controller.getRemainingMoves() + " Zuege.");
		return gameRunning;
	}

	@Override
	public void update(Event e) {
		// Show TUI
		LOGGER.entry(toString());

		//TODO refactor: gs in toString ? 
		GameStatus gs = controller.getGameStatus();
		if (gs.equals(GameStatus.WinPLAYER1)) {
			LOGGER.entry("Player 1 gewonnen");

		} else if (gs.equals(GameStatus.WinPLAYER2)) {
			LOGGER.entry("Player 2 gewonnen");

		} else if (gs.equals(GameStatus.EXIT)) {
			LOGGER.entry("Vielen Dank.");
			gameRunning = false;
		}
	}

	
	//TODO refactor
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

	//TODO refactor
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

	//TODO refactor
	public boolean moveFigureByString(int player, String eingabe) {
		if (eingabe.length() != 5) {
			throw new IllegalArgumentException("Die Eingabe muss dem Format \"c6-d6\" entsprechen.");
		}

		char[] parts = eingabe.toCharArray();
		Position from = new Position(readPosX(parts[0]), readPosY(parts[1]));
		Position to = new Position(readPosX(parts[3]), readPosY(parts[4]));

		return controller.moveFigure(player, from, to);
	}
	
}
