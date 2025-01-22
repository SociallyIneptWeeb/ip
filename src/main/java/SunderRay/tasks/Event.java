package SunderRay.tasks;

import SunderRay.data.icons.TaskIcon;

public class Event extends ToDo {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTaskIcon() {
        return TaskIcon.EVENT.toString();
    }

    @Override
    public String toParsableString() {
        return String.format("%s | %s | %s", super.toParsableString(), this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
