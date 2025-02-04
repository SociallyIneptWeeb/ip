package sunderray.tasks;

import sunderray.data.icons.TaskIcon;

import java.time.Duration;

/**
 * A task that has a description and a duration that represents how long it takes to complete.
 */
public class Timed extends Task {
    private final Duration duration;

    public Timed(String description, Duration duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String getTaskIcon() {
        return TaskIcon.TIMED.toString();
    }

    @Override
    public String toParsableString() {
        return String.format(
                "%s | %s",
                super.toParsableString(),
                duration.toString());
    }

    @Override
    public String toString() {
        String formattedHours = "";
        int numHours = duration.toHoursPart();
        if (numHours > 0) {
            formattedHours = String.format("%d %s", numHours, numHours == 1 ? "hour": "hours");
        }

        String formattedMinutes = "";
        int numMinutes = duration.toMinutesPart();
        if (numMinutes > 0) {
            formattedMinutes = String.format("%d %s", numMinutes, numMinutes == 1 ? "minute": "minutes");
        }

        String formattedDuration = String.format("%s %s", formattedHours, formattedMinutes).trim();

        assert !formattedDuration.isBlank();

        return String.format("%s (needs: %s)", super.toString(), formattedDuration);
    }
}
