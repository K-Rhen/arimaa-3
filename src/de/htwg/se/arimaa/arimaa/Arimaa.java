package de.htwg.se.arimaa.arimaa;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.arimaa.aview.gui.ArimaaFrame;
import de.htwg.se.arimaa.aview.tui.TextUI;
import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;

public class Arimaa {

	private static Scanner scanner;
	private TextUI tui;
	private ArimaaFrame gui;
	protected IArimaaController controller;
	private static Arimaa instance = null;

	private Arimaa() {
		Injector injector = Guice.createInjector(new ArimaaModule());

		controller = injector.getInstance(IArimaaController.class);
		tui = new TextUI(controller);
		gui = new ArimaaFrame(controller);

		// init new game
		controller.createNewGame();




	}

	public TextUI getTui() {
		return tui;
	}


	public ArimaaFrame getGui() {
		return gui;
	}

	private static Arimaa getInstance() {
		if (instance == null) {
			instance = new Arimaa();
		}
		return instance;
	}

	private boolean exit() {
		return controller.getGameStatus().equals(GameStatus.EXIT);
	}

	public static void main(final String[] args) {
		Arimaa game = Arimaa.getInstance();

		scanner = new Scanner(System.in);
		while (!game.exit()) {
			game.tui.processInputLine(scanner.next());
		}

	}

}
