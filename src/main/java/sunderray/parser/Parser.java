package sunderray.parser;

import sunderray.commands.AddCommand;
import sunderray.commands.Command;
import sunderray.commands.CommandWord;
import sunderray.commands.DeleteCommand;
import sunderray.commands.ExitCommand;
import sunderray.commands.FindCommand;
import sunderray.commands.InvalidCommand;
import sunderray.commands.ListCommand;
import sunderray.commands.MarkCommand;
import sunderray.data.formats.DateFormat;
import sunderray.data.messages.ErrorMsg;
import sunderray.tasks.TaskList;
import sunderray.tasks.Deadline;
import sunderray.tasks.Event;
import sunderray.tasks.Timed;
import sunderray.tasks.ToDo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input into a command that can be executed.
 */
public class Parser {
    private static final Pattern eventPattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
    private static final Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");
    private static final Pattern timedPattern = Pattern.compile("timed (.+?) /duration (\\d{2}):([0-5][0-9])");

    /**
     * Parses user input into command for execution.
     *
     * @param taskList user's current tasklist
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parse(TaskList taskList, String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        assert words.length > 0;

        CommandWord commandWord;
        try {
            commandWord = CommandWord.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(ErrorMsg.UNKNOWN_COMMAND);
        }

        return switch (commandWord) {
            case BYE -> new ExitCommand();
            case LIST -> new ListCommand(taskList);
            case MARK, UNMARK, DELETE -> handleTaskModification(taskList, words, commandWord);
            case FIND -> handleFind(taskList, words);
            case TODO -> handleToDo(taskList, words);
            case DEADLINE -> handleDeadline(taskList, userInput);
            case EVENT -> handleEvent(taskList, userInput);
            case TIMED -> handleTimed(taskList, userInput);
            default -> new InvalidCommand(ErrorMsg.UNKNOWN_COMMAND);
        };
    }

    private static Command handleTaskModification(TaskList taskList, String[] words, CommandWord commandWord) {
        try {
            int taskId = Integer.parseInt(words[1]) - 1;
            if (taskId < 0 || taskId >= taskList.getNumTasks()) {
                return new InvalidCommand(ErrorMsg.INVALID_ID);
            }

            return commandWord.equals(CommandWord.DELETE) ?
                    new DeleteCommand(taskList, taskId) :
                    new MarkCommand(taskList, taskId, commandWord.equals(CommandWord.MARK));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new InvalidCommand(String.format(
                    ErrorMsg.WRONG_FORMAT,
                    String.format("%s <task-id>", commandWord.name().toLowerCase())));
        }
    }

    private static Command handleFind(TaskList taskList, String[] words) {
        return words.length < 2 ?
                new InvalidCommand(String.format(ErrorMsg.WRONG_FORMAT, "find <keyword>")) :
                new FindCommand(taskList, words[1]);
    }

    private static Command handleToDo(TaskList taskList, String[] words) {
        return words.length < 2 ?
                new InvalidCommand(String.format(ErrorMsg.WRONG_FORMAT, "todo <description>")) :
                new AddCommand(taskList, new ToDo(words[1]));
    }

    private static Command handleDeadline(TaskList taskList, String userInput) {
        Matcher matcher = deadlinePattern.matcher(userInput);
        Command invalidCommand = new InvalidCommand(String.format(
                ErrorMsg.WRONG_FORMAT, "deadline <description> /by <yyyy-MM-dd>"));

        if (matcher.find()) {
            try {
                LocalDate date = LocalDate.parse(matcher.group(2), DateTimeFormatter.ofPattern(DateFormat.PARSABLE));
                return new AddCommand(taskList, new Deadline(matcher.group(1), date));
            } catch (DateTimeParseException e) {
                return invalidCommand;
            }
        }

        return invalidCommand;
    }

    private static Command handleEvent(TaskList taskList, String userInput) {
        Matcher matcher = eventPattern.matcher(userInput);
        return matcher.find() ?
                new AddCommand(taskList, new Event(matcher.group(1), matcher.group(2), matcher.group(3))) :
                new InvalidCommand(String.format(
                        ErrorMsg.WRONG_FORMAT, "event <description> /from <when> /to <when>"));
    }

    private static Command handleTimed(TaskList taskList, String userInput) {
        Matcher matcher = timedPattern.matcher(userInput);
        if (matcher.find()) {
            Duration duration = Duration.ofHours(Integer.parseInt(matcher.group(2)))
                    .plusMinutes(Integer.parseInt(matcher.group(3)));
            return new AddCommand(taskList, new Timed(matcher.group(1), duration));
        }

        return new InvalidCommand(String.format(ErrorMsg.WRONG_FORMAT, "timed <description> /duration <HH:MM>"));
    }
}
