package SunderRay.ui;

import SunderRay.data.messages.InfoMsg;
import SunderRay.tasklist.TaskList;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showLine() {
        out.println(InfoMsg.DIVIDER);
    }

    public void showMessage(String message) {
        showLine();
        out.print("Ray: ");
        out.println(message);
        showLine();
    }

    public void showInitErrorMessage(Exception e) {
        out.print("Error: ");
        out.println(e.getMessage());
    }

    public void showWelcomeMessage(TaskList taskList) {
        showMessage(String.format("%s%n%s", InfoMsg.INTRO, taskList.toLoadedTasksDisplay()));
    }

    public String getUserInput() {
        out.print("You: ");
        return in.nextLine();
    }
}
