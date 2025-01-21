import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends ToDo {
    private final LocalDate by;

    Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTaskIcon() {
        return TaskIcon.DEADLINE.toString();
    }

    @Override
    public String toParsableString() {
        return String.format(
                "%s | %s",
                super.toParsableString(),
                this.by.format(DateTimeFormatter.ofPattern(DateFormat.PARSABLE.toString())));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern(DateFormat.READABLE.toString())));
    }
}
