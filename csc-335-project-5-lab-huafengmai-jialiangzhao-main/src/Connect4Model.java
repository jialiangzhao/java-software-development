import javafx.application.Application;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.IntStream;

/**
 * This class is made as the model of the connect 4 game. And there are the human and ai player in the game,
 * which decide the input location of the game,
 *
 * @author project5 group
 * @version v1
 * @see Application
 */
public class Connect4Model extends Observable {
    /**
     * the board list of the connect 4 game
     */
    private final ArrayList<ArrayList<Integer>> boardList;
    /**
     * the player which is setting the board
     */
    private int play;

    /**
     * the constructor of the Connect4Model class.
     */
    public Connect4Model() {
        boardList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                integers.add(0);
            }
            boardList.add(integers);
        }
    }

    /**
     * the setter of the player.
     *
     * @param play the given player.
     */
    public void setPlay(int play) {
        this.play = play;
    }

    /**
     * the getter of the board list
     *
     * @return the target board list
     */
    public ArrayList<ArrayList<Integer>> getBoardList() {
        return boardList;
    }

    /**
     * the message from the controller.
     *
     * @param col the input message
     * @return the next connect4 move message
     */
    public Connect4MoveMessage getMessage(int col) {
        return new Connect4MoveMessage(0, col, this.play);
    }

    /**
     * move to next location
     *
     * @param loc  the input place
     * @param play the setting player
     */
    public void moveTo(int loc, int play) {
        int i = 5;
        while (i >= 0) {
            if (boardList.get(i).get(loc) == 0) {
                boardList.get(i).set(loc, play);
                break;
            }
            i--;
        }
        setChanged();
        notifyObservers(boardList);
    }

    /**
     * the move to over write method
     *
     * @param place the input place
     */
    public void moveTo(int place) {
        this.moveTo(place, play);
    }

    /**
     * check is it a valid move
     *
     * @param col the input move loc
     * @return is valid
     */
    public boolean validMove(int col) {
        return boardList.get(0).get(col) == 0;
    }

    /**
     * is the board is full
     *
     * @return is full
     */
    public boolean isFullCheck() {
        return isBoardFilled() || isWinner() != 0;
    }

    /**
     * is the board is isBoardFilled
     *
     * @return is isBoardFilled
     */
    public boolean isBoardFilled() {
        return IntStream.range(0, 7).noneMatch(i -> boardList.get(0).get(i) == 0);
    }

    /**
     * is the game over
     *
     * @return the winner
     */
    public int isWinner() {
        for (int row = 0; row < boardList.size(); row++) {
            for (int col = 0; col < boardList.get(row).size(); col++) {
                int currentPlay = boardList.get(row).get(col);
                if (currentPlay == 0) {
                    continue;
                }

                if (col + 3 < boardList.get(row).size() && currentPlay == boardList.get(row).get(col + 1) && currentPlay == boardList.get(row).get(col + 2) && currentPlay == boardList.get(row).get(col + 3)) {
                    return currentPlay;
                }
                if (row + 3 < boardList.size()) {
                    if (currentPlay == boardList.get(row + 1).get(col) && currentPlay == boardList.get(row + 2).get(col) && currentPlay == boardList.get(row + 3).get(col)) {
                        return currentPlay;
                    }
                    if (col + 3 < boardList.get(row).size() && currentPlay == boardList.get(row + 1).get(col + 1) && currentPlay == boardList.get(row + 2).get(col + 2) && currentPlay == boardList.get(row + 3).get(col + 3)) {
                        return currentPlay;
                    }
                    if (col - 3 >= 0 && currentPlay == boardList.get(row + 1).get(col - 1) && currentPlay == boardList.get(row + 2).get(col - 2) && currentPlay == boardList.get(row + 3).get(col - 3)) {
                        return currentPlay;
                    }
                }
            }
        }
        return 0;
    }

}
