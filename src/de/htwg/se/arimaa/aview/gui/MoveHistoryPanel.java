package de.htwg.se.arimaa.aview.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class MoveHistoryPanel extends JPanel implements IObserver {
	IArimaaController controller;

	private JTextArea historyTextArea;
	
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
