import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Connect4Tests {

    private Connect4Model connect4Model;

    @Test
    public void testWinner1() {
        connect4Model = new Connect4Model();
        connect4Model.moveTo(1, 0);
        connect4Model.moveTo(1, 1);
        for (int i = 0; i < 3; i++) {
            connect4Model.moveTo(1, 1);
        }
        assertEquals(connect4Model.isWinner(), 1);
    }

    @Test
    public void testWinner2() {
        connect4Model = new Connect4Model();
        connect4Model.moveTo(2, 0);
        for (int i = 0; i < 4; i++) {
            connect4Model.moveTo(2, 0);
        }
        assertEquals(connect4Model.isWinner(), 0);
    }

    @Test
    public void testNoWinner1() {
        connect4Model = new Connect4Model();
        connect4Model.moveTo(1, 0);
        for (int i = 2; i <= 6; i += 2) {
            connect4Model.moveTo(i, 0);
        }
        connect4Model.moveTo(2, 0);
        assertEquals(connect4Model.isWinner(), 0);
    }

    @Test
    void testMess() {
        Connect4Model connect4Model = new Connect4Model();
        Connect4MoveMessage message = connect4Model.getMessage(1);
        assertEquals(message.getColumn(), 1);
    }

    @Test
    void testFill() {
        Connect4Model connect4Model = new Connect4Model();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                connect4Model.moveTo(j, 1);
            }
        }
        connect4Model.validMove(1);
        connect4Model.isBoardFilled();
        assertTrue(connect4Model.isFullCheck());
    }

    @Test
    void testMove() {
        Connect4MoveMessage connect4MoveMessage = new Connect4MoveMessage(0, 0, 0);
        assertEquals(connect4MoveMessage.getColumn(), 0);
    }

    @Test
    void testSetter() {
        Connect4Model connect4Model = new Connect4Model();
        Connect4Controller connect4Controller = new Connect4Controller(connect4Model);
        connect4Controller.setNextPlayer(true);
        connect4Controller.getModelBoard();
        connect4Controller.validMove(1);
        connect4Controller.isWinner();
        connect4Controller.setConnect4Model(new Connect4Model());
        connect4Controller.nextPlayer();
        connect4Controller.setConnect4View(new Connect4View());
        connect4Controller.setNextPlayer(true);
        assertTrue(connect4Controller.getNextPlayer());
    }

}
