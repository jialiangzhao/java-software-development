import java.io.Serializable;

/**
 * This class is Connect4MoveMessage of the main game. Contains the player color.
 *
 * @author project5 group
 * @version v1
 * @see Serializable
 */
public class Connect4MoveMessage implements Serializable {

    /**
     * the server player
     */
    public static int YELLOW = 1;
    /* *the client player */
    public static int RED = 2;


    private static final long serialVersionUID = 1L;
    /**
     * row of move message
     */
    private int row;
    /**
     * column of move message
     */
    private int col;
    /**
     * color of the current player
     */
    private int color;

    /**
     * the constructor of the class
     *
     * @param row   the input row
     * @param col   the input col
     * @param color the input color
     */
    public Connect4MoveMessage(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    /**
     * the getter of the row
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * the getter of the col
     *
     * @return col
     */
    public int getColumn() {
        return col;
    }

    /**
     * the getter of the color
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

}