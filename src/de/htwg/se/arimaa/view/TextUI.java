package de.htwg.se.arimaa.view;


import java.util.List;
import java.util.Scanner;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.Character;
import de.htwg.se.arimaa.model.Player;

import java.lang.System;

public class TextUI {
	
	Scanner EINGABE = new Scanner(System.in);
	ArimaaController controller = new ArimaaController();
	
	private Player p1;
	private List<Character> figures1;
	private Player p2;
	private List<Character> figures2;
	
	
	public void start(){
			
		System.out.println("Hallo. Willkommen zu Arimaa.");
		System.out.println("Spieler eins bitte geb deinen Namen ein.\n");
		p1 = new Player(EINGABE.nextLine(), figures1);
		System.out.println("\nHallo " + p1.getPlayerName() + "\n");
		System.out.println("Spieler zwei bitte geb deinen Namen ein.\n");
		p2 = new Player(EINGABE.nextLine(), figures2);
		System.out.println("\nHallo " + p2.getPlayerName() + "\n");
		
		System.out.println(p1.getPlayerName() + " bitte setze deine Figuren,");
		System.out.println("indem du z.B. \"R a1\" eingibst.");
		
	}
	
	public void run(){
		
		while(true){
			
			controller.ShowPitch();
			System.out.printf("Mache einen Zug <z.B. mit a3-a4>\n");
			System.out.println("help fuer Hilfe\n");
			
			String eingabe = EINGABE.nextLine();

			if (eingabe.equals("exit")){
				return;
			}
			if(eingabe.equals("help")){
				System.out.println("TODO");
			}
			
			
		}
	}

}
