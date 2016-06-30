package de.htwg.se.arimaa.view;

import java.awt.Event;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.IObserver;


public class TextUI implements IObserver {

	IArimaaController controller;
	
	public TextUI(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	private String player1 = "Spieler1";
	private String player2 = "Spieler2";
	boolean gameRunning = true;

	public void run() {
//		boolean firstPlayer = true; // steuert welcher Spieler an der Reihe ist
//
//		while (true) {
//			for (int i = 0; i < 4; ++i) { // jeder Spieler hat maximal 4 Zuege
//											// pro Runde
//
//				controller.ShowPitch(); // spielfeld ausgeben
//				if (firstPlayer) { // einleitender satz in abhängigkeit wer dran
//									// ist 
//					System.out.printf(player1
//							+ " du bist an der Reihe und hast noch " + (4 - i)
//							+ " Zuege. Mache einen Zug z.B. mit \"a7-a6\"\n");
//				} else {
//					System.out.println(player2
//							+ " du bist an der Reihe und hast noch " + (4 - i)
//							+ " Zuege. Mache einen Zug z.B. mit \"b2-b3\"\n");
//				}
//				System.out.println("Schreib \"help\" fuer Hilfe\n");
//
//				String eingabe = EINGABE.nextLine();
//
//				if (eingabe.equals("exit")) { // bei help als befehl mit
//												// aufnehmen
//					System.out.println("Auf Wiedersehen.");
//					return;
//				}
//				if (eingabe.equals("help")) {
//					System.out.println("TODO");
//				}
//				if (eingabe.equals("fertig")) { // bei help als befehl mit
//												// aufnehmen
//					i = 4; // beendet die runde für den jeweiligen spieler
//
//				}
//				if (eingabe.equals("ziehen")) { // bei help als befehl mit
//												// aufnehmen
//					System.out
//							.println("Gib an, wo die gegnerische Figur steht, welche du ziehen willst.z.b. mit \"c3\"");
//
//					try {
//						String ziehen = EINGABE.nextLine();
//						controller.pullFigureEnemy(firstPlayer, ziehen); // markiert
//																			// die
//																			// Figur
//																			// die
//																			// gezogen
//																			// wird
//						System.out
//								.println("Von wo nach wo möchtest du deine Figur ziehen?");
//						ziehen = EINGABE.nextLine();
//						controller.pullFigureOwn(firstPlayer, ziehen); // setzt
//																		// die
//																		// eigene
//																		// figur
//																		// an
//																		// neue
//																		// position
//																		// und
//																		// zieht
//																		// gegnerische
//																		// mit
//
//					} catch (IllegalArgumentException e) {
//						System.out
//								.println("Falsche Eingabe: " + e.getMessage());
//					}
//				} else {
//					try {
//						if (firstPlayer) // liest und verabeitet die Eingabe in
//											// abhängigkeit wer dran ist
//							controller.moveFigureByString(1, eingabe); // false,
//																		// da
//																		// keine
//																		// figur
//																		// gezogen
//																		// wird
//						else
//							controller.moveFigureByString(2, eingabe);// false,
//																		// da
//																		// keine
//																		// figur
//																		// gezogen
//																		// wird
//					} catch (IllegalArgumentException e) {
//						--i;
//						System.out
//								.println("Falsche Eingabe: " + e.getMessage());
//					}
//				}
//			}
//
//			firstPlayer = !firstPlayer; // schaltet von spieler 1 auf 2 um bzw
//										// umgekehrt
//		}
	}

	int i =1;
	public boolean processInputLine(String line) {
		System.out.println("BLABLA");
		
		if (line.matches("exit")) {
			controller.arimaaExit();
			return false;
		} else if (line.matches("help")) {
			System.out.println("TODO");
		} else if (line.matches("done")) { // bei help als befehl mit
		controller.changePlayer();
		}else if (line.matches("[a-h][1-8]-[a-h][1-8]#[a-h][1-8]-[a-h][1-8]")) { // ziehen
																			// schieben
			controller.pushFigurs(controller.getActualPlayer(),controller.getNextPlayer(), line);  //TODO PLAYER
			
		}else if (line.matches("[a-h][1-8]-[a-h][1-8]")) { // normaler Zug
			controller.moveFigureByString(controller.getActualPlayer(), line); // TODO Player
		
		}
		
		controller.ShowPitch();  // spielfeld ausgeben
		System.out.println("Spieler"+controller.getActualPlayer() +" hat noch " + controller.getMoveCounter() + " Zuege.");
		return gameRunning;

	}

	public void showPitch() {
		controller.ShowPitch();
		
	}

	@Override
	public void update(Event e) {
		GameStatus gs = controller.getGameStatus();
		if(gs.equals(GameStatus.WinPLAYER1)){
			System.out.println("Player 1 gewonnen");
			controller.arimaaExit();
		}else if(gs.equals(GameStatus.WinPLAYER2)){
			System.out.println("Player 2 gewonnen");
			controller.arimaaExit();
	}else if(gs.equals(GameStatus.EXIT)){
		System.out.println("Vielen Dank.");
		gameRunning = false;
	}
	}

}
