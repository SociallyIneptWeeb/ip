package sunderray.tasks;

import sunderray.data.icons.StatusIcon;

/**
 * Represents a task in the list.
 */
public abstract class Task {
    private final String description;
    private Boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return isDone ? StatusIcon.DONE.toString() : StatusIcon.NOT_DONE.toString();
    }

    private String getParsableStatusIcon() {
        return isDone ? StatusIcon.DONE.toParsableString() : StatusIcon.NOT_DONE.toParsableString();
    }

    protected abstract String getTaskIcon();

    public String toParsableString() {
        return String.format("%s | %s | %s", getTaskIcon(), getParsableStatusIcon(), description);
    }

    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskIcon(), getStatusIcon(), description);
    }
}
