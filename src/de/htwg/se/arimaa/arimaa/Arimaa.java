package de.htwg.se.arimaa.arimaa;

import de.htwg.se.arimaa.view.TextUI;



public class Arimaa {
	private Arimaa() {
	}

	public static void main(final String[] args) {
		TextUI tui = new TextUI();
		//tui.start();
		tui.fastStart();  //zum schnellen starten des spiels ohne eingabe. sobald tui lauft, loeschen.
		tui.run(); 
		
	}

}
