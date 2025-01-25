import sunderray.commands.Command;
import sunderray.commands.ExitCommand;
import sunderray.parser.Parser;
import sunderray.storage.Storage;
import sunderray.tasklist.TaskList;
import sunderray.ui.Ui;

import java.io.IOException;

/**
 * Entry point of the application.
 * Initializes the application and starts the interaction with the user.
 */
public class SunderRay {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Sets up the required objects, loads up any saved tasks from the data file, and prints the welcome message.
     */
    private void init() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskList = storage.load();
        } catch (IOException | Storage.ParseTaskException| Storage.CreateStorageFileException e) {
            ui.showInitErrorMessage(e);
            throw new RuntimeException(e);
        }

        ui.showWelcomeMessage(taskList);
    }

    private void exit() {
        System.exit(0);
    }

    /**
     * Executes the command, saves tasks into the data file and returns the result.
     *
     * @param command user command
     * @return response to show to the user
     */
    private String executeCommand(Command command) {
        try {
            String response = command.execute();
            storage.store(taskList.toParsableLines());
            return response;
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the user's input and executes commands until the user enters the exit command.
     */
    private void commandLoop() {
        Command command;
        Parser parser = new Parser();
        do {
            String userInput = ui.getUserInput();
            command = parser.parse(taskList, userInput);
            String result = executeCommand(command);
            ui.showMessage(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        init();
        commandLoop();
        exit();
    }

    public static void main(String[] args) {
        new SunderRay().run();
    }
}
