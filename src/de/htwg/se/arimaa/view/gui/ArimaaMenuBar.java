package de.htwg.se.arimaa.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import de.htwg.arimaa.controller.impl.ArimaaController;
import de.htwg.se.arimaa.controller.IArimaaController;

public class ArimaaMenuBar extends JMenuBar {

	IArimaaController controller;

	JMenu fileMenu;
	JMenuItem newMenuItem, quitMenuItem;

	JMenu infoMenu;
	JMenuItem helpMenuItem;

	public ArimaaMenuBar(IArimaaController controller, JFrame frame) {
		this.controller = controller;
		createFileMenu(controller, frame);
		createInfoMenu(controller, frame);
	}

	private void createFileMenu(IArimaaController controller, JFrame frame) {
		fileMenu = new JMenu("Datei");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		newMenuItem = new JMenuItem("Neues Spiel");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String[] yesNoOptions = { "Ja", "Nein" };
				int n = JOptionPane.showOptionDialog(null, "Neus Spiel?", "Neus Spiel?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);

				if (n == JOptionPane.YES_OPTION)
					System.out.println("Ja gewählt");
				// TODO make a new game

			}
		});
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

		fileMenu.add(newMenuItem);
		this.add(fileMenu);
	}

	private void createInfoMenu(IArimaaController controller, JFrame frame) {
		infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F);

		helpMenuItem = new JMenuItem("Hilfe");
		helpMenuItem.setMnemonic(KeyEvent.VK_H);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		helpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"Entstanden im Rahmen der Vorlesung Softwareentwicklung an der HTWG Konstanz.\n"
						+ "Für mehr Informationen über Arimaa,\n"
						+ "siehe http://arimaa.com/arimaa/","Arimaa 2016",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		infoMenu.add(helpMenuItem);
		this.add(infoMenu);
	}

}
