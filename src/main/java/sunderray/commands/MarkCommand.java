package sunderray.commands;

import sunderray.data.messages.InfoMsg;
import sunderray.tasklist.TaskList;
import sunderray.tasks.Task;

public class MarkCommand extends Command {
    private final TaskList taskList;
    private final int taskId;
    private final boolean isDone;

    public MarkCommand(TaskList taskList, int taskId, boolean isDone) {
        this.taskList = taskList;
        this.taskId = taskId;
        this.isDone = isDone;
    }

    @Override
    public String execute() {
        Task task = taskList.markTask(taskId, isDone);
        return String.format(
                "%s%n\t%s",
                String.format(InfoMsg.MARK_TASK, isDone ? "mark" : "unmark"),
                task);
    }
}
