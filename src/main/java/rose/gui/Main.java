package rose.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rose.Rose;

/**
 * The main entry point for the GUI application using JavaFX.
 * This class initializes and launches the graphical user interface for Rose.
 */
public class Main extends Application {

    private final Rose rose = new Rose("data/Rose.txt");

    /**
     * Starts the GUI application by loading the FXML layout and setting up the primary stage.
     *
     * @param stage The primary stage for the application window.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the Rose instance into the controller
            fxmlLoader.<MainWindow>getController().setRose(rose);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
