package sunderray.commands;

import sunderray.data.messages.InfoMsg;
import sunderray.tasks.TaskList;
import sunderray.tasks.Task;

/**
 * Adds a task to the list.
 */
public class AddCommand extends Command {
    private final TaskList taskList;
    private final Task task;

    public AddCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    @Override
    public String execute() {
        assert task != null;
        assert taskList != null;

        taskList.addTask(task);
        return String.format(
                "%s%n\t%s%n%s",
                InfoMsg.ADDED_TASK,
                task,
                taskList.toNumTasksDisplay());
    }
}
