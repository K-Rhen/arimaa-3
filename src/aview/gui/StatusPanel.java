package aview.gui;

import aview.StatusMessage;
import controller.GameStatus;
import controller.IArimaaController;
import util.observer.Event;
import util.observer.IObserver;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusPanel extends JPanel implements IObserver {
    private IArimaaController controller;

    private final JLabel statusLabel = new JLabel("");

    public StatusPanel(IArimaaController controller) {
        this.controller = controller;
        controller.addObserver(this);

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statusLabel);
    }

    private final void setText(final GameStatus status, final String statusText) {
        statusLabel.setText(StatusMessage.getText(status) + " : " + statusText);
    }

    @Override
    public void update(Event e) {
        setText(controller.getGameStatus(), controller.getStatusText());
    }
}
