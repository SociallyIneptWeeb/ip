public class Deadline extends ToDo {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTaskIcon() {
        return TaskIcon.DEADLINE.toString();
    }

    @Override
    public String toParsableString() {
        return String.format("%s | %s", super.toParsableString(), this.by);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.by);
    }
}
