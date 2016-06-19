package de.htwg.se.arimaa.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.htwg.se.arimaa.controller.ArimaaController;

public class ArimaaMenuBar extends JMenuBar {

	ArimaaController controller;

	JMenu fileMenu;
	JMenuItem newMenuItem, quitMenuItem;

	JMenu infoMenu;
	JMenuItem helpMenuItem;

	public ArimaaMenuBar(ArimaaController controller, JFrame frame) {
		this.controller = controller;
		createFileMenu(controller, frame);
		createInfoMenu(controller,frame);
	}

	private void createFileMenu(ArimaaController controller, JFrame frame) {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO make a new game
			}
		});
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

		fileMenu.add(newMenuItem);

		fileMenu.addSeparator();

		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		fileMenu.add(quitMenuItem);

		this.add(fileMenu);
	}

	private void createInfoMenu(ArimaaController controller, JFrame frame) {
		infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F);
		
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.setMnemonic(KeyEvent.VK_H);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		helpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//TODO help text
			}
		});
		
		infoMenu.add(helpMenuItem);
		this.add(infoMenu);
	}

}
