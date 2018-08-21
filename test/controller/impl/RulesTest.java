package controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.GameStatus;
import controller.IArimaaController;
import main.ArimaaModule;
import model.FIGURE_NAME;
import model.PLAYER_NAME;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.position.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    private IArimaaController controller;
    private Injector injector = Guice.createInjector(new ArimaaModule());

    @BeforeEach
    void setUp() {
        controller = injector.getInstance(IArimaaController.class);
    }

    @Test
    void testFromPositionIsEmpty() {
        assertFalse(controller.moveFigure(new Position(0, 5), new Position(0, 6)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
    }

    @Test
    void testToPositionIsOccupied() {
        assertFalse(controller.moveFigure(new Position(0, 6), new Position(1, 6)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
    }

    @Test
    void testNoMoveRemain() {
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 3)));
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 2)));
        assertFalse(controller.moveFigure(new Position(0, 2), new Position(1, 2)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
    }

    @Test
    void testIsRabbitMoveBackward() {
        // Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));

        // Gold Rabbit wants go backward
        assertFalse(controller.moveFigure(new Position(0, 4), new Position(0, 5)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

        controller.changePlayer();

        // Silver Rabbit down
        assertTrue(controller.moveFigure(new Position(7, 1), new Position(7, 2)));
        assertTrue(controller.moveFigure(new Position(7, 2), new Position(7, 3)));

        // Silver Rabbit wants go up
        assertFalse(controller.moveFigure(new Position(7, 3), new Position(7, 2)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

        // Silver Rabbit wants move far away position
        assertFalse(controller.moveFigure(new Position(7, 3), new Position(0, 4)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
    }

    @Test
    void testIsHold() {
        // Gold Camel
        assertTrue(controller.moveFigure(new Position(3, 6), new Position(3, 5)));
        assertTrue(controller.moveFigure(new Position(3, 5), new Position(3, 4)));
        controller.changePlayer();
        // Silver Elephant
        assertTrue(controller.moveFigure(new Position(3, 1), new Position(3, 2)));
        assertTrue(controller.moveFigure(new Position(3, 2), new Position(3, 3)));

        controller.changePlayer();
        // Gold camel wants to go right
        assertFalse(controller.moveFigure(new Position(3, 4), new Position(4, 4)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
        // Gold Cat came to help
        assertTrue(controller.moveFigure(new Position(2, 6), new Position(3, 6)));
        assertTrue(controller.moveFigure(new Position(3, 6), new Position(3, 5)));

        // Gold Camel can no go right
        assertTrue(controller.moveFigure(new Position(3, 4), new Position(4, 4)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());

        controller.changePlayer();
        // Silver Camel go down
        assertTrue(controller.moveFigure(new Position(4, 1), new Position(4, 2)));
        assertTrue(controller.moveFigure(new Position(4, 2), new Position(4, 3)));

        controller.changePlayer();
        // Gold Camel go left
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());

        controller.changePlayer();
        // Silver Elephant go down
        assertTrue(controller.moveFigure(new Position(3, 3), new Position(3, 4)));

        controller.changePlayer();
        // Gold Camel go left
        assertTrue(controller.moveFigure(new Position(5, 4), new Position(4, 4)));
        // Gold Camel can't go right
        assertFalse(controller.moveFigure(new Position(4, 4), new Position(4, 5)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
        // Gold Elephants come help
        assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
        // Gold Camel no can go right
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());
    }

    @Test
    void testIsPushed() {
        // Gold Elephant go up
        assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
        assertTrue(controller.moveFigure(new Position(4, 5), new Position(4, 4)));
        controller.changePlayer();
        // Silver Camel go down
        assertTrue(controller.moveFigure(new Position(4, 1), new Position(4, 2)));
        assertTrue(controller.moveFigure(new Position(4, 2), new Position(4, 3)));
        controller.changePlayer();

        // Silver Camel would get pushed two right
        assertFalse(controller.moveFigure(new Position(4, 3), new Position(6, 3)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

        // Silver Camel get pushed right
        assertTrue(controller.moveFigure(new Position(4, 3), new Position(5, 3)));
        assertEquals(GameStatus.PUSH_FIGURE, controller.getGameStatus());
        assertEquals(3, controller.getRemainingMoves());

        // no other figure can be moved, test on Gold Rabbit
        assertFalse(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertEquals(GameStatus.PUSH_FIGURE, controller.getGameStatus());

        // no player change possible
        controller.changePlayer();
        assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());

        // Gold Elephant goes up and finish push
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(4, 3)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());
        assertEquals(2, controller.getRemainingMoves());

        // change player now possible
        controller.changePlayer();
        assertEquals(PLAYER_NAME.SILVER, controller.getCurrentPlayerName());

        // Silver Rabbit goes up, other moves now possible
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());

        // -Test weaker figure Silver Camel will push Gold Elephant
        assertFalse(controller.moveFigure(new Position(4, 3), new Position(3, 3)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

    }

    @Test
    void testIsPushedRabbit() {
        // Gold Elephant go up
        assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
        assertTrue(controller.moveFigure(new Position(4, 5), new Position(4, 4)));
        controller.changePlayer();

        // Silver Rabbit go down
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertTrue(controller.moveFigure(new Position(0, 2), new Position(0, 3)));
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 4)));
        controller.changePlayer();

        // Gold Elephant go left
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(3, 4)));
        controller.changePlayer();

        // Silver Rabbit go right
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(1, 4)));
        assertTrue(controller.moveFigure(new Position(1, 4), new Position(2, 4)));
        controller.changePlayer();

        // Gold Elephant will push Silver Rabbit up
        assertFalse(controller.moveFigure(new Position(2, 4), new Position(2, 3)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

        // Gold Elephant push Silver Rabbit left
        assertTrue(controller.moveFigure(new Position(2, 4), new Position(1, 4)));
        assertEquals(GameStatus.PUSH_FIGURE, controller.getGameStatus());
        assertTrue(controller.moveFigure(new Position(3, 4), new Position(2, 4)));

        // Gold Elephant push Silver Rabbit down
        assertTrue(controller.moveFigure(new Position(1, 4), new Position(1, 5)));
        assertEquals(GameStatus.PUSH_FIGURE, controller.getGameStatus());
        assertTrue(controller.moveFigure(new Position(2, 4), new Position(1, 4)));
    }

    @Test
    void testIsPulled() {
        // Gold Elephant go up
        assertTrue(controller.moveFigure(new Position(4, 6), new Position(4, 5)));
        assertTrue(controller.moveFigure(new Position(4, 5), new Position(4, 4)));
        controller.changePlayer();
        // Silver Camel go down
        assertTrue(controller.moveFigure(new Position(4, 1), new Position(4, 2)));
        assertTrue(controller.moveFigure(new Position(4, 2), new Position(4, 3)));
        controller.changePlayer();

        // Gold Elephant go right
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
        // Gold Elephant would pull Silver camel left
        assertFalse(controller.moveFigure(new Position(4, 3), new Position(3, 3)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
        // Gold Elephant pull Silver camel down
        assertTrue(controller.moveFigure(new Position(4, 3), new Position(4, 4)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());
        assertEquals(2, controller.getRemainingMoves());

        // Gold Elephant go right
        assertTrue(controller.moveFigure(new Position(5, 4), new Position(6, 4)));
        // Gold Elephant pull Silver camel right
        assertTrue(controller.moveFigure(new Position(4, 4), new Position(5, 4)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());
        assertEquals(0, controller.getRemainingMoves());
    }

    @Test
    void testIsCaptured() {
        // -TRAP bottom left
        // move Gold Camel up
        assertTrue(controller.moveFigure(new Position(3, 6), new Position(3, 5)));
        // move Gold Cat into trap
        assertTrue(controller.moveFigure(new Position(2, 6), new Position(2, 5)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());
        // figure is in this trap
        assertEquals(FIGURE_NAME.C, controller.getFigureName(new Position(2, 5)));
        // move Gold Camel down
        assertTrue(controller.moveFigure(new Position(3, 5), new Position(3, 6)));
        assertEquals(GameStatus.CAPTURED, controller.getGameStatus());
        // no figure on this trap
        assertEquals(null, controller.getFigureName(new Position(2, 5)));

        // -TRAP bottom right
        // move Gold Cat into trap
        assertTrue(controller.moveFigure(new Position(5, 6), new Position(5, 5)));
        assertEquals(GameStatus.CAPTURED, controller.getGameStatus());
        // no figure on this trap
        assertEquals(null, controller.getFigureName(new Position(5, 5)));

        controller.changePlayer();

        // -TRAP top left
        // move Silver Cat into trap
        assertTrue(controller.moveFigure(new Position(2, 1), new Position(2, 2)));
        assertEquals(GameStatus.CAPTURED, controller.getGameStatus());
        // no figure on this trap
        assertEquals(null, controller.getFigureName(new Position(2, 2)));

        // -TRAP top right
        // move Silver Cat into trap
        assertTrue(controller.moveFigure(new Position(5, 1), new Position(5, 2)));
        assertEquals(GameStatus.CAPTURED, controller.getGameStatus());
        // no figure on this trap
        assertEquals(null, controller.getFigureName(new Position(5, 2)));

    }

    @Test
    void testToPositionPossibleMove() {
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertEquals(GameStatus.MOVE_FIGURE, controller.getGameStatus());

        // move a figure a grater distance than 1
        assertFalse(controller.moveFigure(new Position(0, 5), new Position(0, 2)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());

        // other player figure wants move and Figure is not pulled or pushed
        assertFalse(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertEquals(GameStatus.PRECONDITION_RULES_VIOLATED, controller.getGameStatus());
    }

    @Test
    void testGetWinnerEliminationGold() {
        // disable almost Gold Rabbits
        controller.disableFigure(new Position(0, 6));
        controller.disableFigure(new Position(0, 7));
        controller.disableFigure(new Position(1, 7));
        controller.disableFigure(new Position(5, 7));
        controller.disableFigure(new Position(6, 7));
        controller.disableFigure(new Position(7, 7));
        controller.disableFigure(new Position(7, 6));

        // disable Gold Cat
        controller.disableFigure(new Position(2, 6));

        // move last Rabbit to trap
        assertTrue(controller.moveFigure(new Position(2, 7), new Position(2, 6)));
        assertTrue(controller.moveFigure(new Position(2, 6), new Position(2, 5)));
        assertEquals(GameStatus.FINISH, controller.getGameStatus());
    }

    @Test
    void testGetWinnerEliminationSilver() {
        // disable almost Silver Rabbits
        controller.disableFigure(new Position(0, 0));
        controller.disableFigure(new Position(0, 1));
        controller.disableFigure(new Position(1, 0));
        controller.disableFigure(new Position(5, 0));
        controller.disableFigure(new Position(6, 0));
        controller.disableFigure(new Position(7, 0));
        controller.disableFigure(new Position(7, 1));

        // disable Silver Cat
        controller.disableFigure(new Position(2, 1));

        // move a gold figure
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        controller.changePlayer();

        // move last Rabbit to trap
        assertTrue(controller.moveFigure(new Position(2, 0), new Position(2, 1)));
        assertTrue(controller.moveFigure(new Position(2, 1), new Position(2, 2)));
        assertEquals(GameStatus.FINISH, controller.getGameStatus());
    }

    @Test
    void testGetWinnerGoalGold() {
        // move Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 3)));
        controller.changePlayer();

        // Disable Silver Figures
        controller.disableFigure(new Position(0, 0));
        controller.disableFigure(new Position(0, 1));
        controller.disableFigure(new Position(1, 1));

        // move Silver Rabbit
        assertTrue(controller.moveFigure(new Position(7, 1), new Position(7, 2)));
        controller.changePlayer();

        // move Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 2)));
        assertTrue(controller.moveFigure(new Position(0, 2), new Position(0, 1)));
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 0)));
        assertEquals(GameStatus.FINISH, controller.getGameStatus());

        // no move now able
        assertFalse(controller.moveFigure(new Position(7, 1), new Position(7, 2)));
        // no change player able
        controller.changePlayer();
        assertEquals(PLAYER_NAME.GOLD, controller.getCurrentPlayerName());
    }

    @Test
    void testGetWinnerGoalSilver() {
        // move Gold Rabbit
        assertTrue(controller.moveFigure(new Position(7, 6), new Position(7, 5)));
        controller.changePlayer();

        // move Silver Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertTrue(controller.moveFigure(new Position(0, 2), new Position(0, 3)));
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 4)));
        controller.changePlayer();

        // Disable Gold Figures
        controller.disableFigure(new Position(0, 6));
        controller.disableFigure(new Position(0, 7));
        controller.disableFigure(new Position(1, 6));

        // move Gold Rabbit
        assertTrue(controller.moveFigure(new Position(7, 5), new Position(7, 4)));
        controller.changePlayer();

        // move Silver Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 5)));
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 6)));
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 7)));
        assertEquals(GameStatus.FINISH, controller.getGameStatus());

        // no move now able
        assertFalse(controller.moveFigure(new Position(7, 6), new Position(7, 5)));
        // no change player able
        controller.changePlayer();
        assertEquals(PLAYER_NAME.SILVER, controller.getCurrentPlayerName());
    }

    @Test
    void testIsImmobileGold() {
        // disable some gold figures
        controller.disableFigure(new Position(1, 7));
        controller.disableFigure(new Position(2, 7));
        controller.disableFigure(new Position(3, 7));
        controller.disableFigure(new Position(4, 7));
        controller.disableFigure(new Position(5, 7));
        controller.disableFigure(new Position(6, 7));
        controller.disableFigure(new Position(0, 6));
        controller.disableFigure(new Position(1, 6));
        controller.disableFigure(new Position(2, 6));
        controller.disableFigure(new Position(3, 6));
        controller.disableFigure(new Position(4, 6));
        controller.disableFigure(new Position(5, 6));
        controller.disableFigure(new Position(6, 6));
        controller.disableFigure(new Position(7, 6));

        // move the gold rabbit left
        assertTrue(controller.moveFigure(new Position(7, 7), new Position(6, 7)));
        controller.changePlayer();

        // move Silver Rabbit down
        assertTrue(controller.moveFigure(new Position(0, 1), new Position(0, 2)));
        assertTrue(controller.moveFigure(new Position(0, 2), new Position(0, 3)));
        // move Silver Hours down
        assertTrue(controller.moveFigure(new Position(1, 1), new Position(1, 2)));
        assertTrue(controller.moveFigure(new Position(1, 2), new Position(1, 3)));
        controller.changePlayer();

        // move the gold rabbit left
        assertTrue(controller.moveFigure(new Position(6, 7), new Position(7, 7)));
        controller.changePlayer();

        // move Silver Rabbit down
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 4)));
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 5)));
        // move Silver Hours down
        assertTrue(controller.moveFigure(new Position(1, 3), new Position(1, 4)));
        assertTrue(controller.moveFigure(new Position(1, 4), new Position(1, 5)));
        controller.changePlayer();

        // move the gold rabbit left
        assertTrue(controller.moveFigure(new Position(7, 7), new Position(6, 7)));
        // disable gold rabbit
        controller.disableFigure(new Position(6, 7));
        controller.changePlayer();

        // move Silver Rabbit down
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 6)));
        // move Silver Hours down
        assertTrue(controller.moveFigure(new Position(1, 5), new Position(1, 6)));
        assertTrue(controller.moveFigure(new Position(1, 6), new Position(1, 7)));
        assertEquals(GameStatus.FINISH, controller.getGameStatus());
    }

    @Test
    void testIsImmobileSilver() {
        // disable some silver figures
        controller.disableFigure(new Position(1, 0));
        controller.disableFigure(new Position(2, 0));
        controller.disableFigure(new Position(3, 0));
        controller.disableFigure(new Position(4, 0));
        controller.disableFigure(new Position(5, 0));
        controller.disableFigure(new Position(6, 0));
        controller.disableFigure(new Position(0, 1));
        controller.disableFigure(new Position(1, 1));
        controller.disableFigure(new Position(2, 1));
        controller.disableFigure(new Position(3, 1));
        controller.disableFigure(new Position(4, 1));
        controller.disableFigure(new Position(5, 1));
        controller.disableFigure(new Position(6, 1));
        controller.disableFigure(new Position(7, 1));

        // move Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 6), new Position(0, 5)));
        assertTrue(controller.moveFigure(new Position(0, 5), new Position(0, 4)));
        // move Gold Hours up
        assertTrue(controller.moveFigure(new Position(1, 6), new Position(1, 5)));
        assertTrue(controller.moveFigure(new Position(1, 5), new Position(1, 4)));
        controller.changePlayer();

        // move the silver rabbit left
        assertTrue(controller.moveFigure(new Position(7, 0), new Position(6, 0)));
        controller.changePlayer();

        // move Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 4), new Position(0, 3)));
        assertTrue(controller.moveFigure(new Position(0, 3), new Position(0, 2)));
        // move Gold Hours up
        assertTrue(controller.moveFigure(new Position(1, 4), new Position(1, 3)));
        assertTrue(controller.moveFigure(new Position(1, 3), new Position(1, 2)));
        controller.changePlayer();

        // move the silver rabbit right
        assertTrue(controller.moveFigure(new Position(6, 0), new Position(7, 0)));
        // disable silver rabbit
        controller.disableFigure(new Position(7, 0));
        controller.changePlayer();

        // move Gold Rabbit up
        assertTrue(controller.moveFigure(new Position(0, 2), new Position(0, 1)));
        // move Gold Hours up
        assertTrue(controller.moveFigure(new Position(1, 2), new Position(1, 1)));
        assertTrue(controller.moveFigure(new Position(1, 1), new Position(1, 0)));
        controller.changePlayer();

        assertEquals(GameStatus.FINISH, controller.getGameStatus());
    }

    @Test
    void getPossibleMoves() {
        // Gold Rabbit surround Position
        List<Position> oughtGoldRabbitSurroundList = new ArrayList<>();
        oughtGoldRabbitSurroundList.add(new Position(0, 5));
        List<Position> isGoldRabbitSurroundList = controller.getPossibleMoves(new Position(0, 6));
        assertEquals(oughtGoldRabbitSurroundList, isGoldRabbitSurroundList);

        // Silver Rabbit surround Position
        List<Position> oughtSilverRabbitSurroundList = new ArrayList<>();
        List<Position> isSilverRabbitSurroundList = controller.getPossibleMoves(new Position(0, 1));
        assertEquals(oughtSilverRabbitSurroundList, isSilverRabbitSurroundList);
    }
}
