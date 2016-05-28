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

	public ArimaaController() {
		initPitchPlayer();
		rules = new Rules(pitch);
	}

	private void initPitchPlayer() {
		List<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		

		List<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 7), CHARAKTER_NAME.r));
		
		pitch = new Pitch("Player1", "Player2", figures1, figures2);
	}

	public void ShowPitch() {
		System.out.println(pitch.toString());
	}
	
	public boolean setFigure(int player, Character figure, Position set){
		return false;
		
	}

	public boolean moveFigur(int player, Position from, Position to){
		
		return false;
	}
	
}
