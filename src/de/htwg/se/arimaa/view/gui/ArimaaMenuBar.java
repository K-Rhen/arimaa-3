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
		createInfoMenu(controller,frame);
	}

	private void createFileMenu(IArimaaController controller, JFrame frame) {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String[] yesNoOptions = { "Ja", "Nein"};
				int n = JOptionPane.showOptionDialog( null,
				          "Neus Spiel?",             
				          "Neus Spiel?",          
				          JOptionPane.YES_NO_OPTION,
				          JOptionPane.QUESTION_MESSAGE, 
				          null, yesNoOptions,yesNoOptions[0] );

				if ( n == JOptionPane.YES_OPTION )
				  System.out.println("Ja gewählt");
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
				System.exit(0);
			}
		});
		fileMenu.add(quitMenuItem);

		this.add(fileMenu);
	}

	private void createInfoMenu(IArimaaController controller, JFrame frame) {
		infoMenu = new JMenu("Info");
		infoMenu.setMnemonic(KeyEvent.VK_F);
		
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.setMnemonic(KeyEvent.VK_H);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		helpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog( null, "Arimaa Blabla info" );
				//TODO help text
			}
		});
		
		infoMenu.add(helpMenuItem);
		this.add(infoMenu);
	}

}
