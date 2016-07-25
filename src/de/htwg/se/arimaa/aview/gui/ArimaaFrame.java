package de.htwg.se.arimaa.aview.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ArimaaFrame extends JFrame implements IObserver {

	private static final int DEFAULT_Y = 600;
	private static final int DEFAULT_X = 580;
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

//		JPanel mainPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

//		pitchpanel = new PitchPanel(controller);
//		mainPanel.add(pitchpanel,BorderLayout.SOUTH);

//		this.setContentPane(mainPanel);
//		this.pack();
//		this.setVisible(true);
	}

	private void constructArimaaPane(IArimaaController controller2) {
//		if (digitPanel != null) {
//			pane.remove(digitPanel);
//		}
//		digitPanel = new HighlightButtonPanel(controller);
//		pane.add(digitPanel, BorderLayout.NORTH);
//
//		if (gridPanel != null) {
//			pane.remove(gridPanel);
//		}
		pitchPanel = new PitchPanel(controller);
		pane.add(pitchPanel, BorderLayout.CENTER);

//		if (statusPanel != null) {
//			pane.remove(statusPanel);
//		}
//		statusPanel = new StatusPanel();
//		pane.add(statusPanel, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}

	@Override
	public void update(Event e) {
//		statusPanel.setText(controller.getStatus());
//		if (e instanceof SizeChangedEvent) {
//			constructSudokuPane(controller);
//		}
		repaint();
	}
	
}
