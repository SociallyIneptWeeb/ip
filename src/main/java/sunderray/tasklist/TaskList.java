package sunderray.tasklist;

import sunderray.data.messages.InfoMsg;
import sunderray.tasks.Task;

import java.util.ArrayList;

/**
 * Contains all the user's tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given data.
     *
     * @param tasks the tasks to be initialized.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Marks a task as done or not done using its index in the list.
     */
    public Task markTask(int taskId, boolean isDone) {
        Task task = tasks.get(taskId);
        task.setIsDone(isDone);
        return task;
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list using its index.
     */
    public Task deleteTask(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return task;
    }

    /**
     * Returns a formatted string which lists all tasks.
     */
    public String toListDisplay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumTasks(); i++) {
            sb.append(String.format("%d.\t%s%n", i + 1, tasks.get(i)));
        }

        return sb.toString().trim();
    }

    /**
     * Returns a list of formatted tasks whose description contains the keyword.
     */
    public String toMatchedTasksDisplay(String keyword) {
        StringBuilder sb = new StringBuilder();
        int numMatched = 0;
        for (int i = 0; i < getNumTasks(); i++) {
            Task task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                sb.append(String.format("%d.\t%s%n", numMatched++ + 1, tasks.get(i)));
            }
        }

        return sb.toString().trim();
    }

    public String toLoadedTasksDisplay() {
        return String.format(InfoMsg.LOAD_DATA_FILE, getNumTasks(), getNumTasks() == 1 ? "task" : "tasks");
    }

    /**
     * Returns a formatted string which shows how many tasks are currently in the list.
     */
    public String toNumTasksDisplay() {
        return String.format(InfoMsg.NUM_TASKS, getNumTasks(), getNumTasks() == 1 ? "task" : "tasks");
    }

    /**
     * Returns an array of strings to be written to the data file for saving of tasks.
     */
    public String[] toParsableLines() {
        int numTasks = getNumTasks();
        String[] lines = new String[numTasks];
        for (int i = 0; i < numTasks; i++) {
            lines[i] = tasks.get(i).toParsableString();
        }

        return lines;
    }
}
