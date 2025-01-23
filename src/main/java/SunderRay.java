import sunderray.commands.Command;
import sunderray.commands.ExitCommand;
import sunderray.parser.Parser;
import sunderray.storage.Storage;
import sunderray.tasklist.TaskList;
import sunderray.ui.Ui;

import java.io.IOException;

public class SunderRay {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

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

    private void run() {
        init();
        commandLoop();
        exit();
    }

    public static void main(String[] args) {
        new SunderRay().run();
    }
}
