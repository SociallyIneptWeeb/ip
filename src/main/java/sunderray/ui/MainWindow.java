package sunderray.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import sunderray.commands.Command;
import sunderray.commands.ExitCommand;
import sunderray.data.messages.InfoMsg;
import sunderray.parser.Parser;
import sunderray.storage.Storage;
import sunderray.tasklist.TaskList;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the main GUI.
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

    private TaskList taskList;
    private Storage storage;
    private final Parser parser = new Parser();

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.png")));
    private final Image sunderRayImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/SunderRay.png")));

    /**
     * Sets up the required objects, loads up any saved tasks from the data file, and displays the welcome message.
     */
    @FXML
    private void initialize() {
        try {
            storage = new Storage();
            taskList = storage.load();
        } catch (IOException | Storage.ParseTaskException | Storage.CreateStorageFileException e) {
            throw new RuntimeException(e);
        }

        String welcomeMessage = String.format("%s%n%s", InfoMsg.INTRO, taskList.toLoadedTasksDisplay());
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSunderRayDialog(welcomeMessage, sunderRayImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing SunderRay's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response;
        Command command;

        try {
            command = parser.parse(taskList, input);
            response = command.execute();
            storage.store(taskList.toParsableLines());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSunderRayDialog(response, sunderRayImage)
        );
        userInput.clear();

        if (ExitCommand.isExit(command)) {
            Platform.exit();
        }
    }
}
