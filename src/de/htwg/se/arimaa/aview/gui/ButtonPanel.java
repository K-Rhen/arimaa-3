package de.htwg.se.arimaa.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ButtonPanel extends JPanel implements IObserver {

	IArimaaController controller;

	JButton playerChangeButton;
	JButton undoButton;
	JButton redoButton;

	public ButtonPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		initButtons();
	}

	private void initButtons() {
		playerChangeButton = new JButton("change player");
		playerChangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				controller.changePlayer();
			}
		});
		this.add(playerChangeButton);

		undoButton = new JButton("<-");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO set Button disable
				controller.undo();
			}
		});
		this.add(undoButton);

		redoButton = new JButton("->");
		redoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO set Button disable
				controller.redo();
			}
		});
		this.add(redoButton);

	}

	@Override
	public void update(Event e) {
			playerChangeButton.setEnabled(controller.isChangePlayerEnable());
		
			undoButton.setEnabled(!controller.isUndoListEmpty());
			redoButton.setEnabled(!controller.isRedoListEmpty());
	}

}
