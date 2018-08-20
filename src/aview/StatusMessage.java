package aview;

import controller.GameStatus;

import java.util.EnumMap;
import java.util.Map;

public class StatusMessage {
    private static final Map<GameStatus, String> text = new EnumMap<>(GameStatus.class);

    private StatusMessage() {

    }

    static {
        text.put(GameStatus.CREATE, "CREATE");
        text.put(GameStatus.EXIT, "EXIT");
        text.put(GameStatus.MOVE_FIGURE, "MOVE_FIGURE");
        text.put(GameStatus.CHANGE_PLAYER, "CHANGE_PLAYER");

        text.put(GameStatus.PRECONDITIONRULES_VIOLATED, "PRECONDITIONRULES_VIOLATED");

        text.put(GameStatus.REDO, "REDO");
        text.put(GameStatus.UNDO, "UNDO");

        text.put(GameStatus.PUSHFIGURE, "PUSHFIGURE");
        text.put(GameStatus.CAPTURED, "CAPTURED");
        text.put(GameStatus.FINISH, "FINISH");

    }

    public static String getText(GameStatus gameStatus) {
        return text.get(gameStatus);
    }
}
