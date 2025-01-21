public enum DateFormat {
    READABLE("MMM dd yyyy"),
    PARSABLE("yyyy-MM-dd");

    private final String format;

    DateFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return this.format;
    }
}
