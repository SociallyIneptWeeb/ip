package SunderRay.data.icons;

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
