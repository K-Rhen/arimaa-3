package de.htwg.se.arimaa.arimaa;

import java.util.ArrayList;
import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;
import de.htwg.se.arimaa.view.TextUI;

public class Arimaa {

	private static Scanner scanner;
	private static TextUI tui;
	private static ArimaaController controller;

	public static void main(final String[] args) {

		// TODO implements
		ArrayList<Character> figures1 = new ArrayList<>();
		figures1.add(new Character(new Position(0, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(0, 1), CHARAKTER_NAME.E));
		
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(7, 0), CHARAKTER_NAME.R));
		figures2.add(new Character(new Position(7, 1), CHARAKTER_NAME.E));
		
		Pitch p = new Pitch("Player1", "Player2",figures1,figures2);
		System.out.println(p.toString());

	}

}
