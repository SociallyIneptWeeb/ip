public class Deadline extends ToDo {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
