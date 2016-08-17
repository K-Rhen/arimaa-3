package de.htwg.se.arimaa.aview;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.arimaa.controller.GameStatus;

public class StatusMessage {
	public static final Map<GameStatus, String> text = new HashMap<>();

	private StatusMessage() {

	}

	static {
		text.put(GameStatus.CREATE, "CREATE");
		text.put(GameStatus.EXIT, "EXIT");
		text.put(GameStatus.MOVEFIGURE, "MOVEFIGURE");
		text.put(GameStatus.CHANGEPLAYER, "CHANGEPLAYER");

		text.put(GameStatus.PRECONDITIONRULES_VIOLATED, "PRECONDITIONRULES_VIOLATED");
		text.put(GameStatus.POSTCONDITIONRULES_VIOLATED, "POSTCONDITIONRULES_VIOLATED");

		text.put(GameStatus.REDO, "REDO");
		text.put(GameStatus.UNDO, "UNDO");

		text.put(GameStatus.PUSHFIGURE, "PUSHFIGURE");
		text.put(GameStatus.CAPTURED, "CAPTURED");
		text.put(GameStatus.FINISH, "FINISH");

	}
}
