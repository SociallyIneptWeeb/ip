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
            It’s not like I enjoy pointing out your mistakes or anything…""");

    private final String message;

    ErrorMsg(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
