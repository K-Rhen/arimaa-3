package de.htwg.se.arimaa.controller;

public enum  GameStatus {
    CREATE,
    EXIT,
    MOVEFIGURE,
    CHANGEPLAYER, 
    PRECONDITIONRULES_VIOLATED, 
    REDO, 
    UNDO, 
    PUSHFIGURE, 
    CAPTURED, 
    FINISH, 
    CIRCULARMOVE; 
}
