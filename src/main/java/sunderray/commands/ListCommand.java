package sunderray.commands;

import sunderray.data.messages.InfoMsg;
import sunderray.tasklist.TaskList;

/**
 * Lists all tasks or informs if there are none.
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        if (taskList.getNumTasks() == 0) {
            return InfoMsg.NO_TASKS;
        }

        return String.format("%s%n%s", InfoMsg.LIST_TASKS, taskList.toListDisplay());
    }
}
