package de.htwg.se.arimaa.aview.gui;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class InfoPanel extends JPanel implements IObserver {
	IArimaaController controller;

	JLabel actPlayerLabel;
	JLabel moveRemainLabel;
	
	public InfoPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		iniInfoPanel();
	}

	private void iniInfoPanel() {
		actPlayerLabel = new JLabel(getPlayerText());
		this.add(actPlayerLabel);
		
		moveRemainLabel = new JLabel(getMoveRemainText());
		this.add(moveRemainLabel);

	}

	private String getPlayerText(){
		return "Player: " + controller.getCurrentPlayerName();
	}
	
	private String getMoveRemainText(){
		return "Moves: " + controller.getRemainingMoves();
	}
	
	@Override
	public void update(Event e) {
		//GameStatus gs = controller.getGameStatus();

			actPlayerLabel.setText(getPlayerText());
			moveRemainLabel.setText(getMoveRemainText());
	}

}
