package sunderray.commands;

import sunderray.data.messages.InfoMsg;
import sunderray.tasklist.TaskList;
import sunderray.tasks.Task;

/**
 * Deletes a task identified using its index in the list.
 */
public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int taskId;

    public DeleteCommand(TaskList taskList, int taskId) {
        this.taskList = taskList;
        this.taskId = taskId;
    }

    @Override
    public String execute() {
        Task task = taskList.deleteTask(taskId);
        return String.format(
                "%s%n\t%s%n%s",
                InfoMsg.DELETE_TASK,
                task,
                taskList.toNumTasksDisplay());
    }
}
