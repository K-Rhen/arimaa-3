package aview.gui;

import controller.IArimaaController;
import util.observer.Event;
import util.observer.IObserver;

import javax.swing.*;

public class InfoPanel extends JPanel implements IObserver {
	private final IArimaaController controller;

	private JLabel actPlayerLabel;
	private JLabel moveRemainLabel;

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

	private String getPlayerText() {
		return "player: " + controller.getCurrentPlayerName();
	}

	private String getMoveRemainText() {
		return "moves: " + controller.getRemainingMoves();
	}

	@Override
	public void update(Event e) {
		actPlayerLabel.setText(getPlayerText());
		moveRemainLabel.setText(getMoveRemainText());
	}

}
