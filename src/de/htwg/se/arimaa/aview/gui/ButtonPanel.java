package de.htwg.se.arimaa.aview.gui;

import java.awt.Point;
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
	Point playerChangePoint = new Point(270, 460);
	
	 public ButtonPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		initButtons();
	}

	private void initButtons() {
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
