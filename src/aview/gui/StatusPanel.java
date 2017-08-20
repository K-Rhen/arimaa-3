package aview.gui;

import aview.StatusMessage;
import controller.GameStatus;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = -2136188660234901904L;
	private final JLabel statusLabel = new JLabel("");

	public StatusPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add(statusLabel);
	}

	public final void setText(final GameStatus status, final String statusText) {
		statusLabel.setText(StatusMessage.text.get(status) + " : " + statusText);
	}

	public void clear() {
		statusLabel.setText(" ");
	}
}
