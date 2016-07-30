package de.htwg.se.arimaa.aview;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.arimaa.controller.GameStatus;

public class StatusMessage {
    public static final Map<GameStatus,String> text = new HashMap<>();
    private StatusMessage() {

    }

    static {
             
    	text.put(GameStatus.IDEL, "IDEL");
        text.put(GameStatus.CREATE, "A new Arimaa pitch was created successfully");
        text.put(GameStatus.EXIT, "Have a nice day :)");
        text.put(GameStatus.MOVEFIGURE, "MOVEFIGURE");
        text.put(GameStatus.REMAINMOVE_CHANGE, "REMAINMOVE_CHANGE");
        text.put(GameStatus.CHANGEPLAYER, "CHANGEPLAYER");
        text.put(GameStatus.WIN_GOLD, "Gold you win the game");
        text.put(GameStatus.WIN_SILVER, "Silver you win the game");
        text.put(GameStatus.PRECONDITIONRULES_VIOLATED, "PRECONDITIONRULES_VIOLATED");
        text.put(GameStatus.POSTCONDITIONRULES_VIOLATED, "POSTCONDITIONRULES_VIOLATED");
        text.put(GameStatus.EMPTYCELL, "EMPTYCELL");
        text.put(GameStatus.REDO, "REDO");
        text.put(GameStatus.UNDO, "UNDO");
        
    }
}
