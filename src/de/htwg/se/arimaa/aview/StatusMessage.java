package de.htwg.se.arimaa.aview;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.arimaa.controller.GameStatus;

public class StatusMessage {
    public static final Map<GameStatus,String> text = new HashMap<>();
    private StatusMessage() {

    }

    static {
        text.put(GameStatus.CREATE, "A new Arimaa pitch was created successfully");
        text.put(GameStatus.EXIT, "Have a nice day :)");
        //text.put(GameStatus.ILLEGAL_ARGUMENT, "This is not a valid entry");
        text.put(GameStatus.WIN_GOLD, "Gold you win the game");
        text.put(GameStatus.WIN_SILVER, "Silver you win the game");
        
    }
}
