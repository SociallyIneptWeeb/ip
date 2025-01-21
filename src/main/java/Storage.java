import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private final String filepath = "data/tasks.txt";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    Storage() throws IOException {
        File f = new File(this.filepath);
        if (!f.isFile()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
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

    // TODO: Throw a more meaningful exception
    private Task parseTask(String line) throws IOException {
        String[] details = line.split("\\|");
        for (int i = 0; i < details.length; i++) {
            details[i] = details[i].trim();
        }

        if (!isValidTask(details)) {
            throw new IOException();
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
                default -> throw new IOException();
            };
        } catch (DateTimeParseException e) {
            throw new IOException();
        }

        task.setIsDone(isDone);
        return task;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            // TODO: Throw a more meaningful exception
            throw new IOException();
        }

        return tasks;
    }

    public void store(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath, false))) {
            for (Task task : tasks) {
                writer.write(task.toParsableString());
                writer.newLine();
            }
        } catch (IOException e) {
            // TODO: Throw a more meaningful exception
            throw new IOException();
        }
    }
}
