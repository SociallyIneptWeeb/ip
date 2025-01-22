package SunderRay.tasks;

import SunderRay.data.icons.StatusIcon;

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
        return (isDone ? StatusIcon.DONE.toString() : StatusIcon.NOT_DONE.toString());
    }

    private String getParsableStatusIcon() {
        return (isDone ? StatusIcon.DONE.toParsableString() : StatusIcon.NOT_DONE.toParsableString());
    }

    protected abstract String getTaskIcon();

    public String toParsableString() {
        return String.format("%s | %s | %s", this.getTaskIcon(), this.getParsableStatusIcon(), this.description);
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskIcon(), this.getStatusIcon(), this.description);
    }
}
