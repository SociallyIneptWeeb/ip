public class Event extends ToDo {
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from, to);
    }
}
