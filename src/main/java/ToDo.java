public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    protected String getTaskIcon() {
        return TaskIcon.TODO.toString();
    }
}
