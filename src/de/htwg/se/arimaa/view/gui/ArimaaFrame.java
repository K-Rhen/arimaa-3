package de.htwg.se.arimaa.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ArimaaFrame extends JFrame implements IObserver {


	private PitchPanel pitchpanel;
	private IArimaaController controller; //TODO I
	
	public ArimaaFrame(final IArimaaController controller) {
		this.controller = controller;
		//controller.addObserver(this);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		
		this.setJMenuBar(new ArimaaMenuBar(controller, this));
				
		pitchpanel = new PitchPanel(controller);
		mainPanel.add(pitchpanel,BorderLayout.SOUTH);
		

		
		this.setContentPane(mainPanel);
		
		this.setTitle("HTWG Arimaa");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(450, 580));
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}

	
}
