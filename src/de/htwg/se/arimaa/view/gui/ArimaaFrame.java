package de.htwg.se.arimaa.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;

import javax.swing.JFrame;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ArimaaFrame extends JFrame implements IObserver {

	private static final int DEFAULT_Y = 630;
	private static final int DEFAULT_X = 528;
	private Container pane;
	//private GridPanel gridPanel;
//	private HighlightButtonPanel digitPanel;
//	private StatusPanel statusPanel;
	private ArimaaController controller; //TODO I
	
	public ArimaaFrame(final ArimaaController controller) {
		this.controller = controller;
		//controller.addObserver(this);

		setTitle("HTWG Arimaa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_X, DEFAULT_Y);
		setJMenuBar(new ArimaaMenuBar(controller, this));
		pane = getContentPane();
		pane.setLayout(new BorderLayout());	
		constructArimaaPane(controller);
	}
	
	public final void constructArimaaPane(ArimaaController controller) {
//		if (digitPanel != null) {
//			pane.remove(digitPanel);
//		}
//		digitPanel = new HighlightButtonPanel(controller);
//		pane.add(digitPanel, BorderLayout.NORTH);
//
//		if (gridPanel != null) {
//			pane.remove(gridPanel);
//		}
//		gridPanel = new GridPanel(controller);
//		pane.add(gridPanel, BorderLayout.CENTER);
//
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
		// TODO Auto-generated method stub
		
	}

	
}
