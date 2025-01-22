package SunderRay.commands;

import SunderRay.data.messages.InfoMsg;

public class ExitCommand extends Command {
    @Override
    public String execute() {
        return InfoMsg.END;
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
