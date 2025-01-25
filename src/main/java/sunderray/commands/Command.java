package sunderray.commands;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command and returns the message to display to the user.
     */
    public abstract String execute();
}
