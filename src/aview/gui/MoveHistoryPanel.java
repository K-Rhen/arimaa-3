package aview.gui;

import controller.IArimaaController;
import util.observer.Event;
import util.observer.IObserver;

import javax.swing.*;

public class MoveHistoryPanel extends JPanel implements IObserver {
	private IArimaaController controller;

	private final JTextArea historyTextArea;
	
	public MoveHistoryPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		historyTextArea = new JTextArea(20, 20);
		historyTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(historyTextArea);
		add(scrollPane);

	}

	public final void append(String line) {
		historyTextArea.append(line + "\n");
	}

	public void clear() {
		historyTextArea.setText("");
	}

	@Override
	public void update(Event e) {

		String moveHistoryText = controller.getMoveHistoryText();
		historyTextArea.setText(moveHistoryText);

	}
}
