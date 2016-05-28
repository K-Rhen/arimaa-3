package de.htwg.se.arimaa.view;

import java.util.List;
import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Player;

import java.lang.System;

public class TextUI {

	Scanner EINGABE = new Scanner(System.in);
	ArimaaController controller = new ArimaaController();

	String player1;
	String player2;

	public void start() {

		System.out.println("Hallo. Willkommen bei Arimaa.");
		System.out.println("Spieler Eins bitte geb deinen Namen ein.\n");
		player1 = EINGABE.nextLine();
		System.out.println("\nHallo " + player1 + "\n");
		System.out.println("Spieler Zwei bitte geb deinen Namen ein.\n");
		player2 = EINGABE.nextLine();
		System.out.println("\nHallo " + player2 + "\n");

		System.out.println(player1 + " bitte setze deine Figuren,");
		System.out.println("indem du z.B. \"R a1\" eingibst, oder \"egal\" "
				+ " für die Standartbesetzung.");
		if (EINGABE.nextLine().equals("egal")) {
			controller.initdefaultPitch(1);
			System.out.println("Danke.");
		} else {
			for (int i = 0; i < 16; ++i) {
				System.out.println("Du kannst noch " + (16 - i)
						+ " Figuren setzen");
				controller.setFigure("p1", EINGABE.nextLine());
			}
		}
		
		System.out.println(player2 + " bitte setze deine Figuren,");
		System.out.println("indem du z.B. \"r h1\" eingibst, oder \"egal\" "
				+ " für die Standartbesetzung.");
		if (EINGABE.nextLine().equals("egal")) {
			controller.initdefaultPitch(2);
			System.out.println("Danke.");
		} else {
			for (int i = 0; i < 16; ++i) {
				System.out.println("Du kannst noch " + (16 - i)
						+ " Figuren setzen");
				controller.setFigure("p1", EINGABE.nextLine());
			}
		}

	}

	public void run() {

		while (true) {

			controller.ShowPitch();
			System.out.printf("Mache einen Zug z.B. mit \"a3-a4\"\n");
			System.out.println("help fuer Hilfe\n");

			String eingabe = EINGABE.nextLine();

			if (eingabe.equals("exit")) {
				return;
			}
			if (eingabe.equals("help")) {
				System.out.println("TODO");
			}

		}
	}

}
