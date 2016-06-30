package de.htwg.se.arimaa.arimaa;

import java.util.Scanner;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.controller.IArimaaControllerFactory;
import de.htwg.se.arimaa.view.TextUI;
import de.htwg.se.arimaa.view.gui.ArimaaFrame;

public class Arimaa {

	private static Scanner scanner;
	protected IArimaaController controller;

	private TextUI tui;
	private ArimaaFrame gui;

	private Arimaa() {

		controller = IArimaaControllerFactory.getInstance(); // TODO inject

		tui = new TextUI();
		gui = new ArimaaFrame(controller);
	}

	public TextUI getTui() {
		return tui;
	}

	public ArimaaFrame getGui() {
		return gui;
	}

	public static void main(final String[] args) {
		Arimaa game = new Arimaa();

		game.tui.showPitch();
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
			System.out.println("Bitte um Eingabe: "); //TODO SCHÖNER SCHREIBEN
			continu = game.tui.processInputLine(scanner.next());
		}



	}
	

}
