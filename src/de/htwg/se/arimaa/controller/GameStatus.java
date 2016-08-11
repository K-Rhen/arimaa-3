package de.htwg.se.arimaa.controller;

public enum  GameStatus {
    CREATE,
    EXIT,
    MOVEFIGURE,
    CHANGEPLAYER, 
    WIN_GOLD,
    WIN_SILVER,
    PRECONDITIONRULES_VIOLATED, 
    POSTCONDITIONRULES_VIOLATED,  
    REDO, 
    UNDO, PUSHFIGURE, ALLOWEDMOVE,  
}
