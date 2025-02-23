package sunderray.tasks;

import sunderray.data.icons.TaskIcon;

/**
 * An event that has a description and two strings that represent when it starts and ends.
 */
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
        return String.format("%s | %s | %s", super.toParsableString(), from, to);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from, to);
    }
}
