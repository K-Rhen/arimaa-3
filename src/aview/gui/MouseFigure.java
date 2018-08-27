package aview.gui;

import model.FIGURE_NAME;
import model.PLAYER_NAME;
import util.position.Position;

import java.awt.*;

class MouseFigure {

    private Point mousePoint;
    private final FIGURE_NAME figureName;
    private final PLAYER_NAME playerName;
    private Position fromPosition;
    private Position toPosition;

    public MouseFigure(Point mousePoint, FIGURE_NAME figureName, PLAYER_NAME playerName, Position fromPosition) {
        this.mousePoint = mousePoint;
        this.figureName = figureName;
        this.playerName = playerName;
        this.fromPosition = fromPosition;
    }

    public Point getPosition() {
        return mousePoint;
    }

    public void setPoint(Point point) {
        this.mousePoint = point;
    }

    public FIGURE_NAME getFigureName() {
        return figureName;
    }

    public PLAYER_NAME getPlayer() {
        return playerName;
    }

    public Position getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(Position pos) {
        fromPosition = pos;

    }

    public double getX() {
        return mousePoint.getX();
    }

    public double getY() {
        return mousePoint.getY();
    }

    public Position getToPosition() {
        return toPosition;
    }

    public void setToPosition(Position toPosition) {
        this.toPosition = toPosition;
    }

}
