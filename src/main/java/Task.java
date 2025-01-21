public abstract class Task {
    private final String description;
    private Boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? StatusIcon.DONE.toString() : StatusIcon.NOT_DONE.toString());
    }

    protected abstract String getTaskIcon();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskIcon(), this.getStatusIcon(), this.description);
    }
}
