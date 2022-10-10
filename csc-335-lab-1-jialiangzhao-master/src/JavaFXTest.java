import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is a simple class to test that JavaFX works on your computer.
 * JDK 8 has JavaFX as part of its install, but JDK 11+ do not.
 * If you click the green run triangle button above and this does not
 * compile and run, then you'll need to install JDK 8 as the lab
 * document and readme files indicate. * 
 * 
 * @author Jonathan Misurda
 *
 */
public class JavaFXTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		Label label = new Label("Seeing this window means JavaFX is installed correctly.");
		pane.setTop(label);
		Button btnClickMe = new Button("Click Me");
		pane.setCenter(btnClickMe);
		btnClickMe.setOnAction((event) -> { 
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setTitle("JavaFX Works!");
			a.setContentText("JavaFX appears to be properly installed");
			a.setHeaderText("JavaFX Test");
			a.showAndWait();
		});
		
		Scene scene = new Scene(pane, 300, 200);
		stage.setScene(scene);
		stage.setTitle("JavaFX Test");
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
