package sunderray.tasks;

import sunderray.data.icons.TaskIcon;

/**
 * A task that simply has a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    protected String getTaskIcon() {
        return TaskIcon.TODO.toString();
    }
}
