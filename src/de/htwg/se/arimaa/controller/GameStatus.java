package de.htwg.se.arimaa.controller;

public enum  GameStatus {
	IDEL,
    CREATE,
    EXIT,
    MOVEFIGURE,
    REMAINMOVE_CHANGE,
    CHANGEPLAYER, 
    WIN_GOLD,
    WIN_SILVER,
    PRECONDITIONRULES_VIOLATED, 
    POSTCONDITIONRULES_VIOLATED, 
    EMPTYCELL,  
}
