package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPitchFactory;

public class PitchFactory implements IPitchFactory{
	

	public static Pitch getInstance(String player1Name, String player2Name) {
		return new Pitch(player1Name, player2Name);
	}
}
