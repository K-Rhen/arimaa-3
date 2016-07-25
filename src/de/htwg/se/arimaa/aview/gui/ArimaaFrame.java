package de.htwg.se.arimaa.aview.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ArimaaFrame extends JFrame implements IObserver {

	private static final int DEFAULT_Y = 600;
	private static final int DEFAULT_X = 580;
	private ButtonPanel buttonPanel;
	private PitchPanel pitchPanel;
	private Container pane;
	private IArimaaController controller;

	@Inject
	public ArimaaFrame(final IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		this.setTitle("HTWG Arimaa");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_X, DEFAULT_Y);
		this.setJMenuBar(new ArimaaMenuBar(controller, this));
		pane = getContentPane();
		pane.setLayout(new BorderLayout());
		constructArimaaPane(controller);

		// Closing window handler
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				controller.quitGame();
			}
		});
	}

	private void constructArimaaPane(IArimaaController controller) {
		// if (digitPanel != null) {
		// pane.remove(digitPanel);
		// }
		// digitPanel = new HighlightButtonPanel(controller);
		// pane.add(digitPanel, BorderLayout.NORTH);
		//
		// if (gridPanel != null) {
		// pane.remove(gridPanel);
		// }
		pitchPanel = new PitchPanel(controller);
		pane.add(pitchPanel, BorderLayout.CENTER);

		buttonPanel = new ButtonPanel(controller);
		pane.add(buttonPanel,BorderLayout.SOUTH);
		// if (statusPanel != null) {
		// pane.remove(statusPanel);
		// }
		// statusPanel = new StatusPanel();
		// pane.add(statusPanel, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}

	@Override
	public void update(Event e) {
		GameStatus gs = controller.getGameStatus();
		if (gs.equals(GameStatus.EXIT)) {
			this.setVisible(false);
			this.dispose();
			return;
		}

		repaint();
	}

}
