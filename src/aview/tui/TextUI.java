package aview.tui;

import aview.StatusMessage;
import controller.IArimaaController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.observer.Event;
import util.observer.IObserver;
import util.position.Coordinate;
import util.position.Position;

public class TextUI implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(TextUI.class.getName());

	private final IArimaaController controller;

	public TextUI(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nif you lost, type h\n");
		sb.append("\nplayer: " + controller.getCurrentPlayerName().toString() + "  moves: "
				+ controller.getRemainingMoves() + "\n");
		sb.append(controller.getPitchView());
		sb.append("INFO: " + StatusMessage.getText(controller.getGameStatus()) + "\n");
        sb.append("STATUS TEXT: " + controller.getStatusText() + "\n");
		sb.append("READY: :-");
		return sb.toString();
	}

	public void processInputLine(String inputLine) {
		if (inputLine.matches("q")) {
			controller.quitGame();
		} else if (inputLine.matches("h")) {
            LOGGER.info(helpText());
		} else if (inputLine.matches("c")) {
			controller.changePlayer();
		} else if (inputLine.matches("u")) {
			controller.undo();
		} else if (inputLine.matches("r")) {
			controller.redo();
		} else if (inputLine.matches("[a-h][1-8]-[a-h][1-8]")) {
			moveFigureByString(inputLine);
		} else {
            LOGGER.info("\'" + inputLine + "\' is a wrong input, type h for help");
		}
	}

	private String helpText() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nUse:\n");
		sb.append("  a2-a3     -> move figure  [from position - to position]\n");
		sb.append("  c         -> change player\n");
		sb.append("  u         -> undo last move\n");
		sb.append("  r         -> redo last move\n");
		sb.append("  q         -> exit the game\n");
		return sb.toString();
	}

	private boolean moveFigureByString(String inputLine) {
		String[] parts = inputLine.split("-");
		Position from = Coordinate.convert(parts[0]);
		Position to = Coordinate.convert(parts[1]);

		return controller.moveFigure(from, to);
	}

	@Override
	public void update(Event e) {
		// Show TUI
        LOGGER.info(toString());
	}

}
