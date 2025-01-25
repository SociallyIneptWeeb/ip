package sunderray.data.icons;

/**
 * Contains the readable and parsable icons for a task's status.
 */
public enum StatusIcon {
    DONE("X", "1"),
    NOT_DONE(" ", "0");

    private final String icon;
    private final String parsableIcon;

    StatusIcon(String icon, String parsableIcon) {
        this.icon = icon;
        this.parsableIcon = parsableIcon;
    }

    public String toParsableString() {
        return this.parsableIcon;
    }

    @Override
    public String toString() {
        return this.icon;
    }
}
