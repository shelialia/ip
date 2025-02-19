package rose.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rose.Rose;
import rose.exceptions.RoseException;

/**
 * Controller for the main GUI window of the application.
 * This class handles user interactions and updates the UI accordingly.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Rose rose;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the UI by binding the scroll pane's vertical value
     * to the height property of the dialog container.
     * This ensures that new messages are always visible.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@code Rose} instance into the controller.
     *
     * @param r The instance of {@code Rose} used for handling user input.
     */
    public void setRose(Rose r) {
        rose = r;
    }

    /**
     * Handles user input by creating two dialog boxes:
     * one for the user's input and another for Rose's response.
     * The response is retrieved from the {@code Rose} instance.
     * After processing, the user input field is cleared.
     *
     * @throws RoseException If an error occurs while processing the input.
     */
    @FXML
    private void handleUserInput() throws RoseException {
        String input = userInput.getText();
        try {
            String response = rose.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (Exception e) {
            throw new RoseException(e.getMessage());
        }
    }
}