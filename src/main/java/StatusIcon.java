public enum StatusIcon {
    DONE("X"),
    NOT_DONE(" ");

    private final String icon;

    StatusIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return this.icon;
    }
}
