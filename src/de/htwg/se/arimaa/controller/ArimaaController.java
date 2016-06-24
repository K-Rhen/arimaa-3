package de.htwg.se.arimaa.controller;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.CharacterFactory;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.PitchFactory;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.character.Position;

public class ArimaaController {
	private Position position;
	private IPitch pitch;
	private Rules rules;
	private String player1Name ="Player1";
	private String player2Name= "Player2";

	private Position toPull; // benoetigt in pullFigureEnemy

//	private int[] figureSetList1 = { 8, 2, 2, 2, 1, 1 }; // 8 rabbits, 2 cats, 2
//															// dogs,2 horses, 1
//															// camel, 1elephant
//	private int[] figureSetList2 = { 8, 2, 2, 2, 1, 1 };

	public ArimaaController() {
		pitch = PitchFactory.getInstance(player1Name,player2Name);
		rules = new Rules(pitch);
	}

	// ---------------------Methods to set figures on Pitch-------------
	public void ShowPitch() {
		System.out.println(pitch.toString());
	}

	public void setFigure(String player, String string) {
//		char figurename = ' ';
//		char positionx = ' ';
//		char positiony = ' ';
//		if (!(string.length() == 4))
//			throw new IllegalArgumentException("Die Eingabe muss dem Format \"c h4\" entsprechen.");
//		char[] parts = string.toCharArray();
//		figurename = parts[0];
//		positionx = parts[2];
//		positiony = parts[3];
//		CHARAKTER_NAME figur = readname(figurename);
//		int x = readPosX(positionx);
//		int y = readPosY(positiony);
//		Position pos = new Position(x, y);
//		if (rules.occupiedCell(pos))
//			throw new IllegalArgumentException("Feld ist bereits belegt");
//		checkSetPosition(player, positiony);
//		checkSetFigure(player, figurename);
//		ICharacter character = CharacterFactory.getInstance(pos, figur);
//		if (player.equals("p1"))
//			figures1.add(character);
//		if (player.equals("p2"))
//			figures2.add(character);

	}

//	private void checkSetFigure(String player, char figure) {

//		boolean fehler = false;
//
//		if (player.equals("p1")) {
//			switch (figure) {
//			case 'r':
//				--figureSetList1[0];
//				if (figureSetList1[0] < 0)
//					fehler = true;
//				break;
//			case 'c':
//				--figureSetList1[1];
//				if (figureSetList1[1] < 0)
//					fehler = true;
//				break;
//			case 'd':
//				--figureSetList1[2];
//				if (figureSetList1[2] < 0)
//					fehler = true;
//				break;
//			case 'h':
//				--figureSetList1[3];
//				if (figureSetList1[3] < 0)
//					fehler = true;
//				break;
//			case 'l':
//				--figureSetList1[4];
//				if (figureSetList1[4] < 0)
//					fehler = true;
//				break;
//			case 'e':
//				--figureSetList1[5];
//				if (figureSetList1[5] < 0)
//					fehler = true;
//				break;
//
//			default:
//				throw new IllegalArgumentException("Unerwarteter Fehler");
//
//			}
//		}

//		if (player.equals("p2")) {
//			switch (figure) {
//			case 'r':
//				--figureSetList2[0];
//				if (figureSetList2[0] < 0)
//					fehler = true;
//				break;
//			case 'c':
//				--figureSetList2[1];
//				if (figureSetList2[1] < 0)
//					fehler = true;
//				break;
//			case 'd':
//				--figureSetList2[2];
//				if (figureSetList2[2] < 0)
//					fehler = true;
//				break;
//			case 'h':
//				--figureSetList2[3];
//				if (figureSetList2[3] < 0)
//					fehler = true;
//				break;
//			case 'l':
//				--figureSetList2[4];
//				if (figureSetList2[4] < 0)
//					fehler = true;
//				break;
//			case 'e':
//				--figureSetList2[5];
//				if (figureSetList2[5] < 0)
//					fehler = true;
//				break;
//
//			default:
//				throw new IllegalArgumentException("Unerwarteter Fehler");
//			}
//		}
//
//		if (fehler) {
//			throw new IllegalArgumentException("Alle Figuren des Typs " + figure + " wurden bereits gesetzt.");
//		}
//	}

	private void checkSetPosition(String player, char yPos) {
		if (player.equals("p1") && (yPos == '7' || yPos == '8')) {
			return;
		}
		if (player.equals("p2") && (yPos == '1' || yPos == '2')) {
			return;
		}

		String fehler = null;

		if (player.equals("p1"))
			fehler = "Position ist auserhalb deines Bereiches.\n" + "Dein Bereich liegt zwischen a8 und b7.";
		if (player.equals("p2"))
			fehler = "Position ist auserhalb deines Bereiches.\n" + "Dein Bereich liegt zwischen a1 und h2.";
		throw new IllegalArgumentException(fehler);
	}

	private int readPosX(char c) {

		switch (c) {
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
			throw new IllegalArgumentException(
					c + " ist keine korrekte x-Koordinate. Sie muss zwischen a und h liegen");
		}
	}

	private int readPosY(char c) {

		switch (c) {
		case '1':
			return 7;
		case '2':
			return 6;
		case '3':
			return 5;
		case '4':
			return 4;
		case '5':
			return 3;
		case '6':
			return 2;
		case '7':
			return 1;
		case '8':
			return 0;
		default:
			throw new IllegalArgumentException(
					c + " ist keine korrekte y-Koordinate. Sie muss zwischen 1 und 8 liegen");
		}
	}

	private CHARAKTER_NAME readname(char name) {
		switch (name) {
		case 'r':
			return CHARAKTER_NAME.r;
		case 'c':
			return CHARAKTER_NAME.c;
		case 'd':
			return CHARAKTER_NAME.d;
		case 'h':
			return CHARAKTER_NAME.h;
		case 'l':
			return CHARAKTER_NAME.l;
		case 'e':
			return CHARAKTER_NAME.e;
		case 'R':
			return CHARAKTER_NAME.R;
		case 'C':
			return CHARAKTER_NAME.C;
		case 'D':
			return CHARAKTER_NAME.D;
		case 'H':
			return CHARAKTER_NAME.H;
		case 'L':
			return CHARAKTER_NAME.L;
		case 'E':
			return CHARAKTER_NAME.E;
		default:
			throw new IllegalArgumentException(name + " ist keine korrekter Figurenname.");
		}

	}

	// ---------------------Methods to play -------------------------

	public void checkEingabe(int player, String eingabe, boolean pull) {
		if (eingabe.length() != 5) {
			throw new IllegalArgumentException("Die Eingabe muss dem Format \"c6-d6\" entsprechen.");
		}
		char[] parts = eingabe.toCharArray();
		Position from = new Position(readPosX(parts[0]), readPosY(parts[1]));
		Position to = new Position(readPosX(parts[3]), readPosY(parts[4]));
		if (pull) {
			checkAblePull(from, toPull, player); // bevor figur gezogen -> erst
													// pruefen ob es moeglich
													// ist
		}

		// ist figur auch vom eigenen Spieler
		if (player == 1)
			if (!isFigurOwn(pitch.getP1().getFigures(), from))
				throw new IllegalArgumentException("Figur gehoert dir nicht");
			else if (!isFigurOwn(pitch.getP2().getFigures(), from))
				throw new IllegalArgumentException("Figur gehoert dir nicht");

		if (!moveFigur(player, from, to))
			throw new IllegalArgumentException("Ungueltiger Zug");
	}

	private boolean isFigurOwn(List<ICharacter> figures, Position from) {

		for (ICharacter usedchar : figures) {
			if (usedchar.getPosition().equals(from))
				return true;
		}
		return false; 
	}

	public boolean moveFigur(int player, Position from, Position to) {
		if (!rules.posDistance(from, to))
			throw new IllegalArgumentException("Du kannst hoechstens 1 Feld weiter ziehen.");
		if (rules.occupiedCell(to))
			throw new IllegalArgumentException("Die Position auf welche du ziehen willst ist bereits belegt");

		if (player == 1) {
			ICharacter characterStart = CharacterFactory.getInstance(from, pitch.getP1().getFigur(from));

			for (ICharacter usedchar : pitch.getP1().getFigures()) {
				if (usedchar.getPosition().equals(characterStart.getPosition()))
					usedchar.setPosition(to);
			}

			return true;
		}
		if (player == 2) {
			ICharacter characterStart = CharacterFactory.getInstance(from, pitch.getP2().getFigur(from));

			for (ICharacter usedchar : pitch.getP2().getFigures()) {
				if (usedchar.getPosition().equals(characterStart.getPosition()))
					usedchar.setPosition(to);
				;
			}

			return true;
		}

		return false;
	}

//	public void initializePitch(String playername1, String playername2) {
//
//		//pitch = PitchFactory.getInstance(player1.getPlayerName(), player2.getPlayerName());
//		pitch = PitchFactory.getInstance(player1,player2);
//	}

	public void pullFigureEnemy(boolean firstPlayer, String pull) { // markiert
																	// die
																	// gegnerische
																	// Figur die
																	// gezogen
																	// werden
																	// soll
		if (pull.length() != 2) {
			throw new IllegalArgumentException("Falschen Format. Die Eingabe muss z.b. \"d4\" sein");
		}
		char[] pullPosition = pull.toCharArray();
		toPull = new Position(readPosX(pullPosition[0]), readPosY(pullPosition[1]));
		if (firstPlayer) {
			if (isFigurOwn(pitch.getP1().getFigures(), toPull))
				throw new IllegalArgumentException("Es muss eine gegnerische Figur sein.");
		} else {
			if (isFigurOwn(pitch.getP2().getFigures(), toPull))
				throw new IllegalArgumentException("Es muss eine gegnerische Figur sein.");
		}

	}

	public void pullFigureOwn(boolean firstPlayer, String ziehen) {
		if (firstPlayer) {
			checkEingabe(1, ziehen, true);
			char[] tmp = ziehen.toCharArray();
			Position to = new Position(readPosX(tmp[0]), readPosY(tmp[1]));
			moveFigur(2, toPull, to);
		} else {
			checkEingabe(2, ziehen, true);
			char[] tmp = ziehen.toCharArray();
			Position to = new Position(readPosX(tmp[0]), readPosY(tmp[1]));
			moveFigur(1, toPull, to);
		}

	}

	private void checkAblePull(Position from, Position to, int player) {
		// noch pr�fen ob figuren jeweils dem anderen geh�ren
		// noch pr�fen ob die figuren nebeneinander stehen
		if (position.nextTo(from, to)) {

		}

	}

	public Player getPlayer1(){
		return pitch.getP1();
	}
	public Player getPlayer2(){
		return pitch.getP2();
	}
}
