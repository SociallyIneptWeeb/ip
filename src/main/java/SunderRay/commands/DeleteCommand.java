package SunderRay.commands;

import SunderRay.data.messages.InfoMsg;
import SunderRay.tasklist.TaskList;
import SunderRay.tasks.Task;

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
