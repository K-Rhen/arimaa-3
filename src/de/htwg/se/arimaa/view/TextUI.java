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

		// ---------------------------Begrüßung--------------------------------

		System.out.println("Hallo. Willkommen bei Arimaa.");
		System.out.println("Spieler Eins bitte geb deinen Namen ein.\n");
		player1 = EINGABE.nextLine();
		System.out.println("\nHallo " + player1 + ".\n");
		System.out.println("Spieler Zwei bitte geb deinen Namen ein.\n");
		player2 = EINGABE.nextLine();
		System.out.println("\nHallo " + player2 + ".\n");

		// ---------------------------Spieler 1 setzt Figuren------------------

		System.out.println(player1
				+ " möchtest du deine Figuren individuell setzten?");
		System.out.println("Tippe \"j\" für individuelles setzen der Figuren\n"
				+ "oder \"n\" für eine Standartbesetzung.");

		String eingabe = EINGABE.nextLine();
		boolean falscheEingabe = false;

		while (!falscheEingabe) { // falsche Eingabe abfangen
			if (eingabe.equals("j") || eingabe.equals("n")) {
				falscheEingabe = true;

			} else {
				System.out
						.println("Falsche Eingabe. Bitte wähle zwischen \"j\" und \"n\"");
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
				+ " möchtest du deine Figuren individuell setzten?");
		System.out.println("Tippe \"j\" für individuelles setzen der Figuren\n"
				+ "oder \"n\" für eine Standartbesetzung.");
		eingabe = EINGABE.nextLine();
		falscheEingabe = false;

		while (!falscheEingabe) { // falsche Eingabe abfangen
			if (eingabe.equals("j") || eingabe.equals("n")) {
				falscheEingabe = true;

			} else {
				System.out
						.println("Falsche Eingabe. Bitte wähle zwischen \"j\" und \"n\"");
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

	public void fastStart() { // loeschen sobald tui laeuft
		player1 = "Spieler1";
		player2 = "Spieler2";
		controller.initdefaultPitch(1);
		controller.initdefaultPitch(2);
	}

	public void run() {
		boolean firstPlayer = true; // steuert welcher Spieler an der Reihe ist
		controller.initializePitch(player1, player2);

		while (true) {
			for (int i = 0; i < 4; ++i) { //jeder Spieler hat maximal 4 Zuege pro Runde

				controller.ShowPitch();  //spielfeld ausgeben
				if (firstPlayer) { //einleitender satz in abhängigkeit wer dran ist
					System.out.printf(player1
							+ " du bist an der Reihe und hast noch " + (4 - i)
							+ " Zuege. Mache einen Zug z.B. mit \"a7-a6\"\n");
				} else {
					System.out
							.println(player2
									+ " du bist an der Reihe und hast noch " + (4 - i)
							+ " Zuege. Mache einen Zug z.B. mit \"b2-b3\"\n");
				}
				System.out.println("Schreib \"help\" fuer Hilfe\n");

				String eingabe = EINGABE.nextLine();

				if (eingabe.equals("exit")) { //bei help als befehl mit aufnehmen
					System.out.println("Auf Wiedersehen.");
					return;
				}
				if (eingabe.equals("help")) {
					System.out.println("TODO");
				}
				if(eingabe.equals("fertig")){  //bei help als befehl mit aufnehmen
					i = 4; // beendet die runde für den jeweiligen spieler
				
				}
				if(eingabe.equals("ziehen")){ //bei help als befehl mit aufnehmen
					System.out.println("Gib an, wo die gegnerische Figur steht, welche du ziehen willst.z.b. mit \"c3\"");
					
					try{
						String ziehen = EINGABE.nextLine();
						controller.pullFigureEnemy(firstPlayer, ziehen);
						System.out.println("Von wo nach wo möchtest du deine Figur ziehen?");
						ziehen = EINGABE.nextLine();
						controller.pullFigureOwn(firstPlayer, ziehen);
						
					} catch(IllegalArgumentException e){
						System.out.println("Falsche Eingabe: " + e.getMessage());
					}
				}else {
					try {
						if (firstPlayer) //liest und verabeitet die Eingabe in abhängigkeit wer dran ist
							controller.checkEingabe(1, eingabe, false); //false, da keine figur gezogen wird
						else
							controller.checkEingabe(2, eingabe, false);//false, da keine figur gezogen wird
					} catch (IllegalArgumentException e) {
						--i;
						System.out
								.println("Falsche Eingabe: " + e.getMessage());
					}
				}
			}

			firstPlayer = !firstPlayer; //schaltet von spieler 1 auf 2 um bzw umgekehrt
		}
	}
}
