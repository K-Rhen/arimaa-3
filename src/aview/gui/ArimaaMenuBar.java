package aview.gui;

import controller.IArimaaController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ArimaaMenuBar extends JMenuBar {
	private static final Logger LOGGER = LogManager.getLogger(ArimaaMenuBar.class.getName());

	private IArimaaController controller;

	private JMenu fileMenu;
	private JMenuItem newMenuItem, quitMenuItem;

	private JMenu infoMenu;
	private JMenuItem helpMenuItem;

	public ArimaaMenuBar(IArimaaController controller) {
		this.controller = controller;
		createFileMenu(controller);
		createInfoMenu();
	}

	private void createFileMenu(IArimaaController controller) {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		newMenuItem = new JMenuItem("new game");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String[] yesNoOptions = { "Yes", "No" };
				int n = JOptionPane.showOptionDialog(null, "Do you want to start a new game?", "New game?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);

				if (n == JOptionPane.YES_OPTION) {
					controller.createNewGame();
				}

			}
		});
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(newMenuItem);

		quitMenuItem = new JMenuItem("quit");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.quitGame();
			}
		});
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(quitMenuItem);

		this.add(fileMenu);
	}

	private void createInfoMenu() {
		infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F);

		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.setMnemonic(KeyEvent.VK_H);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		helpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ImageIcon icon = createImageIcon("/img/GoldElephant.png");

				JOptionPane.showMessageDialog(null,
						"This project is an implementation of the arimaa game.\n\n"
								+ "It was created for the lecture Software Engineering\n"
								+ "at the University of Applied Science Konstanz, Germany.\n\n"
								+ "For more information about arimaa,\n" + "          go to http://arimaa.com",
						"HTWG Arimaa, Germany 2016", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});

		infoMenu.add(helpMenuItem);
		this.add(infoMenu);
	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			LOGGER.error("Images: " + path + "not found");
			return null;
		}
	}

}