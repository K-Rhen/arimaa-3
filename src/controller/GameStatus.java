package controller;

public enum  GameStatus {
    CREATE,
    EXIT,
    MOVE_FIGURE,
    CHANGEPLAYER, 
    PRECONDITIONRULES_VIOLATED, 
    REDO,
    UNDO,
    PUSHFIGURE,
    CAPTURED,
    FINISH,
    CIRCULARMOVE
}
