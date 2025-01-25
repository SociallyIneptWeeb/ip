package sunderray.commands;

import sunderray.data.messages.InfoMsg;

/**
 * Returns the concluding message before the program ends.
 */
public class ExitCommand extends Command {
    @Override
    public String execute() {
        return InfoMsg.END;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
