package de.htwg.se.arimaa.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.model.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.util.character.Position;

public class RulesTest {
	private Pitch pitch;
	private Rules rules;

	@Before
	public void setUp() throws Exception {
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 7), CHARAKTER_NAME.r));

		pitch = new Pitch("Player1", "Player2", figures1, figures2);
		rules = new Rules(pitch);
	}

	@Test
	public void testoccupiedCell() {
		assertTrue(rules.occupiedCell(new Position(0,0)));
		assertFalse(rules.occupiedCell(new Position(1,1)));
		assertTrue(rules.occupiedCell(new Position(0,7)));
	}
	
	@Test
	public void testposDistance(){
		Position p1 = new Position(0, 0);
		Position p2 = new Position(0, 0);
		
		assertTrue(rules.posDistance(p1, p2));
		p2 = new Position(0, 2);
		assertFalse(rules.posDistance(p1, p2));
		p1 = new Position(2, 2);
		assertFalse(rules.posDistance(p1, p2));
		p1 = new Position(0, 0);
		p2 = new Position(2, 0);
		assertFalse(rules.posDistance(p1, p2));
		p1 = new Position(0, 2);
		p2 = new Position(0, 0);
		assertFalse(rules.posDistance(p1, p2));
	}
	
	@Test
	public void pitchAlreadyExistedCheck(){
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 1), CHARAKTER_NAME.r));
		Pitch a = new Pitch("p1","p2",figures1,figures2);
		Pitch d = new Pitch("p1","p2",figures1,figures2); 		
		assertTrue(rules.pitchAlreadyExisted(a));
		assertFalse(rules.pitchAlreadyExisted(a));
		assertFalse(rules.pitchAlreadyExisted(a));
		ArrayList<Character> figures3 = new ArrayList<>();
		figures3.add(new Character(new Position(0, 2), CHARAKTER_NAME.H));
		Pitch b = new Pitch("p1","p2",figures1,figures3);
		
		assertTrue(rules.pitchAlreadyExisted(b));
		assertFalse(rules.pitchAlreadyExisted(b));

		assertFalse(rules.pitchAlreadyExisted(a));
		assertFalse(rules.pitchAlreadyExisted(a));
		assertFalse(rules.pitchAlreadyExisted(b));
		assertFalse(rules.pitchAlreadyExisted(d));   
		Pitch c = b;
		assertFalse(rules.pitchAlreadyExisted(c));
		assertFalse(rules.pitchAlreadyExisted(a));
		b = new Pitch("p1","p2",figures1,figures2);
		assertFalse(rules.pitchAlreadyExisted(b)); 		
		assertFalse(rules.pitchAlreadyExisted(a));
		
		ArrayList<Character> figures4 = new ArrayList<>();
		figures4.add(new Character(new Position(0, 2), CHARAKTER_NAME.H));
		c = new Pitch("p1","p2",figures1,figures4);
		assertFalse(rules.pitchAlreadyExisted(c));
		
	}
}