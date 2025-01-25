package sunderray.tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sunderray.tasks.Deadline;
import sunderray.tasks.Event;
import sunderray.tasks.Task;
import sunderray.tasks.ToDo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void getNumTasks_noTasks_zeroReturned() {
        assertEquals(taskList.getNumTasks(), 0);
    }

    @Test
    public void getNumTasks_threeTasks_threeReturned() {
        initTasks();
        assertEquals(taskList.getNumTasks(), 3);
    }

    @Test
    public void addTask_emptyList_oneTaskAdded() {
        Task task = new ToDo("Buy books");
        taskList.addTask(task);
        assertEquals(taskList.getNumTasks(), 1);
    }

    @Test
    public void deleteTask_someTasks_oneTaskDeleted() {
        initTasks();
        taskList.deleteTask(0);
        assertEquals(taskList.getNumTasks(), 2);
    }

    @Test
    public void toParsableLines_someTasks_formattedTasksReturned() {
        assertEquals(taskList.toParsableLines().length, 0);
        initTasks();
        String[] actualOutput = taskList.toParsableLines();
        String[] expectedOutput = {
                "T | 0 | Buy books",
                "D | 0 | Project presentation | 2025-11-20",
                "E | 0 | Idol show | Mon 3pm | 5pm"
        };
        for (int i = 0; i < expectedOutput.length; i++) {
            assertEquals(expectedOutput[i], actualOutput[i]);
        }
    }

    private void initTasks() {
        taskList.addTask(new ToDo("Buy books"));
        taskList.addTask(
                new Deadline("Project presentation", LocalDate.of(2025, 11, 20)));
        taskList.addTask(new Event("Idol show", "Mon 3pm", "5pm"));
    }
}
