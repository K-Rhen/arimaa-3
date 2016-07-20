package de.htwg.se.arimaa.aview.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;

public class ArimaaFrame extends JFrame implements IObserver {


	private PitchPanel pitchpanel;
	
	public ArimaaFrame(final IArimaaController controller) {
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
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub
		
	}
	
}
