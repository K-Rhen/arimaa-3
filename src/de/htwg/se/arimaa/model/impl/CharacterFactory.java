package de.htwg.se.arimaa.model.impl;

import de.htwg.se.arimaa.model.ICharacterFactory;
import de.htwg.se.arimaa.util.character.Position;


public class CharacterFactory implements ICharacterFactory{
	
	public static Character getInstance(Position positon, CHARAKTER_NAME name){
		return new Character(positon, name);
	}

}
