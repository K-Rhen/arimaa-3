package de.htwg.se.arimaa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.arimaa.util.character.Position;

public class TestRules {

	Rules rules;
	@Before
	public void setUp() throws Exception {
		rules = new Rules();
		
	}
	
	@Test
	public void testpositionOnPitch() {
		Position end = new Position(0, 0);
		assertTrue(rules.positionOnPitch(end));
		
		end = new Position(9, 9);
		assertFalse(rules.positionOnPitch(end));
		end = new Position(0, 9);
		assertFalse(rules.positionOnPitch(end));
		end = new Position(9, 0);
		assertFalse(rules.positionOnPitch(end));
		
		end = new Position(-1, -1);
		assertFalse(rules.positionOnPitch(end));
		end = new Position(0, -1);
		assertFalse(rules.positionOnPitch(end));
		end = new Position(-1, 0);
		assertFalse(rules.positionOnPitch(end));
		
		
	}
}
