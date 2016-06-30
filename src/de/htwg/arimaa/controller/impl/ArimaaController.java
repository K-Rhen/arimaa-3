package de.htwg.arimaa.controller.impl;


import java.util.List;

import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.PitchFactory;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.character.Position;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.view.TextUI;

public class ArimaaController  extends Observable implements IArimaaController{

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
		
		// Überprüfen ob gewonnen
		rules.isfinish();
		
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
