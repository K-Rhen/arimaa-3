package de.htwg.se.arimaa.arimaa;

import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.view.TextUI;

public class Arimaa {

	private static Scanner scanner;
	private static TextUI tui;
	private static ArimaaController controller;

	public static void main(final String[] args) {

		// TODO implements
		Pitch p = new Pitch("Player1", "Player2");
		System.out.println(p.toString());

	}

}
