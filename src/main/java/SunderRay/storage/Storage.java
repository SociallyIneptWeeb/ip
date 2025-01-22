package SunderRay.storage;

import SunderRay.data.formats.DateFormat;
import SunderRay.data.icons.StatusIcon;
import SunderRay.data.icons.TaskIcon;
import SunderRay.data.messages.ErrorMsg;
import SunderRay.tasklist.TaskList;
import SunderRay.tasks.Deadline;
import SunderRay.tasks.Event;
import SunderRay.tasks.Task;
import SunderRay.tasks.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Storage {
    private final String filepath = "data/tasks.txt";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Storage() throws CreateStorageFileException {
        try {
            File f = new File(this.filepath);
            if (!f.isFile()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new CreateStorageFileException(ErrorMsg.CREATE_FILE_ERROR);
        }

    }

    private boolean isValidTask(String[] details) {
        String taskIcon = details[0];

        if (Arrays.stream(TaskIcon.values()).noneMatch(icon -> icon.toString().equals(taskIcon))) {
            return false;
        }

        if (taskIcon.equals(TaskIcon.TODO.toString()) && details.length != 3) {
            return false;
        } else if (taskIcon.equals(TaskIcon.DEADLINE.toString()) && details.length != 4) {
            return false;
        } else if (taskIcon.equals(TaskIcon.EVENT.toString()) && details.length != 5) {
            return false;
        }

        String statusIcon = details[1];
        return Arrays.stream(StatusIcon.values()).anyMatch(icon -> icon.toParsableString().equals(statusIcon));
    }

    private Task parseTask(String line) throws ParseTaskException {
        String[] details = line.split("\\|");
        for (int i = 0; i < details.length; i++) {
            details[i] = details[i].trim();
        }

        if (!isValidTask(details)) {
            throw new ParseTaskException(ErrorMsg.PARSE_TASK_ERROR);
        }

        String taskType = details[0];
        boolean isDone = details[1].equals(StatusIcon.DONE.toParsableString());
        String description = details[2];
        Task task;
        try {
            task = switch (taskType) {
                case "T" -> new ToDo(description);
                case "D" -> new Deadline(
                        description,
                        LocalDate.parse(details[3], DateTimeFormatter.ofPattern(DateFormat.PARSABLE.toString())));
                case "E" -> new Event(description, details[3], details[4]);
                default -> throw new ParseTaskException(ErrorMsg.PARSE_TASK_ERROR);
            };
        } catch (DateTimeParseException e) {
            throw new ParseTaskException(ErrorMsg.PARSE_TASK_ERROR);
        }

        task.setIsDone(isDone);
        return task;
    }

    public TaskList load() throws IOException, ParseTaskException {
        TaskList taskList = new TaskList();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                taskList.addTask(task);
            }
        } catch (IOException e) {
            throw new IOException(ErrorMsg.CORRUPTED_DATA);
        }

        return taskList;
    }

    public void store(String[] taskLines) throws WriteStorageFileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath, false))) {
            for (String taskLine : taskLines) {
                writer.write(taskLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new WriteStorageFileException(ErrorMsg.SAVE_TASKS_ERROR);
        }
    }

    public static class CreateStorageFileException extends Exception {
        CreateStorageFileException(String message) {
            super(message);
        }
    }

    public static class WriteStorageFileException extends Exception {
        WriteStorageFileException(String message) {
            super(message);
        }
    }

    public static class ParseTaskException extends Exception {
        ParseTaskException(String message) {
            super(message);
        }
    }


}
