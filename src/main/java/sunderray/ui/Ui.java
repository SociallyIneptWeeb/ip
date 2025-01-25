package sunderray.ui;

import sunderray.data.messages.InfoMsg;
import sunderray.tasklist.TaskList;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Displays a line separating the user's and the bot's message.
     */
    public void showLine() {
        out.println(InfoMsg.DIVIDER);
    }

    /**
     * Displays the bot's message to the user.
     */
    public void showMessage(String message) {
        showLine();
        out.print("Ray: ");
        out.println(message);
        showLine();
    }

    /**
     * Displays an error message if it occurs upon initialization.
     */
    public void showInitErrorMessage(Exception e) {
        out.print("Error: ");
        out.println(e.getMessage());
    }

    /**
     * Displays the starting message upon initialization.
     */
    public void showWelcomeMessage(TaskList taskList) {
        showMessage(String.format("%s%n%s", InfoMsg.INTRO, taskList.toLoadedTasksDisplay()));
    }

    /**
     * Prompts for the user's command and reads the text entered by the users before returning it.
     */
    public String getUserInput() {
        out.print("You: ");
        return in.nextLine();
    }
}
