import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * This class is Connect4View of the main game.
 *
 * @author project5 group
 * @version v1
 * @see Application
 * @see Observer
 */
public class Connect4View extends Application implements Observer {
    /**
     * BOARD_RADIUS for Connect4View
     */
    public static final int BOARD_RADIUS = 8;
    /**
     * ITEM_RADIUS for Connect4View
     */
    public static final int ITEM_RADIUS = 20;
    /**
     * menuBar for Connect4View
     */
    private MenuBar menuBar;
    /**
     * connect4NewGameView for Connect4View
     */
    private Connect4NewGameView connect4NewGameView;
    /**
     * gridPane for Connect4View
     */
    private GridPane gridPane;
    /**
     * connect4Controller for Connect4View
     */
    private Connect4Controller connect4Controller;
    /**
     * vBox for Connect4View
     */
    private VBox vBox;


    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set. The primary stage will be embedded in
     *              the browser if the application was launched as an applet.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages and will not be embedded in the browser.
     */
    public void start(Stage stage) {
        Connect4Model connect4Model = new Connect4Model();
        connect4Controller = new Connect4Controller(connect4Model);
        connect4Model.addObserver(this);
        stage.setTitle("Connect 4 ");
        vBox = new VBox();
        menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem menuItem = new MenuItem("New Game");

        menuItem.setOnAction((event) -> {
            connect4NewGameView = new Connect4NewGameView();
            this.newConnect4();
        });

        menu.getItems().add(menuItem);
        menuBar.getMenus().add(menu);
        this.boardCreated(connect4Controller.getModelBoard());
        Scene scene = new Scene(vBox, 344, 321);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * create a new game board to the game.
     *
     * @param grid the input grid
     */
    private void boardCreated(ArrayList<ArrayList<Integer>> grid) {

        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color:#0000ff");
        gridPane.setHgap(BOARD_RADIUS);
        gridPane.setVgap(BOARD_RADIUS);
        gridPane.setPadding(new Insets(BOARD_RADIUS, BOARD_RADIUS, BOARD_RADIUS, BOARD_RADIUS));

        int i = 0;
        while (i < 6) {
            for (int j = 0; j < 7; j++) {
                Circle cir = new Circle(ITEM_RADIUS);
                cir.setFill(getColor(grid.get(i).get(j)));
                gridPane.add(cir, j, i, 1, 1);
                gridPane.setOnMouseClicked((event) -> defineConnect4Location(event.getX()));
            }
            i++;
        }

        vBox.getChildren().clear();
        vBox.getChildren().addAll(menuBar, gridPane);
    }

    /**
     * get the game color of the grid
     *
     * @param i the input i
     * @return the color flag
     */
    private Color getColor(int i) {
        switch (i) {
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.RED;
            default:
                return Color.WHITE;
        }

    }

    /**
     * the connection location to the game.
     *
     * @param loc the mouse click location.
     */
    private void defineConnect4Location(double loc) {
        int col;
        if (loc < 52) col = 0;
        else if (loc >= 52 && loc < 100) col = 1;
        else if (loc >= 100 && loc < 148) col = 2;
        else if (loc >= 148 && loc < 196) col = 3;
        else if (loc >= 196 && loc < 244) col = 4;
        else if (loc >= 244 && loc < 292) col = 5;
        else col = 6;
        if (!connect4Controller.isPlayerCheck() || !connect4Controller.getNextPlayer()) {
            gridPane.setOnMouseClicked((event) -> {
            });
        } else if (connect4Controller.validMove(col)) {
            connect4Controller.humanTurn(col);
            connect4Controller.setNextPlayer(false);
            int controllerWinner = connect4Controller.isWinner();
            if (controllerWinner != 0) {
                gridPane.setOnMouseClicked((event) -> {
                });
                Alert winAlert = new Alert(AlertType.INFORMATION);
                winAlert.setHeaderText("Message ");
                winAlert.setContentText("You won! ");
                winAlert.showAndWait();
            }
        } else {
            Alert illegalMove = new Alert(AlertType.ERROR);
            illegalMove.setHeaderText("Error");
            illegalMove.setContentText("Column full, pick somewhere else!");
            illegalMove.showAndWait();
        }
    }

    /**
     * if there is a winner, open a alert to display the related information.
     */
    public void displayWinner() {
        Alert winAlert = new Alert(AlertType.INFORMATION);
        winAlert.setHeaderText("Message");
        winAlert.setContentText("You won!");
        winAlert.showAndWait();
    }

    /**
     * if there is a Loser, open a alert to display the related information.
     */
    public void displayLoser() {
        Alert winAlert = new Alert(AlertType.INFORMATION);
        winAlert.setHeaderText("Message");
        winAlert.setContentText("You lost! ");
        winAlert.showAndWait();
    }

    /**
     * the method to create the socket to create a new game.s
     */
    private void newConnect4() {
        Connect4Model connect4Model = new Connect4Model();
        this.connect4Controller.setConnect4Model(connect4Model);
        this.connect4Controller.setConnect4View(this);
        connect4Model.addObserver(this);
        this.boardCreated(connect4Model.getBoardList());
        Socket socket = null;
        int play = Connect4MoveMessage.YELLOW;
        if (!this.connect4NewGameView.playerType()) {
            try {
                ServerSocket serverSocket = new ServerSocket(this.connect4NewGameView.getPort());
                socket = serverSocket.accept();
                serverSocket.close();
                connect4Controller.setNextPlayer(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            play = Connect4MoveMessage.RED;
            try {
                socket = new Socket(this.connect4NewGameView.getAddress(), this.connect4NewGameView.getPort());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        connect4Model.setPlay(play);
        connect4Controller.setPlayByAiOrHuman(this.connect4NewGameView.isHuman());
        connect4Controller.messageSocketRunner(socket);
        if (this.connect4NewGameView.playerType()) this.connect4Controller.nextPlayer();
        else if (!this.connect4NewGameView.isHuman() && !this.connect4NewGameView.playerType())
            this.connect4Controller.computerTurn();
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    public void update(Observable o, Object arg) {
        ArrayList<ArrayList<Integer>> grid = (ArrayList<ArrayList<Integer>>) arg;
        this.boardCreated(grid);
    }

}
