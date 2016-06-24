package de.htwg.se.arimaa.arimaa;


import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.controller.IArimaaControllerFactory;
import de.htwg.se.arimaa.view.TextUI;
import de.htwg.se.arimaa.view.gui.ArimaaFrame;


public class Arimaa {
	protected IArimaaController controller;
	
	private TextUI tui;
	private ArimaaFrame gui;
	
	private Arimaa() {
		
		controller = IArimaaControllerFactory.getInstance(); //TODO inject
		
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
	     
//		game.tui.start();
		game.tui.fastStart();  //zum schnellen starten des spiels ohne eingabe. sobald tui lauft, loeschen.
		game.tui.run(); 
		
	}

}
