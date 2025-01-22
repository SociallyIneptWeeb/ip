public class ErrorMsg {
    public static final String UNKNOWN_COMMAND = """
            W-What?! I couldn’t understand what you said, okay?
            It’s not like it’s my fault or anything!
            Just… ugh, repeat it already, and don’t make me wait!""";
    public static final String WRONG_FORMAT = """
            Ugh, seriously? Be more clear! I can’t read your mind, you know!
            Just give it to me straight in this format: %s""";
    public static final String INVALID_ID = """
            Ugh, seriously?! Just pick a valid task ID already!
            It’s not like I enjoy pointing out your mistakes or anything…""";
    public static final String CORRUPTED_DATA = """
            The data file with your saved tasks is corrupted!
            I’m not cleaning up your mess, so you’d better fix or delete it, got it?!""";
    public static final String CREATE_FILE_ERROR = """
            An unexpected error happened while I was trying to create a new file for your tasks!
            S-So hurry up and figure it out already!""";
    public static final String PARSE_TASK_ERROR = """
            There was an error parsing your saved tasks from the data file! I-It’s not like I’m
            blaming you or anything, but maybe check if they’re in the correct format, okay?""";
    public static final String SAVE_TASKS_ERROR = """
            Tch, an unexpected error came up while saving your tasks to the data file!
            It’s not a big deal or anything, but you’d better handle it before it gets worse!""";
}
