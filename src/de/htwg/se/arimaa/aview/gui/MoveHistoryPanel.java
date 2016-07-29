package de.htwg.se.arimaa.aview.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import de.htwg.se.arimaa.aview.StatusMessage;
import de.htwg.se.arimaa.controller.GameStatus;

public class MoveHistoryPanel extends JPanel {

	private JTextArea historyTextArea;
	private final static String newline = "\n";

	public MoveHistoryPanel() {
		historyTextArea = new JTextArea(20, 20);
		historyTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(historyTextArea);

		add(scrollPane);

	}

	public final void append(String line) {
		historyTextArea.append(line + newline);
	}

	public void clear() {
		historyTextArea.setText("");
	}
}
