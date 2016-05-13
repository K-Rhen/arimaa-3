package de.htwg.se.arimaa.arimaa;

import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.util.character.Position;
import de.htwg.se.arimaa.view.TextUI;

public class Arimaa {

	private  Scanner scanner;
	private  TextUI tui;
	private  ArimaaController controller;

	public static void main(final String[] args) {

		// TODO implements
		ArimaaController controller = new ArimaaController();
		controller.ShowPitch();

		// TODO TEST
		// pitch.getP1().setFigureChangePositon(new Position(0,1), new
		// Position(0,2));
		// System.out.println(pitch.toString());
	}

}
