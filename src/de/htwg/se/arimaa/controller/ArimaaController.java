package de.htwg.se.arimaa.controller;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.model.Player;
import de.htwg.se.arimaa.util.character.Position;

public class ArimaaController {
	private Pitch pitch;
	private Rules rules;
	
	private List<Character> figures1 = new ArrayList<>();
	private List<Character> figures2 = new ArrayList<>();
	public ArimaaController() {
		initPitchPlayer();
		rules = new Rules(pitch);
	}

	private void initPitchPlayer() {
		pitch = new Pitch("Player1", "Player2", figures1, figures2);
	}

	public void ShowPitch() {
		System.out.println(pitch.toString());
	}
	
	public void setFigure(String player, String string){
		String figurename = null;
		String position = null;
		String[] parts = string.split(" ");
		parts[0] = figurename; 
		parts[1] = position;
		Position pos = new Position(readposx(position), readposy(position));
		CHARAKTER_NAME figur = readname(figurename);
		List<Character> list = new ArrayList<Character>();
		Character character = new Character(pos,figur);
		figures1.add(character);
		
	}

	public boolean moveFigur(int player, Position from, Position to){
		
		return false;
	}
	
	private int readposx(String posx){
		return 0;
	}
	private int readposy(String posy){
		return 0;
	}
	private CHARAKTER_NAME readname(String name){
		return null;
		
	}
	
}
