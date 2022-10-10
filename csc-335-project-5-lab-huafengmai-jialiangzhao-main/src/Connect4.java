import javafx.application.Application;

/**
 * The start main function of the connect 4 game. So we can run the program in this class.
 *
 * @author project5 group
 * @version v1
 * @see Application
 */
public class Connect4 {


    /**
     * the start main function, start the connect 4 game.
     *
     * @param args the main function input args.
     */
    public static void main(String[] args) {
        /* the start main function, start the connect 4 game */
        Application.launch(Connect4View.class, args);
    }
}
