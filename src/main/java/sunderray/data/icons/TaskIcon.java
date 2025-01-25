package sunderray.data.icons;

/**
 * Contains the readable and parsable icons for a task's type.
 */
public enum TaskIcon {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String type;

    TaskIcon(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
