package de.htwg.se.arimaa.aview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.arimaa.Arimaa;
import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

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
		sb.append("\nturn: Player1  remaining moves: " + controller.getRemainingMoves() + "\n");
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
			controller.pushFigurs(controller.getCurrentPlayer(), controller.getNextPlayer(), line);

		} else if (line.matches("[a-h][1-8]-[a-h][1-8]")) {
			// TODO normal move
			controller.moveFigureByString(controller.getCurrentPlayer(), line);

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

}
