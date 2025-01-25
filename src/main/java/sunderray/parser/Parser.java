package sunderray.parser;

import sunderray.commands.*;
import sunderray.data.formats.DateFormat;
import sunderray.data.messages.ErrorMsg;
import sunderray.tasklist.TaskList;
import sunderray.tasks.Deadline;
import sunderray.tasks.Event;
import sunderray.tasks.Task;
import sunderray.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern eventpattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
    private static final Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");

    public Command parse(TaskList taskList, String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        CommandWord commandWord;
        try {
            commandWord = CommandWord.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(ErrorMsg.UNKNOWN_COMMAND);
        }

        int taskId;
        Task task;
        Matcher matcher;

        switch (commandWord) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand(taskList);

        case MARK:
            // Fallthrough

        case UNMARK:
            // Fallthrough

        case DELETE:
            try {
                taskId = Integer.parseInt(words[1]) - 1;
            } catch (NumberFormatException e) {
                return new InvalidCommand(String.format(
                        ErrorMsg.WRONG_FORMAT,
                        String.format("%s <task-id>", commandWord.name().toLowerCase())));
            }

            if (taskId < 0 || taskId >= taskList.getNumTasks()) {
                return new InvalidCommand(ErrorMsg.INVALID_ID);
            }

            if (commandWord.equals(CommandWord.DELETE)) {
                return new DeleteCommand(taskList, taskId);
            }

            return new MarkCommand(taskList, taskId, commandWord.equals(CommandWord.MARK));

        case FIND:
            if (words.length < 2) {
                return new InvalidCommand(String.format(ErrorMsg.WRONG_FORMAT, "find <keyword>"));
            }

            return new FindCommand(taskList, words[1]);

        case TODO:
            if (words.length < 2) {
                return new InvalidCommand(String.format(ErrorMsg.WRONG_FORMAT, "todo <description>"));
            }

            task = new ToDo(words[1]);
            return new AddCommand(taskList, task);

        case DEADLINE:
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateFormat.PARSABLE.toString());
            matcher = deadlinePattern.matcher(userInput);
            Command invalidCommand = new InvalidCommand(String.format(
                    ErrorMsg.WRONG_FORMAT,
                    String.format("deadline <description> /by <%s>", DateFormat.PARSABLE)));

            if (matcher.find()) {
                try {
                    LocalDate date = LocalDate.parse(matcher.group(2), dtf);
                    task = new Deadline(matcher.group(1), date);
                    return new AddCommand(taskList, task);
                } catch (DateTimeParseException e) {
                    return invalidCommand;
                }
            }

            return invalidCommand;

        case EVENT:
            matcher = eventpattern.matcher(userInput);
            if (matcher.find()) {
                task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                return new AddCommand(taskList, task);
            }

            return new InvalidCommand(String.format(
                    ErrorMsg.WRONG_FORMAT,
                    "event <description> /from <when> /to <when>"));

        default:
            return new InvalidCommand(ErrorMsg.UNKNOWN_COMMAND);
        }
    }
}
