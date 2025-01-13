public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    protected String getTaskIcon() {
        return "T";
    }
}
