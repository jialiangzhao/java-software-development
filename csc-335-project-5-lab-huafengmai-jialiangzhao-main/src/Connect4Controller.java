import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;

/**
 * This class is made as the controller of the connect 4 game. And there are the human and ai player in the game.
 *
 * @author project5 group
 * @version v1
 * @see Application
 */
public class Connect4Controller {
    /**
     * the bound range of the baord in the connect 4 game.
     */
    public static final int BOUND = 6;
    /**
     * the Connect4Model of the connect 4 game
     */
    private Connect4Model connect4Model;
    /**
     * the connect4View of the connect 4 game
     */
    private Connect4View connect4View;
    /**
     * the ObjectInputStream of the connect 4 game
     */
    private ObjectInputStream objectInputStream;
    /**
     * the ObjectOutputStream of the connect 4 game
     */
    private ObjectOutputStream objectOutputStream;
    /**
     * the Connect4MoveMessage of the connect 4 game
     */
    private Connect4MoveMessage connect4MoveMessage;
    /**
     * the Connect4Controller of the connect 4 game
     */
    private final Connect4Controller connect4Controller = this;
    /**
     * the playerCheck of the connect 4 game
     */
    private boolean playerCheck = true;
    /**
     * the nextPlayer of the connect 4 game
     */
    private boolean nextPlayer = false;

    /**
     * The constructor of the Connect4Controller
     *
     * @param connect4Model the given connect4Model
     */
    public Connect4Controller(Connect4Model connect4Model) {
        this.connect4Model = connect4Model;
    }

    /**
     * receive the data from socket message.
     *
     * @param socket the input socket
     */
    public void messageSocketRunner(Socket socket) {
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * use the thread to decide the operation of next player.
     */
    public void nextPlayer() {

        Thread nextPlayerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                connect4MoveMessage = null;
                try {
                    connect4MoveMessage = (Connect4MoveMessage) objectInputStream.readObject();
                    // use the  Platform.runLater to solver the problem:
                    // When you finally receive the other player’s Connect4MoveMessage, you’ll need to negate the boolean
                    // and update the UI. You can’t do these things from the thread you spawned, only from the main thread,
                    // or it will not work properly.
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            connect4Model.moveTo(connect4MoveMessage.getColumn(), connect4MoveMessage.getColor());
                            if (connect4Model.isWinner() == 0) {
                                if (!connect4Controller.playerCheck) {
                                    connect4Controller.computerTurn();
                                }
                            } else {
                                connect4View.displayLoser();
                                connect4Controller.setNextPlayer(false);
                            }
                        }
                    });
                } catch (Exception e) {
//                    e.printStackTrace();
                }
                connect4Controller.setNextPlayer(true);
            }
        });
        nextPlayerThread.start();
    }

    /**
     * the human turn operation, just for the manual click runner.
     *
     * @param col input column value
     */
    public void humanTurn(int col) {
        this.connect4Model.moveTo(col);
        try {
            this.objectOutputStream.writeObject(this.connect4Model.getMessage(col));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.nextPlayer();
    }


    /**
     * the computer turn operation, just for the manual click runner.
     */
    public void computerTurn() {
        Random random = new Random();
        int input = random.nextInt(BOUND);
        while (!this.validMove(input)) {
            input = random.nextInt(BOUND);
        }
        this.connect4Model.moveTo(input);
        try {
            this.objectOutputStream.writeObject(this.connect4Model.getMessage(input));
            if (this.connect4Model.isWinner() != 0) this.connect4View.displayWinner();
            this.nextPlayer = false;
            this.nextPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * switch the player.
     *
     * @param playerCheck the input player define.
     */
    public void setPlayByAiOrHuman(boolean playerCheck) {
        this.playerCheck = playerCheck;
    }

    /**
     * @return the board of connect4Model.
     */
    public ArrayList<ArrayList<Integer>> getModelBoard() {
        return connect4Model.getBoardList();
    }

    /**
     * check the input column is valid or not
     *
     * @param col the check column
     * @return the valid field of the column
     */
    public boolean validMove(int col) {
        return connect4Model.validMove(col);
    }

    /**
     * check whether win or not
     *
     * @return the winner
     */
    public int isWinner() {
        return connect4Model.isWinner();
    }

    /**
     * setter of the Connect4Model
     *
     * @param connect4Model the input Connect4Model to set to the Connect4Model
     */
    public void setConnect4Model(Connect4Model connect4Model) {
        this.connect4Model = connect4Model;
    }

    /**
     * getter of next player
     *
     * @return the next player
     */
    public boolean getNextPlayer() {
        return this.nextPlayer;
    }

    /**
     * setter of the Connect4View
     *
     * @param connect4View the input connect4View to set to the Connect4View
     */
    public void setConnect4View(Connect4View connect4View) {
        this.connect4View = connect4View;
    }

    /**
     * setter of the nextPlayer
     *
     * @param nextPlayer the input nextPlayer to set to the nextPlayer
     */
    public void setNextPlayer(boolean nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    /**
     * getter of the playerCheck
     */
    public boolean isPlayerCheck() {
        return this.playerCheck;
    }

}
