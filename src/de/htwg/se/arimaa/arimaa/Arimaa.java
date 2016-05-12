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
		figures1.add(new Character(new Position(1, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(2, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(3, 0), CHARAKTER_NAME.D));
		figures1.add(new Character(new Position(4, 0), CHARAKTER_NAME.D));
		figures1.add(new Character(new Position(5, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(6, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(7, 0), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(0, 1), CHARAKTER_NAME.R));
		figures1.add(new Character(new Position(1, 1), CHARAKTER_NAME.H));
		figures1.add(new Character(new Position(2, 1), CHARAKTER_NAME.C));
		figures1.add(new Character(new Position(3, 1), CHARAKTER_NAME.L));
		figures1.add(new Character(new Position(4, 1), CHARAKTER_NAME.E));
		figures1.add(new Character(new Position(5, 1), CHARAKTER_NAME.C));
		figures1.add(new Character(new Position(6, 1), CHARAKTER_NAME.H));
		figures1.add(new Character(new Position(7, 1), CHARAKTER_NAME.R));

		
		ArrayList<Character> figures2 = new ArrayList<>();
		figures2.add(new Character(new Position(0, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(1, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(2, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(3, 7), CHARAKTER_NAME.d));
		figures2.add(new Character(new Position(4, 7), CHARAKTER_NAME.d));
		figures2.add(new Character(new Position(5, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(6, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(7, 7), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(0, 6), CHARAKTER_NAME.r));
		figures2.add(new Character(new Position(1, 6), CHARAKTER_NAME.h));
		figures2.add(new Character(new Position(2, 6), CHARAKTER_NAME.c));
		figures2.add(new Character(new Position(3, 6), CHARAKTER_NAME.l));
		figures2.add(new Character(new Position(4, 6), CHARAKTER_NAME.e));
		figures2.add(new Character(new Position(5, 6), CHARAKTER_NAME.c));
		figures2.add(new Character(new Position(6, 6), CHARAKTER_NAME.h));
		figures2.add(new Character(new Position(7, 6), CHARAKTER_NAME.r));
		
		Pitch p = new Pitch("Player1", "Player2",figures1,figures2);
		System.out.println(p.toString());

	}

}
