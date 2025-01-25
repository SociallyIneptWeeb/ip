package sunderray.commands;

/**
 * Returns a meaningful error message to the user.
 */
public class InvalidCommand extends Command {
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        return message;
    }
}
