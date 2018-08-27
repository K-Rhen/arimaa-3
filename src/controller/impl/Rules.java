package controller.impl;

import controller.GameStatus;
import controller.IArimaaController;
import model.FIGURE_NAME;
import model.IFigure;
import model.PLAYER_NAME;
import util.observer.Observable;
import util.position.Coordinate;
import util.position.DIRECTION;
import util.position.Position;

import java.util.ArrayList;
import java.util.List;

class Rules extends Observable {

    private final IArimaaController controller;
    private GameStatus status;
    private String statusText;

    Rules(IArimaaController controller) {
        this.controller = controller;
        status = GameStatus.CREATE;

        statusText = "";
    }

    GameStatus getStatus() {
        return status;
    }

    String getStatusText() {
        return statusText;
    }

    private boolean preconditionStateLess(PLAYER_NAME currentPlayerName, Position from, Position to) {
        // from position is empty
        if (controller.getPlayerName(from) == null) {
            statusText = "No figure on " + Coordinate.convert(from);
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        // to position is occupied
        if (controller.getPlayerName(to) != null) {
            statusText = Coordinate.convert(from) + " is not empty";
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        // is figure a rabbit move backwards
        if (isRabbitMoveBackward(from, to)) {
            statusText = "Rabbits can't go backwards";
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        // is pushed
        if (isPushed(from, to)) {
            statusText = "Figure is pushed";
            status = GameStatus.PUSH_FIGURE;
            return true;
        }

        // is hold
        if (isHold(from)) {
            statusText = "Figure is hold";
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        // is pulled
        if (isPulled(from, to)) {
            statusText = "Figure is pulled";
            status = GameStatus.MOVE_FIGURE;
            return true;
        }

        // is to position a possible move
        List<Position> possibleMoves = getFreeOwnSurroundPositions(currentPlayerName, from);
        if (possibleMoves.isEmpty() || !possibleMoves.contains(to)) {
            statusText = Coordinate.convert(to) + " is not a permitted position";
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        status = GameStatus.MOVE_FIGURE;
        statusText = "from " + Coordinate.convert(from) + "  to " + Coordinate.convert(to);
        return true;
    }

    boolean precondition(PLAYER_NAME currentPlayerName, Position from, Position to) {
        if (controller.getRemainingMoves() == 0) {
            statusText = "No remain moves";
            status = GameStatus.PRECONDITION_RULES_VIOLATED;
            return false;
        }

        // pushed must be finished
        if (controller.getGameStatus().equals(GameStatus.PUSH_FIGURE)) {
            Position lastFromPosition = controller.getLastMoveFromPosition();
            if (!lastFromPosition.equals(to)) {
                statusText = "First finish your push to " + Coordinate.convert(lastFromPosition);
                status = GameStatus.PUSH_FIGURE;
                return false;
            }
        }

        return preconditionStateLess(currentPlayerName, from, to);
    }

    boolean postcondition(Position to) {
        // trap rule
        if (isCaptured()) {
            statusText = "Figure captured";
            status = GameStatus.CAPTURED;
        }

        // game finish
        if (isGameFinish(to))
            return true;

        // circular move
        if (isCircularMove()) {
            statusText = "Last four moves are circular";
            status = GameStatus.CIRCULAR_MOVE;
        }

        return true;
    }


    private boolean isCircularMove() {
        // TODO circular move and TEST
        return false;
    }

    private boolean isRabbitMoveBackward(Position from, Position to) {
        // is Figure a Rabbit
        FIGURE_NAME figureName = controller.getFigureName(from);
        if (!figureName.equals(FIGURE_NAME.R))
            return false;

        PLAYER_NAME playerName = controller.getPlayerName(from);
        DIRECTION direction = Position.getDirection(from, to);
        if (direction == null)
            return false;

        if (playerName.equals(PLAYER_NAME.GOLD) && !DIRECTION.SOUTH.equals(direction))
            return false;

        return !playerName.equals(PLAYER_NAME.SILVER) || DIRECTION.NORTH.equals(direction);
    }

    private boolean isHold(Position pos) {
        List<Position> ownFigures = new ArrayList<>();
        PLAYER_NAME ownPlayerName = controller.getPlayerName(pos);
        ownFigures = getSurroundFigures(ownPlayerName, pos);

        // is surround by a own figure
        if (!ownFigures.isEmpty())
            return false;

        List<Position> otherFigures = new ArrayList<>();
        PLAYER_NAME otherPlayerName = PLAYER_NAME.invert(ownPlayerName);
        otherFigures = getSurroundFigures(otherPlayerName, pos);

        // own figure is not surrounded by a enemy
        if (otherFigures.isEmpty())
            return false;

        FIGURE_NAME ownFigureName = controller.getFigureName(pos);
        FIGURE_NAME otherStrongestFigureName = getStrongestFigure(otherFigures);

        // other surround figure is stronger than own figure
        return ownFigureName.compareTo(otherStrongestFigureName) < 0;
    }

    private boolean isPushed(Position from, Position to) {
        PLAYER_NAME actPlayerName = controller.getPlayerName(from);
        // actual move figure is from other player
        if (actPlayerName == null || actPlayerName.equals(controller.getCurrentPlayerName()))
            return false;

        // actual move figure to position equals surround position
        List<Position> actSurroundPositions = new ArrayList<>();
        actSurroundPositions = Position.getSurroundPositionForPitch(from);
        if (!actSurroundPositions.contains(to))
            return false;

        // actual move figure surround by a other player figure
        List<Position> otherPlayerPositions = new ArrayList<>();
        otherPlayerPositions = getFiguresPositionsFromPlayer(controller.getCurrentPlayerName(), actSurroundPositions);

        if (otherPlayerPositions.isEmpty())
            return false;

        // other player figure stronger than actual move figure
        FIGURE_NAME strongestOtherFigure = getStrongestFigure(otherPlayerPositions);
        FIGURE_NAME actFigureName = controller.getFigureName(from);
        return strongestOtherFigure != null && strongestOtherFigure.compareTo(actFigureName) > 0;
    }

    private boolean isPulled(Position from, Position to) {
        PLAYER_NAME actPlayerName = controller.getPlayerName(from);
        // actual move figure is from other player
        if (actPlayerName == null || actPlayerName.equals(controller.getCurrentPlayerName()))
            return false;

        // last move figure stronger than pulled figure
        FIGURE_NAME lastFigureName = controller.getLastMoveFigureName();
        FIGURE_NAME actFigureName = controller.getFigureName(from);
        if (lastFigureName == null || lastFigureName.compareTo(actFigureName) <= 0)
            return false;

        // last from position must be act to position
        Position lastFromPosition = controller.getLastMoveFromPosition();
        return lastFromPosition != null && lastFromPosition.equals(to);
    }

    private boolean isGameFinish(Position to) {
        PLAYER_NAME wonPlayerName = null;
        // immobilization
        wonPlayerName = isImmobile();
        if (wonPlayerName != null) {
            statusText = wonPlayerName + " won the game, by immobilization";
            status = GameStatus.FINISH;
            return true;
        }

        // elimination
        wonPlayerName = isEliminated();
        if (wonPlayerName != null) {
            statusText = wonPlayerName + " won the game, by elimination";
            status = GameStatus.FINISH;
            return true;
        }

        // goal
        wonPlayerName = isGoal(to);
        if (wonPlayerName != null) {
            statusText = wonPlayerName + " won the game, by goal";
            status = GameStatus.FINISH;
            return true;
        }

        return false;
    }

    private PLAYER_NAME isImmobile() {
        if (isImmobile(PLAYER_NAME.GOLD))
            return PLAYER_NAME.SILVER;
        else if (isImmobile(PLAYER_NAME.SILVER))
            return PLAYER_NAME.GOLD;
        return null;
    }

    private PLAYER_NAME isEliminated() {
        if (controller.noRabbits(PLAYER_NAME.GOLD))
            return PLAYER_NAME.SILVER;
        else if (controller.noRabbits(PLAYER_NAME.SILVER))
            return PLAYER_NAME.GOLD;

        return null;
    }

    private PLAYER_NAME isGoal(Position to) {
        FIGURE_NAME figureName = controller.getFigureName(to);
        if (figureName == null || !figureName.equals(FIGURE_NAME.R))
            return null;

        PLAYER_NAME playerName = controller.getPlayerName(to);

        if (playerName.equals(PLAYER_NAME.GOLD) && to.getY() == 0)
            return PLAYER_NAME.GOLD;
        else if (playerName.equals(PLAYER_NAME.SILVER) && to.getY() == 7)
            return PLAYER_NAME.SILVER;

        return null;
    }

    private boolean isImmobile(PLAYER_NAME playerName) {
        List<IFigure> figures = new ArrayList<>();
        if (playerName.equals(PLAYER_NAME.GOLD))
            figures = controller.getGoldFigures();
        else
            figures = controller.getSilverFigures();

        Rules r = new Rules(controller);

        int os = 0;
        for (IFigure figure : figures) {
            Position pos = figure.getPosition();
            List<Position> possiblePosList = r.getPossibleMovesStateLess(playerName, pos);
            if (!possiblePosList.isEmpty())
                os++;
        }

        return os == 0;
    }

    private boolean isCaptured() {
        List<Position> traps = new ArrayList<>();
        traps.add(new Position(2, 2));
        traps.add(new Position(5, 2));
        traps.add(new Position(2, 5));
        traps.add(new Position(5, 5));

        boolean result = false;

        for (Position actTrapPos : traps) {
            if (controller.getFigureName(actTrapPos) != null) {

                List<Position> ownFigures = new ArrayList<>();
                PLAYER_NAME ownPlayerName = controller.getPlayerName(actTrapPos);
                ownFigures = getSurroundFigures(ownPlayerName, actTrapPos);

                // is surround by a own figure
                if (ownFigures.isEmpty()) {
                    controller.disableFigure(actTrapPos);
                    result = true;
                }
            }
        }
        return result;
    }

    private List<Position> getSurroundFigures(PLAYER_NAME playerName, Position pos) {
        List<Position> candidates = new ArrayList<>();
        candidates = Position.getSurroundPositionForPitch(pos);
        candidates = getOccupiedPositions(candidates);

        return getFiguresPositionsFromPlayer(playerName, candidates);
    }

    private List<Position> getFreeOwnSurroundPositions(PLAYER_NAME playerName, Position pos) {
        List<Position> candidates = new ArrayList<>();

        if (!controller.getPlayerName(pos).equals(playerName))
            return candidates;

        candidates = Position.getSurroundPositionForPitch(pos);
        candidates.removeAll(getOccupiedPositions(candidates));

        return candidates;
    }

    private FIGURE_NAME getStrongestFigure(List<Position> surroundPosList) {
        FIGURE_NAME figureName = null;
        for (Position pos : surroundPosList) {
            if (figureName == null)
                figureName = controller.getFigureName(pos);

            FIGURE_NAME actFigureName = controller.getFigureName(pos);
            if (actFigureName.compareTo(figureName) > 0)
                figureName = actFigureName;
        }
        return figureName;

    }

    private List<Position> getFiguresPositionsFromPlayer(PLAYER_NAME playerName, List<Position> surroundPosList) {
        List<Position> posList = new ArrayList<>();
        for (Position pos : surroundPosList) {
            PLAYER_NAME actPlayerName = controller.getPlayerName(pos);
            if (actPlayerName != null && actPlayerName.equals(playerName))
                posList.add(pos);
        }
        return posList;
    }

    private List<Position> getOccupiedPositions(List<Position> surroundPosList) {
        List<Position> occupied = new ArrayList<>();
        for (Position pos : surroundPosList) {
            if (controller.getPlayerName(pos) != null)
                occupied.add(pos);
        }
        return occupied;
    }

    private List<Position> getPossibleMovesStateLess(PLAYER_NAME currentPlayerName, Position pos) {
        List<Position> candidates = new ArrayList<>();
        candidates = Position.getSurroundPositionForPitch(pos);
        candidates.removeAll(getOccupiedPositions(candidates));

        for (int i = candidates.size() - 1; i >= 0; i--) {
            if (!preconditionStateLess(currentPlayerName, pos, candidates.get(i)))
                candidates.remove(i);
        }

        return candidates;
    }

    List<Position> getPossibleMoves(PLAYER_NAME currentPlayerName, Position pos) {
        List<Position> candidates = Position.getSurroundPositionForPitch(pos);
        candidates.removeAll(getOccupiedPositions(candidates));

        for (int i = candidates.size() - 1; i >= 0; i--) {
            if (!precondition(currentPlayerName, pos, candidates.get(i)))
                candidates.remove(i);
        }

        return candidates;
    }

}
