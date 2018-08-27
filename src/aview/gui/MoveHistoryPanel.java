package aview.gui;

import controller.IArimaaController;
import util.observer.Event;
import util.observer.IObserver;

import javax.swing.*;

public class MoveHistoryPanel extends JPanel implements IObserver {
    private final IArimaaController controller;

	private final JTextArea historyTextArea;
	
	public MoveHistoryPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		historyTextArea = new JTextArea(20, 20);
		historyTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(historyTextArea);
		add(scrollPane);

	}

	@Override
	public void update(Event e) {

		String moveHistoryText = controller.getMoveHistoryText();
		historyTextArea.setText(moveHistoryText);

	}
}
