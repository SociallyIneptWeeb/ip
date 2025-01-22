public class InvalidCommand extends Command {
    private final String message;

    InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        return message;
    }
}
