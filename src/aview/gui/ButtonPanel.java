package aview.gui;

import controller.IArimaaController;
import util.observer.Event;
import util.observer.IObserver;

import javax.swing.*;

public class ButtonPanel extends JPanel implements IObserver {
    private IArimaaController controller;

    private JButton playerChangeButton;
    private JButton undoButton;
    private JButton redoButton;

    public ButtonPanel(IArimaaController controller) {
        this.controller = controller;
        controller.addObserver(this);

        initButtons();
    }

    private void initButtons() {
        playerChangeButton = new JButton("change player");
        playerChangeButton.addActionListener(ae ->
                controller.changePlayer());
        this.add(playerChangeButton);

        undoButton = new JButton("<-");
        undoButton.addActionListener(ae ->
                controller.undo());
        this.add(undoButton);

        redoButton = new JButton("->");
        redoButton.addActionListener(ae ->
                controller.redo());
        this.add(redoButton);

    }

    @Override
    public void update(Event e) {
        playerChangeButton.setEnabled(controller.isChangePlayerEnable());

        undoButton.setEnabled(!controller.isUndoListEmpty());
        redoButton.setEnabled(!controller.isRedoListEmpty());
    }

}
