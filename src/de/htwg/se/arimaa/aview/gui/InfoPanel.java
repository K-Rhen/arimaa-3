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
	Point actPlayerPoint = new Point(25, 10);
	JLabel moveRemainLabel;
	Point moveRemainPoint = new Point(340, 10);

	public InfoPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		iniInfoPanel();
	}

	private void iniInfoPanel() {
		actPlayerLabel = new JLabel("Player: 1");
		actPlayerLabel.setBounds(actPlayerPoint.x, actPlayerPoint.y, 100, 20);
		this.add(actPlayerLabel);
		moveRemainLabel = new JLabel("Moves: 4");
		moveRemainLabel.setBounds(moveRemainPoint.x, moveRemainPoint.y, 100, 20);
		this.add(moveRemainLabel);

	}

	@Override
	public void update(Event e) {
		GameStatus gs = controller.getGameStatus();

		if (gs.equals(GameStatus.REMAINMOVE_CHANGE)) {
			moveRemainLabel.setText("Moves: " + controller.getRemainingMoves());
		} else if (gs.equals(GameStatus.CHANGEPLAYER)) {
			actPlayerLabel.setText("Player: " + controller.getCurrentPlayerName());
			moveRemainLabel.setText("Moves: " + controller.getRemainingMoves());
		}

	}

}
