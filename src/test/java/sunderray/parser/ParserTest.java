package sunderray.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sunderray.data.messages.ErrorMsg;
import sunderray.data.messages.InfoMsg;
import sunderray.tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        taskList = new TaskList();
    }

    @Test
    public void parse_invalidCommand_unknownCommandMessage(){
        String input = "blah";
        String actualOutput = parser.parse(taskList, input).execute();
        String expectedOutput = ErrorMsg.UNKNOWN_COMMAND;
        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void parse_listNoTasks_noTasksMessage() {
        String input = "list";
        String actualOutput = parser.parse(taskList, input).execute();
        String expectedOutput = InfoMsg.NO_TASKS;
        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void parse_markInvalidTaskId_invalidIdMessage() {
        String input = "mark -1";
        String actualOutput = parser.parse(taskList, input).execute();
        String expectedOutput = ErrorMsg.INVALID_ID;
        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void parse_markWrongFormat_wrongFormatMessage() {
        String input = "unmark blah";
        String actualOutput = parser.parse(taskList, input).execute();
        String expectedOutput = String.format(ErrorMsg.WRONG_FORMAT, "unmark <task-id>");
        assertEquals(actualOutput, expectedOutput);
    }
}
