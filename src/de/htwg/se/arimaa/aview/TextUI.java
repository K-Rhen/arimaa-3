package de.htwg.se.arimaa.aview;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class TextUI implements IObserver {
	IArimaaController controller;

	boolean gameRunning = true;

	public TextUI(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	public boolean processInputLine(String line) {
		if (line.matches("exit")) {
			controller.arimaaExit();
			return false;
		} else if (line.matches("help")) {
			System.out.println("TODO");
		} else if (line.matches("done")) {
			controller.changePlayer();
		} else if (line.matches("[a-h][1-8]-[a-h][1-8]#[a-h][1-8]-[a-h][1-8]")) {
			// TODO pull
			controller.pushFigurs(controller.getCurrentPlayer(), controller.getNextPlayer(), line);

		} else if (line.matches("[a-h][1-8]-[a-h][1-8]")) {
			// TODO normal move
			controller.moveFigureByString(controller.getCurrentPlayer(), line);

		}

		controller.ShowPitch();
		System.out.println(
				"Spieler" + controller.getCurrentPlayer() + " hat noch " + controller.getMoveCounter() + " Zuege.");
		return gameRunning;
	}

	@Override
	public void update(Event e) {
		GameStatus gs = controller.getGameStatus();
		if (gs.equals(GameStatus.WinPLAYER1)) {
			System.out.println("Player 1 gewonnen");

		} else if (gs.equals(GameStatus.WinPLAYER2)) {
			System.out.println("Player 2 gewonnen");

		} else if (gs.equals(GameStatus.EXIT)) {
			System.out.println("Vielen Dank.");
			gameRunning = false;
		}
	}

}
