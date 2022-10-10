import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class is Connect4NewGameView of the main game. The dialog to select the server, address, port
 *
 * @author project5 group
 * @version v1
 * @see Stage
 */
class Connect4NewGameView extends Stage {
    /**
     * group for Connect4NewGameView
     */
    private final ToggleGroup group;
    /**
     * field for Connect4NewGameView
     */
    private final TextField field;
    /**
     * toggleGroup for Connect4NewGameView
     */
    private final ToggleGroup toggleGroup;
    /**
     * textField for Connect4NewGameView
     */
    private final TextField textField;

    /**
     * The constructor of the Connect4NewGameView.
     */
    public Connect4NewGameView() {
        DialogPane dialogPane = new DialogPane();

        dialogPane.setMinHeight(125);
        dialogPane.setMinWidth(250);
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Network Setup ");

        toggleGroup = new ToggleGroup();
        RadioButton server = new RadioButton("Server ");
        server.setSelected(true);
        server.setMinWidth(75);
        server.setToggleGroup(toggleGroup);
        RadioButton client = new RadioButton("Client ");
        client.setMinWidth(75);
        client.setToggleGroup(toggleGroup);

        group = new ToggleGroup();
        RadioButton human = new RadioButton("Human");
        human.setSelected(true);
        human.setMinWidth(75);
        human.setToggleGroup(group);
        RadioButton ai = new RadioButton("Computer");
        ai.setMinWidth(85);
        ai.setToggleGroup(group);

        Text textCreate = new Text("\tCreate: ");
        Text textPlayAs = new Text("\tPlay as: ");
        Text serverText = new Text("\tServer ");
        Text port = new Text("Port");

        field = new TextField("localhost");
        field.setText("localhost");
        field.setMinWidth(90);
        textField = new TextField("4000");
        textField.setText("4000");
        textField.setMinWidth(90);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(textCreate, server, client);
        HBox boxForPlayer = new HBox(10);
        boxForPlayer.getChildren().addAll(textPlayAs, human, ai);
        HBox boxForServer = new HBox(10);
        boxForServer.getChildren().addAll(serverText, field, port, textField);
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, boxForPlayer, boxForServer);

        dialogPane.getChildren().addAll(vBox);
        alert.setDialogPane(dialogPane);

        ButtonType confirm = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().addAll(confirm, cancelButton);

        alert.showAndWait();
    }

    /**
     * the getter of the port
     *
     * @return the port
     */
    public int getPort() {
        try {
            return Integer.parseInt(textField.getText());
        } catch (Exception e) {
            return 4000;
        }

    }

    /**
     * the address of the address
     *
     * @return the address
     */
    public String getAddress() {
        try {
            return field.getText();
        } catch (Exception e) {
            return "localhost";
        }
    }

    /**
     * the human check
     *
     * @return is the human or ai
     */
    public boolean isHuman() {
        return this.group.getSelectedToggle().toString().contains("Human");
    }

    /**
     * the Server check
     *
     * @return is the server or client
     */
    public boolean playerType() {
        return !this.toggleGroup.getSelectedToggle().toString().contains("Server");
    }
}