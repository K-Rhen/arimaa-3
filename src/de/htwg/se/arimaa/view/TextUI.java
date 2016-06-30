package de.htwg.se.arimaa.view;

import java.util.Scanner;

import de.htwg.arimaa.controller.impl.ArimaaController;

import java.lang.System;

public class TextUI {

	Scanner EINGABE = new Scanner(System.in);
	ArimaaController controller = new ArimaaController();

	private String player1;
	private String player2;



	public void fastStart() { // loeschen sobald tui laeuft
		player1 = "Spieler1";
		player2 = "Spieler2";
	}

	public void run() {
		boolean firstPlayer = true; // steuert welcher Spieler an der Reihe ist


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
						controller.pullFigureEnemy(firstPlayer, ziehen); //markiert die Figur die gezogen wird
						System.out.println("Von wo nach wo möchtest du deine Figur ziehen?");
						ziehen = EINGABE.nextLine(); 
						controller.pullFigureOwn(firstPlayer, ziehen); //setzt die eigene figur an neue position und zieht gegnerische mit
						
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
