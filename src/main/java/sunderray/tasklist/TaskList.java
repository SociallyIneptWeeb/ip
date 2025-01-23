package sunderray.tasklist;

import sunderray.data.messages.InfoMsg;
import sunderray.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task markTask(int taskId, boolean isDone) {
        Task task = tasks.get(taskId);
        task.setIsDone(isDone);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return task;
    }

    public String toListDisplay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumTasks(); i++) {
            sb.append(String.format("%d.\t%s%n", i + 1, tasks.get(i)));
        }

        return sb.toString().trim();
    }

    public String toLoadedTasksDisplay() {
        return String.format(InfoMsg.LOAD_DATA_FILE, getNumTasks(), getNumTasks() == 1 ? "task" : "tasks");
    }

    public String toNumTasksDisplay() {
        return String.format(InfoMsg.NUM_TASKS, getNumTasks(), getNumTasks() == 1 ? "task" : "tasks");
    }

    public String[] toParsableLines() {
        int numTasks = getNumTasks();
        String[] lines = new String[numTasks];
        for (int i = 0; i < numTasks; i++) {
            lines[i] = tasks.get(i).toParsableString();
        }

        return lines;
    }
}
