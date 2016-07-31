package de.htwg.se.arimaa.aview.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.aview.StatusMessage;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

public class TextUI implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(TextUI.class.getName());

	IArimaaController controller;

	public TextUI(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nif you lost, type h\n");
		sb.append("\nturn: " + controller.getCurrentPlayerName().toString() + "  remaining moves: "
				+ controller.getRemainingMoves() + "\n");
		sb.append(controller.currentPitchView());
		sb.append("INFO: " + StatusMessage.text.get(controller.getGameStatus()) + "\n");
		sb.append("STATUSTEXT: " + controller.getStatusText() + "\n");
		sb.append("READY: :-");
		return sb.toString();
	}

	public void processInputLine(String inputLine) {
		if (inputLine.matches("q")) {
			controller.quitGame();
		} else if (inputLine.matches("h")) {
			LOGGER.entry(helpText());
		} else if (inputLine.matches("c")) {
			controller.changePlayer();
		} else if (inputLine.matches("u")) {
			controller.undo();
		} else if (inputLine.matches("r")) {
			controller.redo();
		} else if (inputLine.matches("[a-h][1-8]-[a-h][1-8]")) {
			moveFigureByString(inputLine);
		} else {
			LOGGER.entry("\'" + inputLine + "\' is a wrong input, type h for help");
		}

		// Print pitch
		controller.currentPitchView();
	}

	private String helpText() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nUse:\n");
		sb.append("  a2-a3     -> move figure       [fromPosition-toPostion]\n");
		sb.append("  c         -> change player\n");
		sb.append("  u         -> undo last move\n");
		sb.append("  r         -> redo last move\n");
		sb.append("  q         -> exit the programm\n");
		return sb.toString();
	}

	public boolean moveFigureByString(String inputLine) {
		String[] parts = inputLine.split("-");
		Position from = Coordinate.convert(parts[0]);
		Position to = Coordinate.convert(parts[1]);

		return controller.moveFigure(from, to);
	}

	@Override
	public void update(Event e) {
		// Show TUI
		LOGGER.entry(toString());
	}

}
