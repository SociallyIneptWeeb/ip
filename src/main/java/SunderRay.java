import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SunderRay {
    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println(InfoMsg.ADDED_TASK);
        System.out.printf("\t%s%n", task);
        System.out.printf(
                InfoMsg.NUM_TASKS.toString(),
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks");
    }

    public static void converse() {
        Storage storage;
        ArrayList<Task> tasks;
        try {
            storage = new Storage();
        } catch (IOException e) {
            System.out.println(ErrorMsg.CREATE_FILE_ERROR);
            return;
        }

        try {
            tasks = storage.load();
            System.out.printf(
                    InfoMsg.LOAD_DATA_FILE.toString(),
                    tasks.size(),
                    tasks.size() == 1 ? "task" : "tasks");
        } catch (IOException e) {
            System.out.println(ErrorMsg.CORRUPTED_DATA);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Pattern eventpattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
        Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");

        loop: while (true) {
            System.out.println(InfoMsg.DIVIDER);
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            System.out.println(InfoMsg.DIVIDER);

            System.out.print("Ray: ");

            String[] words = userInput.split(" ", 2);
            Command command;
            try {
                command = Command.valueOf(words[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMsg.UNKNOWN_COMMAND);
                continue;
            }
            int taskId;
            Task task;
            Matcher matcher;

            switch (command) {
            case BYE:
                System.out.println(InfoMsg.END);
                break loop;

            case LIST:
                if (tasks.isEmpty()) {
                    System.out.println(InfoMsg.NO_TASKS);
                } else {
                    System.out.println(InfoMsg.LIST_TASKS);

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d.  %s%n", i + 1, tasks.get(i));
                    }
                }
                break;

            case MARK:
                // Fallthrough

            case UNMARK:
                try {
                    taskId = Integer.parseInt(words[1]) - 1;
                    task = tasks.get(taskId);
                    task.setIsDone(command.equals(Command.MARK));
                    System.out.printf(InfoMsg.MARK_TASK.toString(), command.name().toLowerCase());
                    System.out.printf("\t%s%n", task);
                } catch (NumberFormatException e) {
                    System.out.printf(
                            ErrorMsg.WRONG_FORMAT.toString(),
                            String.format("%s <task-id>", command.name().toLowerCase()));
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(ErrorMsg.INVALID_ID);
                }
                break;

            case DELETE:
                try {
                    taskId = Integer.parseInt(words[1]) - 1;
                    task = tasks.get(taskId);
                    tasks.remove(taskId);
                    System.out.println(InfoMsg.DELETE_TASK);
                    System.out.printf("\t%s%n", task);
                    System.out.printf(
                            InfoMsg.NUM_TASKS.toString(),
                            tasks.size(),
                            tasks.size() == 1 ? "task" : "tasks");
                } catch (NumberFormatException e) {
                    System.out.printf(ErrorMsg.WRONG_FORMAT.toString(), "delete <task-id>");
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(ErrorMsg.INVALID_ID);
                }
                break;

            case TODO:
                if (words.length > 1) {
                    task = new ToDo(words[1]);
                    addTask(tasks, task);
                } else {
                    System.out.printf(ErrorMsg.WRONG_FORMAT.toString(), "todo <description>");
                }
                break;

            case DEADLINE:
                matcher = deadlinePattern.matcher(userInput);
                if (matcher.find()) {
                    task = new Deadline(matcher.group(1), matcher.group(2));
                    addTask(tasks, task);
                } else {
                    System.out.printf(ErrorMsg.WRONG_FORMAT.toString(), "deadline <description> /by <when>");
                }
                break;

            case EVENT:
                matcher = eventpattern.matcher(userInput);
                if (matcher.find()) {
                    task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                    addTask(tasks, task);
                } else {
                    System.out.printf(
                            ErrorMsg.WRONG_FORMAT.toString(),
                            "event <description> /from <when> /to <when>");
                }
                break;

            default:
                System.out.println(ErrorMsg.UNKNOWN_COMMAND);
            }

            try {
                storage.store(tasks);
            } catch (IOException e) {
                System.out.println(ErrorMsg.SAVE_TASKS_ERROR);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(InfoMsg.INTRO);
        converse();
    }
}
