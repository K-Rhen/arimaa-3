package de.htwg.se.arimaa.view;


import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;


import java.lang.System;

public class TextUI {

	Scanner EINGABE = new Scanner(System.in);
	ArimaaController controller = new ArimaaController();

	private String player1;
	private String player2;

	public void start() {

		// ---------------------------Begr��ung--------------------------------

		System.out.println("Hallo. Willkommen bei Arimaa.");
		System.out.println("Spieler Eins bitte geb deinen Namen ein.\n");
		player1 = EINGABE.nextLine();
		System.out.println("\nHallo " + player1 + ".\n");
		System.out.println("Spieler Zwei bitte geb deinen Namen ein.\n");
		player2 = EINGABE.nextLine();
		System.out.println("\nHallo " + player2 + ".\n");

		// ---------------------------Spieler 1 setzt Figuren------------------

		System.out.println(player1
				+ " m�chtest du deine Figuren individuell setzten?");
		System.out.println("Tippe \"j\" f�r individuelles setzen der Figuren\n"
				+ "oder \"n\" f�r eine Standartbesetzung.");

		String eingabe = EINGABE.nextLine();
		boolean falscheEingabe = false;

		while (!falscheEingabe) { // falsche Eingabe abfangen
			if (eingabe.equals("j") || eingabe.equals("n")) {
				falscheEingabe = true;

			} else {
				System.out
						.println("Falsche Eingabe. Bitte w�hle zwischen \"j\" und \"n\"");
				eingabe = EINGABE.nextLine();
			}
		}

		if (eingabe.equals("n")) {
			controller.initdefaultPitch(1);
			System.out.println("Danke.");
		} else if (eingabe.equals("j")) {
			for (int i = 0; i < 16; ++i) {
				System.out.println("Du kannst noch " + (16 - i)
						+ " Figuren setzen");
				try {
					controller.setFigure("p1", EINGABE.nextLine());
					controller.ShowPitch();
					System.out.println("So sieht das Spielfeld bis jetzt aus");
				} catch (IllegalArgumentException e) {
					System.out.println("Falsche Eingabe: " + e.getMessage());
					System.out.println("Eingabe wird nicht gewertet");
					--i;
				}
			}
		}

		// ---------------------------Spieler 2 setzt Figuren------------------

		System.out.println(player2
				+ " m�chtest du deine Figuren individuell setzten?");
		System.out.println("Tippe \"j\" f�r individuelles setzen der Figuren\n"
				+ "oder \"n\" f�r eine Standartbesetzung.");
		eingabe = EINGABE.nextLine();
		falscheEingabe = false;

		while (!falscheEingabe) { // falsche Eingabe abfangen
			if (eingabe.equals("j") || eingabe.equals("n")) {
				falscheEingabe = true;

			} else {
				System.out
						.println("Falsche Eingabe. Bitte w�hle zwischen \"j\" und \"n\"");
				eingabe = EINGABE.nextLine();
			}
		}
		if (eingabe.equals("n")) {
			controller.initdefaultPitch(2);
			System.out.println("Danke.");
		}
		if (eingabe.equals("j")) {
			for (int i = 0; i < 16; ++i) {
				System.out.println("Du kannst noch " + (16 - i)
						+ " Figuren setzen");
				try {
					controller.setFigure("p2", EINGABE.nextLine());
					controller.ShowPitch();
					System.out.println("So sieht das Spielfeld bis jetzt aus");
				} catch (IllegalArgumentException e) {
					System.out.println("Falsche Eingabe: " + e.getMessage());
					System.out.println("Eingabe wird nicht gewertet");
					--i;
				}
			}
		}

		controller.ShowPitch(); 
		System.out.println("Dies ist euer Spielfeld. Viel Spass.");
	}

	public void fastStart(){  // loeschen sobald tui laeuft
		player1 = "Spieler1";
		player2 = "Spieler2";
		controller.initdefaultPitch(1);
		controller.initdefaultPitch(2);
	}
	
	public void run() {
		boolean firstPlayer = true;
		controller.initializePitch(player1, player2);
		
		while (true) {

			controller.ShowPitch();
			if(firstPlayer){
				System.out.printf(player1 + " du bist an der Reihe. Mache einen Zug z.B. mit \"a7-a6\"\n");
			}else{
				System.out.println(player2 + " du bist an der Reihe. Mache einen Zug z.B. mit \"b2-b3\"\n");
			}
			System.out.println("Schreib \"help\" fuer Hilfe\n");

			String eingabe = EINGABE.nextLine();

			if (eingabe.equals("exit")) {
				System.out.println("Auf Wiedersehen.");
				return;
			}
			if (eingabe.equals("help")) {
				System.out.println("TODO");
			}else{
				try{
					if(firstPlayer)
						controller.checkEingabe(1 ,eingabe);
					else
						controller.checkEingabe(2 ,eingabe);
				} catch(IllegalArgumentException e){
					System.out.println("Falsche Eingabe: " +e.getMessage());
				}
							
			}
			
			firstPlayer = !firstPlayer;
		}
	}
}
