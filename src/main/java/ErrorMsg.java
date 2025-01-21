public enum ErrorMsg {
    UNKNOWN_COMMAND("""
            W-What?! I couldn’t understand what you said, okay?
            It’s not like it’s my fault or anything!
            Just… ugh, repeat it already, and don’t make me wait!"""),
    WRONG_FORMAT("""
            Ugh, seriously? Be more clear! I can’t read your mind, you know!
            Just give it to me straight in this format: %s%n"""),
    INVALID_ID("""
            Ugh, seriously?! Just pick a valid task ID already!
            It’s not like I enjoy pointing out your mistakes or anything…"""),
    CORRUPTED_DATA("""
            The data file with your saved tasks is corrupted!
            I’m not cleaning up your mess, so you’d better fix or delete it, got it?!"""),
    CREATE_FILE_ERROR("""
            An unexpected error happened while I was trying to create a new file for your tasks!
            S-So hurry up and figure it out already!"""),
    SAVE_TASKS_ERROR("""
            Tch, an unexpected error came up while saving your tasks to the data file!
            It’s not a big deal or anything, but you’d better handle it before it gets worse!""");

    private final String message;

    ErrorMsg(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
