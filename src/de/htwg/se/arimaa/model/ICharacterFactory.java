package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.ICHARAKTER_NAME;

import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.util.character.Position;

public interface ICharacterFactory {

	static Character getInstance(Position positon, ICHARAKTER_NAME name){
		return new Character(positon, name);
	}
}
