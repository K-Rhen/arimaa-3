package de.htwg.arimaa.controller.impl;


import java.util.List;

import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.CharacterFactory;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.PitchFactory;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.character.Position;

public class ArimaaController implements IArimaaController{
	private Position position;
	private IPitch pitch;
	private Rules rules;
	private String player1Name ="Player1";
	private String player2Name= "Player2";

	private Position toPull; // benoetigt in pullFigureEnemy

	public ArimaaController() {
		pitch = PitchFactory.getInstance(player1Name,player2Name);
		rules = new Rules(pitch);
	}

	// ---------------------Methods to set figures on Pitch-------------
	public void ShowPitch() {
		System.out.println(pitch.toString());
	}

	public void setFigure(String player, String string) {

	}



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

	public boolean moveFigureByString(int player, String eingabe) {
		if (eingabe.length() != 5) {
			throw new IllegalArgumentException("Die Eingabe muss dem Format \"c6-d6\" entsprechen.");
		}
		char[] parts = eingabe.toCharArray();
		Position from = new Position(readPosX(parts[0]), readPosY(parts[1]));
		Position to = new Position(readPosX(parts[3]), readPosY(parts[4]));

		boolean able = moveFigur(player, from, to);
		if (!able)
			throw new IllegalArgumentException("Ungueltiger Zug");
			
		return able;
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

		if (player == 1) 
			return setFigureChangePosition(pitch.getP1(), from , to);
		if (player == 2) 
			return setFigureChangePosition(pitch.getP2(), from , to);
		return false;

	}
	private boolean setFigureChangePosition(Player p, Position from, Position to){
		return p.setFigureChangePositon(from, to);

	}


	public void pullFigureEnemy(boolean firstPlayer, String pull) { 
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
//		if (firstPlayer) {
//			checkEingabe(1, ziehen);
//			char[] tmp = ziehen.toCharArray();
//			Position to = new Position(readPosX(tmp[0]), readPosY(tmp[1]));
//			moveFigur(2, toPull, to);
//		} else {
//			checkEingabe(2, ziehen, true);
//			char[] tmp = ziehen.toCharArray();
//			Position to = new Position(readPosX(tmp[0]), readPosY(tmp[1]));
//			moveFigur(1, toPull, to);
//		}
//
	}



	public Player getPlayer1(){
		return pitch.getP1();
	}
	public Player getPlayer2(){
		return pitch.getP2();
	}

	public boolean pushFigurs(int player, String line) {
		
		String[] parts = line.split(" ");
		String part1 = parts[0]; 
		String part2 = parts[1];
		
		//TODO ÜBERPRÜFUNG
		boolean firstmove = moveFigureByString(player, part1);
		boolean secondmove = moveFigureByString(player, part2);
		
		return firstmove && secondmove;
	}
}
