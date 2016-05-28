package de.htwg.se.arimaa.controller;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.model.Player;
import de.htwg.se.arimaa.util.character.Position;

public class ArimaaController {
	private Pitch pitch;
	private Rules rules;

	private List<Character> figures1 = new ArrayList<>();
	private List<Character> figures2 = new ArrayList<>();

	public ArimaaController() {
		initPitchPlayer();
		rules = new Rules(pitch);
	}

	private void initPitchPlayer() {
		pitch = new Pitch("Player1", "Player2", figures1, figures2);
	}

	public void initdefaultPitch(int a) {

		if (a == 1) {
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
		}
		if (a == 2) {
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
		}
		initPitchPlayer();

	}

	public void ShowPitch() {
		System.out.println(pitch.toString());
	}

	public void setFigure(String player, String string) {
		String figurename = null;
		String position = null;
		String[] parts = string.split(" ");
		parts[0] = figurename;
		parts[1] = position;
		Position pos = new Position(readposx(position), readposy(position));
		CHARAKTER_NAME figur = readname(figurename);
		Character character = new Character(pos, figur);
		figures1.add(character);

	}

	public boolean moveFigur(int player, Position from, Position to) {

		return false;
	}

	private int readposx(String posx) {
		char[] c = posx.toCharArray();
		switch (c[0]){
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		default:
			throw new IllegalArgumentException(c[0] + " ist keine korrekte x-Koordinate. Sie muss zwischen a und h liegen");
		}
	}

	private int readposy(String posy) {
		char[] c = posy.toCharArray();
		switch (c[0]){
		case '1':
			return 0;
		case '2':
			return 1;
		case '3':
			return 2;
		case '4':
			return 3;
		case '5':
			return 4;
		case '6':
			return 5;
		case '7':
			return 6;
		case '8':
			return 7;
		default:
			throw new IllegalArgumentException(c[0] + " ist keine korrekte y-Koordinate. Sie muss zwischen 1 und 8 liegen");
		}
	}

	private CHARAKTER_NAME readname(String name) {
		switch (name){
		case "r":
			return CHARAKTER_NAME.r;
		case "c":
			return CHARAKTER_NAME.c;
		case "d":
			return CHARAKTER_NAME.d;
		case "h":
			return CHARAKTER_NAME.h;
		case "l":
			return CHARAKTER_NAME.l;
		case "e":
			return CHARAKTER_NAME.e;
		case "R":
			return CHARAKTER_NAME.R;
		case "C":
			return CHARAKTER_NAME.C;
		case "D":
			return CHARAKTER_NAME.D;
		case "H":
			return CHARAKTER_NAME.H;
		case "L":
			return CHARAKTER_NAME.L;
		case "E":
			return CHARAKTER_NAME.E;
		default:
			throw new IllegalArgumentException(name + " ist keine korrekter Figurenname.");
		}

	}

}
