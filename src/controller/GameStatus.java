package controller;

public enum  GameStatus {
    CREATE,
    EXIT,
    MOVE_FIGURE,
    CHANGE_PLAYER,
    PRECONDITION_RULES_VIOLATED,
    REDO,
    UNDO,
    PUSH_FIGURE,
    CAPTURED,
    FINISH,
    CIRCULAR_MOVE
}
