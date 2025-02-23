package sunderray.commands;

import sunderray.data.messages.InfoMsg;
import sunderray.tasks.TaskList;

/**
 * Finds tasks whose description matches the keyword.
 */
public class FindCommand extends Command {
    private final TaskList taskList;
    private final String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        assert taskList != null;
        assert keyword != null && !keyword.isBlank();

        String result = taskList.toMatchedTasksDisplay(keyword);
        if (result.isBlank()) {
            return InfoMsg.NO_TASKS_MATCHED;
        }

        return String.format("%s%n%s", InfoMsg.LIST_MATCHED_TASKS, result);
    }
}
