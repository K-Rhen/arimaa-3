package de.htwg.se.arimaa.aview.gui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;
import de.htwg.se.arimaa.util.position.Position;

public class ButtonPanel extends JPanel implements IObserver {

	IArimaaController controller;

	// BUTTON
	JButton commitButton;
	Point commitPoint = new Point(20, 460);
	JButton playerChangeButton;
	Point playerChangePoint = new Point(270, 460);
	
	 public ButtonPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		initButtons();
	}

	private void initButtons() {
		commitButton = new JButton("bestaetigen");
		commitButton.setBounds(commitPoint.x, commitPoint.y, 130, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//TODO
			}
		});
		this.add(commitButton);

		playerChangeButton = new JButton("Runde beenden");
		playerChangeButton.setBounds(playerChangePoint.x, playerChangePoint.y, 150, 30);
		playerChangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				controller.changePlayer();
			}
		});
		this.add(playerChangeButton);
		
	}

	@Override
	public void update(Event e) {
		// TODO Auto-generated method stub

	}

}
