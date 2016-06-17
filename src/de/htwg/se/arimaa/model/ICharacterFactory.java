package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;

import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.util.character.Position;

public interface ICharacterFactory {

	static Character getInstance(Position positon, CHARAKTER_NAME name){
		return new Character(positon, name);
	}
}
